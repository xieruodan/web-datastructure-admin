package com.butter.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.butter.admin.bean.ChoiceQuestion;
import com.butter.admin.bean.ChoiceQuestionRecord;
import com.butter.admin.bean.ExerciseRecord;
import com.butter.admin.bean.User;
import com.butter.admin.mapper.ChoiceQuestionPlusMapper;
import com.butter.admin.mapper.ChoiceQuestionRecordMapper;
import com.butter.admin.mapper.ExerciseMapper;
import com.butter.admin.service.ChoiceQuestionPlusService;
import com.butter.admin.service.ChoiceQuestionRecordPlusService;
import com.butter.admin.service.ChoiceQuestionService;
import com.butter.admin.service.ExerciseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 做题板块
 */
@Slf4j
@Controller
public class ExerciseController {

    @Autowired
    ExerciseService exerciseService;

    @Autowired
    ExerciseMapper exerciseMapper;

    @Autowired
    ChoiceQuestionService choiceQuestionService;

    @Autowired
    ChoiceQuestionPlusMapper choiceQuestionPlusMapper;

    @Autowired
    ChoiceQuestionRecordPlusService choiceQuestionRecordPlusService;

    @Autowired
    ChoiceQuestionRecordMapper choiceQuestionRecordMapper;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    private EurekaDiscoveryClient eurekaDiscoveryClient;

    /**
     * 进入做题主界面
     * @param session
     * @return
     */
    @GetMapping("/exercise")
    public String exercisePage(HttpSession session) {
        if (session.getAttribute("loginUser") != null) {
            User loginUser = (User) session.getAttribute("loginUser");
            log.info(loginUser.getUserName() + "访问了exercise的主页面");
        } else {
            log.info("有一个游客访问exercise的主页面");
        }
        return "exercise/exerciseMain";
    }

    /**
     * 用户选择做题章节、难度、时间、题目数
     * @param pn 分页展示
     * @param er 用户本次选择的试卷，试卷id
     * @param session
     * @param model
     * @return
     */
    @PostMapping("/goToExercise")
    public String goToExercise(@RequestParam(value = "pn", defaultValue = "1") Integer pn, ExerciseRecord er, HttpSession session, Model model) {
        log.info("有人进入exercise具体的做题页面");

        log.info(String.valueOf(session.getAttribute("loginUser")));

        User loginUser = (User) session.getAttribute("loginUser");
        er.setUserId(loginUser.getId());

        String levelS = er.getLevelS();
        int level = 0;
        switch (levelS) { //三种难度，低中高
            case "Easy":
                level = 1;
                break;
            case "Medium":
                level = 2;
                break;
            case "Hard":
                level = 3;
                break;
            default:
                break;
        }
        er.setLevel(level);
        exerciseService.saveExerciseRecord(er); //将本次用户选择试卷的信息存到数据库
        session.setAttribute("exerciseRecord", er);
        log.info(er.toString());

        /**
         * 搜集对应难度和章节的题目
         */
        List<ChoiceQuestion> choiceQuestionList = choiceQuestionService.getByLevelAndSection(er.getLevel(), er.getSection()); //从数据库中按用户要求随机抽取题目
        session.setAttribute("choiceQuestionList", choiceQuestionList);

//        Page<ChoiceQuestion> choicePageRule = new Page<>(pn, 2);
//        Page<ChoiceQuestion> choicePage = choiceQuestionPlusMapper.choiceQuestionPage(choicePageRule, er.getLevel(), er.getSection());
//
//        log.info(choicePage.getRecords());
//        log.info(choicePage.getPages());
//        log.info(choicePage.getCurrent());
//        log.info(choicePage.getTotal());



//        QueryWrapper<ChoiceQuestion> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("qLevel", er.getLevel());
//        Page<ChoiceQuestion> test = choiceQuestionPlusMapper.test(choicePageRule, queryWrapper);
//        log.info(test.getRecords());
//        log.info(test.getPages());
//        log.info(test.getCurrent());
//        log.info(test.getTotal());

        return "redirect:/exercise/doExercise";
    }

    /**
     * 跳转到用户实际的做题页面
     * @param pn
     * @param session
     * @param model
     * @return
     */
    @GetMapping("/exercise/doExercise")
    public String doExerciseGet(@RequestParam(value = "pn", defaultValue = "1") Integer pn, HttpSession session, Model model) {
        log.info("get页面");
        List<ChoiceQuestion> choiceQuestionList = (List<ChoiceQuestion>) session.getAttribute("choiceQuestionList");
        log.info(choiceQuestionList.toString());
        ExerciseRecord er = (ExerciseRecord) session.getAttribute("exerciseRecord");
        log.info(er.toString());

        ChoiceQuestion currentChoiceQuestion = choiceQuestionList.get(pn - 1);
        model.addAttribute("choicePage", currentChoiceQuestion);
        model.addAttribute("pn", pn);
        return "exercise/doExercise";
    }

    /**
     * 用户提交做题记录，利用RabbitMQ将用户每一道题的做题详情保存到RabbitMQ的exchange queue中
     * @param pn
     * @param session
     * @param model
     * @param usersAnswer
     * @param content
     * @param ra
     * @return
     */
    @PostMapping("/exercise/doExercise")
    public String doExercisePost(@RequestParam(value = "pn", defaultValue = "1") Integer pn, HttpSession session, Model model,
                                 @RequestParam("usersAnswer") String usersAnswer, @RequestBody String content,
                                 RedirectAttributes ra) {
        List<ChoiceQuestion> choiceQuestionList = (List<ChoiceQuestion>) session.getAttribute("choiceQuestionList");
        log.info(choiceQuestionList.toString());

        /**
         * 保存用户做题的结果，并判断正确与否
         */
        log.info(usersAnswer);
        log.info(content);

        ChoiceQuestion choiceQuestion = choiceQuestionList.get(pn - 1);
        ChoiceQuestionRecord choiceQuestionRecord = new ChoiceQuestionRecord();
        choiceQuestionRecord.setErId(((ExerciseRecord) session.getAttribute("exerciseRecord")).getErId());
        choiceQuestionRecord.setUserId(((ExerciseRecord) session.getAttribute("exerciseRecord")).getUserId());
        choiceQuestionRecord.setQId(choiceQuestion.getQID());
        if (choiceQuestion.getQAnswer().equals(usersAnswer)) {
            choiceQuestionRecord.setIsright(true);
        } else {
            choiceQuestionRecord.setIsright(false);
        }
        choiceQuestionRecord.setUsersAnswer(usersAnswer);
//        choiceQuestionRecordPlusService.save(choiceQuestionRecord);
        //amqp替换上一句
        rabbitTemplate.convertAndSend("exchange.direct", "exercise.record", choiceQuestionRecord);

        pn += 1;
        model.addAttribute("pn", pn);
        log.info(pn.toString() + "..." + pn);
        log.info("post页面");
        ra.addAttribute("pn", pn);

        if (pn != 6) {
            return "redirect:/exercise/doExercise";
        } else {
            return "redirect:/exercise/resultPage";
        }
    }

    /**
     * 用户做完题目后，跳转到结果页面，告诉用户对了几道题
     * @param session
     * @param model
     * @return
     */
    @GetMapping("/exercise/resultPage")
    public String getResult(HttpSession session, Model model) {
        ExerciseRecord er = (ExerciseRecord) session.getAttribute("exerciseRecord");
        long userId = er.getUserId();
        long erId = er.getErId();
        int rightCount = choiceQuestionRecordMapper.getRightCountByErId(erId);
        model.addAttribute("rightCount", rightCount);
        return "exercise/resultPage";
    }

    /**
     * 查看本次的错题
     * @param pn
     * @param session
     * @param model
     * @return
     */
    @GetMapping("/exercise/reflectFaultQuestion")
    public String reflectFaultQuestion(@RequestParam(value = "pn", defaultValue = "1") Integer pn, HttpSession session, Model model) {
        ExerciseRecord er = (ExerciseRecord) session.getAttribute("exerciseRecord");
        List<Long> falseQIdList = choiceQuestionRecordMapper.getFalseQIdByErId(er.getErId());
        log.info(falseQIdList.toString());

        List<ChoiceQuestion> falseChoiceQuestionList = new ArrayList<>();
        for (Long qId : falseQIdList) {
            falseChoiceQuestionList.add(choiceQuestionService.getByQId(qId));
        }
        model.addAttribute("falseChoiceQuestionList", falseChoiceQuestionList);
        model.addAttribute("falseChoiceQuestion", falseChoiceQuestionList.get(pn - 1));
        model.addAttribute("pn", pn);
        return "exercise/reflectFaultQuestion";
    }

    /**
     * spring cloud微服务，为es的consumer（用户学习情况后台数据管理平台）提供用户的错题数据
     * @param userId
     * @return
     */
    @ResponseBody
    @GetMapping("/exercise/record/{userId}")
    public Map get(@PathVariable Long userId) {
        List<ChoiceQuestionRecord> falseQuestionCount = choiceQuestionRecordMapper.getFalseQuestionCount(userId);
        log.info(String.valueOf(falseQuestionCount.get(0)));
        int totalFalseCount = falseQuestionCount.size();

        List<Long> erIdList = new ArrayList<>();
        for (ChoiceQuestionRecord cqr : falseQuestionCount) {
            erIdList.add(cqr.getErId());
        }

        Map<String, Integer> falseSectionDistributionMap = new HashMap<>();
        for (Long erId : erIdList) {
            String sectionByErId = exerciseMapper.getSectionByErId(erId);
            falseSectionDistributionMap.put(sectionByErId, falseSectionDistributionMap.getOrDefault(sectionByErId,0) + 1);
        }
//        int sortingCount =;
//        int linkedListCount =;
//        int hashTableCount =;
//        int binarySearchTreeCount = ;
//        int graphTraversalCount = ;
//        int minimumSpanningTreeCount = ;
//        int shortestPathCount = ;
//        int cycleFindingCount = ;
//        int basicConcepCount = ;
        return falseSectionDistributionMap;
    }
}

package com.butter.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.butter.admin.bean.ThreadRecord;
import com.butter.admin.bean.User;
import com.butter.admin.mapper.ThreadRecordPlusMapper;
import com.butter.admin.service.ThreadRecordPlusService;
import com.butter.admin.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * 论坛的处理逻辑
 */
@Slf4j
@Controller
public class ForumController {

    @Autowired
    ThreadRecordPlusService threadRecordPlusService;

    @Autowired
    UserService userService;

    @Autowired
    ThreadRecordPlusMapper threadRecordPlusMapper;

    /**
     * 展示论坛主页面
     * @param pn
     * @param model
     * @param session
     * @return
     */
    @GetMapping("/forumMain")
    public String forumMainPage(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model, HttpSession session) {
        List<ThreadRecord> threadList = threadRecordPlusService.list();
        log.info(threadList.toString());
        List<ThreadRecord> currentList = new ArrayList<>();
        currentList.add(threadList.get((pn - 1) * 2));
        if (pn == (threadList.size() + 1) / 2 && threadList.size() % 2 != 0){

        } else {
            currentList.add(threadList.get((pn - 1) * 2 + 1));
        }

//        Page<ThreadRecord> threadRecordPage = new Page<>(pn, 2);
//        Page<ThreadRecord> threadPage = threadRecordPlusService.page(threadRecordPage, null);
//        log.info(threadPage.getCurrent());
//        log.info(threadPage.getPages());
//        log.info(threadPage.getTotal());

        model.addAttribute("currentList", currentList);
        model.addAttribute("threadList", threadList);
        model.addAttribute("pn", pn);
        model.addAttribute("pages", (threadList.size() + 1) / 2);

        return "forum/forum_main";
    }

    /**
     * 每个贴子的具体页面
     * @param threadIdS
     * @param session
     * @param model
     * @return
     */
    @GetMapping("/thread")
    public String threadPage(@RequestParam(value = "threadId", defaultValue = "1") String threadIdS, HttpSession session, Model model) {
        Long threadId = Long.valueOf(threadIdS);
        ThreadRecord threadById = threadRecordPlusMapper.getThreadRecordById(threadId);
        model.addAttribute("currentThread", threadById);
        log.info("thread内容" + threadById);

        User userById = userService.getById(threadId);
        String userName = userById.getUserName();
        model.addAttribute("creatorName", userName);
        log.info("发帖人姓名" + userName);
        return "forum/thread";
    }
}

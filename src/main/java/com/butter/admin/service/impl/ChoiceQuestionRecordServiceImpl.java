package com.butter.admin.service.impl;

import com.butter.admin.bean.ChoiceQuestionRecord;
import com.butter.admin.mapper.ChoiceQuestionRecordMapper;
import com.butter.admin.service.ChoiceQuestionRecordPlusService;
import com.butter.admin.service.ChoiceQuestionRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ChoiceQuestionRecordServiceImpl implements ChoiceQuestionRecordService {

    @Autowired
    ChoiceQuestionRecordMapper choiceQuestionRecordMapper;

    @Autowired
    ChoiceQuestionRecordPlusService choiceQuestionRecordPlusService;

    public int getRightCountByErId(long erId) {
        return choiceQuestionRecordMapper.getRightCountByErId(erId);
    }

    public List<Long> getFalseQIdByErId(long erId) {
        return choiceQuestionRecordMapper.getFalseQIdByErId(erId);
    }

    @RabbitListener(queues = "exercise.record")
    public void receiveChoiceQuestionRecord(ChoiceQuestionRecord cqr) {
        log.info("收到做题记录" + cqr.toString());
        choiceQuestionRecordPlusService.save(cqr);
    }

}

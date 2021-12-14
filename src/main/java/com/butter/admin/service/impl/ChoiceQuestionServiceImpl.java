package com.butter.admin.service.impl;

import com.butter.admin.bean.ChoiceQuestion;
import com.butter.admin.mapper.ChoiceQuestionMapper;
import com.butter.admin.service.ChoiceQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;

@Service
public class ChoiceQuestionServiceImpl implements ChoiceQuestionService {

    @Autowired
    ChoiceQuestionMapper choiceQuestionMapper;

    public List<ChoiceQuestion> getByLevelAndSection(int level, String section) {
        return choiceQuestionMapper.getByLevelAndSection(level, section);
    }

    public ChoiceQuestion getByQId(long qId) {
        return choiceQuestionMapper.getByQId(qId);
    }

}
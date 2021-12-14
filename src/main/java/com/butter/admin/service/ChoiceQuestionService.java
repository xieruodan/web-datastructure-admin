package com.butter.admin.service;

import com.butter.admin.bean.ChoiceQuestion;

import java.util.List;

public interface ChoiceQuestionService {

    public List<ChoiceQuestion> getByLevelAndSection(int level, String section);

    public ChoiceQuestion getByQId(long qId);

}

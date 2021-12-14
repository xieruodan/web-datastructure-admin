package com.butter.admin.service;

import com.butter.admin.bean.ChoiceQuestion;
import com.butter.admin.bean.ChoiceQuestionRecord;

import java.util.List;

public interface ChoiceQuestionRecordService {

    public int getRightCountByErId(long erId);

    public List<Long> getFalseQIdByErId(long erId);

    public void receiveChoiceQuestionRecord(ChoiceQuestionRecord cqr);

}

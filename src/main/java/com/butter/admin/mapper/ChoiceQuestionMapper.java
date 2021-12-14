package com.butter.admin.mapper;

import com.butter.admin.bean.ChoiceQuestion;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ChoiceQuestionMapper {

    public List<ChoiceQuestion> getByLevelAndSection(int level, String section);

    @Select("select * from choice_question_tbl where qID=#{qId}")
    public ChoiceQuestion getByQId(long qId);

}

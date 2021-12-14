package com.butter.admin.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.butter.admin.bean.ChoiceQuestion;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
@Mapper
public interface ChoiceQuestionPlusMapper extends BaseMapper<ChoiceQuestion> {

    @Select("select * from choice_question_tbl where qLevel=#{level} AND qSection=#{section} order by rand() limit 5")
    Page<ChoiceQuestion> choiceQuestionPage(Page<ChoiceQuestion> page, int level, String section);

    @Select("select * from choice_question_tbl ${ew.customSqlSegment}")
    Page<ChoiceQuestion> test(Page<ChoiceQuestion> page, @Param("ew") Wrapper<ChoiceQuestion> wrapper);

}

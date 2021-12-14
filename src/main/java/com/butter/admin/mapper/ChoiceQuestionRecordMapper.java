package com.butter.admin.mapper;

import com.butter.admin.bean.ChoiceQuestionRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ChoiceQuestionRecordMapper {

    public int getRightCountByErId(long erId);

    public List<Long> getFalseQIdByErId(long erId);

    @Select("select * from choice_question_record_tbl where user_id=#{userId} AND isright=false")
    public List<ChoiceQuestionRecord> getFalseQuestionCount(long userId);



    //    @Insert("insert into choice_question_record_tbl(``) values(#{})")
    //    public void insertChoiceQuestionRecord(ChoiceQuestionRecord cqr);

}

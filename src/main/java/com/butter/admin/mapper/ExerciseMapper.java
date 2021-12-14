package com.butter.admin.mapper;

import com.butter.admin.bean.ExerciseRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ExerciseMapper {

    public void insertExerciseRecord(ExerciseRecord er);

    @Select("select section from exercise_record_tbl where er_id=#{erId}")
    public String getSectionByErId(long erId);

}

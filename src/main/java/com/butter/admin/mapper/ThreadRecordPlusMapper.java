package com.butter.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.butter.admin.bean.ThreadRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface ThreadRecordPlusMapper extends BaseMapper<ThreadRecord> {

    @Select("select * from forum_thread_tbl where thread_id=#{id}")
    public ThreadRecord getThreadRecordById(Long id);


}

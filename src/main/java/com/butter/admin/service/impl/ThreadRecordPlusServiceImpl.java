package com.butter.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.butter.admin.bean.ThreadRecord;
import com.butter.admin.mapper.ThreadRecordPlusMapper;
import com.butter.admin.service.ThreadRecordPlusService;
import org.springframework.stereotype.Service;

@Service
public class ThreadRecordPlusServiceImpl extends ServiceImpl<ThreadRecordPlusMapper, ThreadRecord> implements ThreadRecordPlusService {

}

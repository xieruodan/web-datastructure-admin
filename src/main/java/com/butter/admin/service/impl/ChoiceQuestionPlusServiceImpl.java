package com.butter.admin.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.butter.admin.bean.ChoiceQuestion;
import com.butter.admin.mapper.ChoiceQuestionPlusMapper;
import com.butter.admin.service.ChoiceQuestionPlusService;
import com.butter.admin.service.ChoiceQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChoiceQuestionPlusServiceImpl extends ServiceImpl<ChoiceQuestionPlusMapper, ChoiceQuestion> implements ChoiceQuestionPlusService {

}

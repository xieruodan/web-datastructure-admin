package com.butter.admin.service.impl;

import com.butter.admin.bean.ExerciseRecord;
import com.butter.admin.mapper.ExerciseMapper;
import com.butter.admin.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    @Autowired
    ExerciseMapper exerciseMapper;

    public void saveExerciseRecord(ExerciseRecord er) {
        exerciseMapper.insertExerciseRecord(er);
    }

}

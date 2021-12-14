package com.butter.admin.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * ExerciseRecord表保存本次试卷，用户选择了章节、难度、题目数、时间
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("exercise_record_tbl")
public class ExerciseRecord {

    private Long erId; //用户本次做的试卷id
    private String userName; //用户姓名
    private Long userId; //用户id
    private String section; //用户选择的本次试卷的章节
    private String levelS;
    private Integer level; //用户选择的本次试卷的难度
    private Integer questionNum; //用户选择的本次试卷的试题数量
    private Integer time; //用户选择的做题时间

}

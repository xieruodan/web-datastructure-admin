package com.butter.admin.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ChoiceQuestionRecord保存用户在做题模块的做的每一道题的记录
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("choice_question_record_tbl")
public class ChoiceQuestionRecord {

    private Long cqrId; //用户做的每一道题的记录id
    private Long erId; //本次试卷id
    private Long userId; //用户id
    private Long qId; //题目id
    private boolean isright; //用户是否答对了
    private String usersAnswer; //用户的答案

}

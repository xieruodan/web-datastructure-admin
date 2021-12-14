package com.butter.admin.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ChoiceQuestion保存做题板块所有题目的信息
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("choice_question_tbl")
public class ChoiceQuestion {

    private Long qID; //题目id
    private String qContent; //题目内容
    private String aChoice; //A选项
    private String bChoice;
    private String cChoice;
    private String dChoice;
    private String qAnswer; //正确答案
    private Integer qLevel; //题目难度
    private String qSection; //题目所属章节

}

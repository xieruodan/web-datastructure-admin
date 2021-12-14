package com.butter.admin.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
 * ThreadRecord保存论坛贴子记录
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("forum_thread_tbl")
public class ThreadRecord {

    private Long threadId; //论坛贴子id
    private String title; //贴子标题
    private String content; //贴子内容
    private Long creatorId; //发贴人id
    private Date createdTime; //发贴时间
    private Integer followCount; //该贴子的关注数
    private Integer upvoteCount; //该贴子的点赞数
    private Integer replyCount;//该贴子的总回复数
    private Date lastAnsweredAt; //该贴子最后被回复的时间
    private String lastAnsweredBy; //改贴子最后被回复的回复人姓名

}

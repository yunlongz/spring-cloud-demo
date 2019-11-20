package com.example.zhouyunlong.pintuan.entity;

import lombok.Data;

import java.util.Date;

/**
 * 2 * @Author: zhouyunlong
 * 3 * @Date: 2019/11/9 17:24
 * 4
 */
@Data
public class GroupActivityRel {
    private Long id;
    private Long activityId;
    private Long groupMemberId;
    private int asKing;
    private int joinNum;
    private int status;
    private Date createTime;
    private Date updateTime;
}

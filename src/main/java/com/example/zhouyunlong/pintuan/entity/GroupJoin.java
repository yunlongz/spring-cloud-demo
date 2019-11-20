package com.example.zhouyunlong.pintuan.entity;

import lombok.Data;

import java.util.Date;

/**
 * 2 * @Author: zhouyunlong
 * 3 * @Date: 2019/11/9 22:57
 * 4
 */
@Data
public class GroupJoin {
    private Long id;
    private Long groupId;
    private Long activityId;
    private Long memberId;
    private Date createTime;
}

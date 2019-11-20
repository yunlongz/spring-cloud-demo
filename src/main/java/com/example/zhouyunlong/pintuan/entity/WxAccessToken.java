package com.example.zhouyunlong.pintuan.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 2 * @Author: zhouyunlong
 * 3 * @Date: 2019/11/15 13:43
 * 4
 */
@Data
public class WxAccessToken implements Serializable {
    private Long id;
    private String appid;
    private String accessToken;
    private Date createTime;
    private Date updateTime;
}

package com.example.zhouyunlong.pintuan.entity;

import com.baomidou.mybatisplus.annotations.KeySequence;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 2 * @Author: zhouyunlong
 * 3 * @Date: 2019/11/23 14:33
 * 4
 */
@Data
@Accessors(chain = true)
@TableName(value = "user_subscribe")
@RequiredArgsConstructor
@KeySequence(value = "user_subscribe_sequence",clazz = Integer.class)
public class UserSubscribe {
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    @TableField(value = "template_id")
    private String templateId;
    @NotNull
    @TableField(value = "openid")
    private String openid;

    @TableField(value = "subscribe_status")
    private Integer subscribeStatus;

    @TableField(value = "send_status")
    private Integer sendStatus;

    @TableField(value = "host_ip")
    private String hostIp;

    @TableField(value = "appid")
    private String appid;

    @TableField(value = "create_time")
    private Date createTime;

    @TableField(value = "update_time")
    private Date updateTime;

}

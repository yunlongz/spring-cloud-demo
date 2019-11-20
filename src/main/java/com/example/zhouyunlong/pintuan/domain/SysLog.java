package com.example.zhouyunlong.pintuan.domain;
import com.baomidou.mybatisplus.annotations.KeySequence;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.type.JdbcType;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 2 * @Author: zhouyunlong
 * 3 * @Date: 2019/11/16 15:40
 * 4
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value = "sys_log")
@KeySequence(value = "sys_log_sequence",clazz = Integer.class)
public class SysLog implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    /**
     * 操作IP
     */
    @TableField(value = "request_ip")
    private String requestIp;

    /**
     * 操作类型 1 操作记录 2异常记录
     */
    @TableField(value = "op_type")
    private Integer opType;

    /**
     * 操作人ID
     */
    @TableField(value = "username")
    private String username;

    /**
     * 操作描述
     */
    @TableField(value = "description")
    private String description;
    /**
     * 返回状态码
     */
    @TableField(value = "status_code")
    private long statusCode;
    /**
     * 返回状态描述
     */
    @TableField(value = "status_message")
    private String statusMessage;
    /**
     * 请求方法
     */
    @TableField(value = "action_method")
    private String actionMethod;

    /**
     * 请求url
     */
    @TableField(value = "action_url")
    private String actionUrl;

    /**
     * 请求参数
     */
    @TableField(value = "params")
    private String params;

    /**
     * 浏览器
     */
    @TableField(value = "ua")
    private String ua;

    /**
     * 类路径
     */
    @TableField(value = "class_path")
    private String classPath;

    /**
     * 请求方法
     */
    @TableField(value = "request_method")
    private String requestMethod;

    /**
     * 开始时间
     */
    @TableField(value = "start_time")
    private Date startTime;

    /**
     * 完成时间
     */
    @TableField(value = "finish_time")
    private Date finishTime;

    /**
     * 消耗时间
     */
    @TableField(value = "consuming_time")
    private Long consumingTime;

    /**
     * 异常详情信息 堆栈信息
     */
    @TableField(value = "ex_detail")
    private String exDetail;

    /**
     * 异常描述 e.getMessage
     */
    @TableField(value = "ex_desc")
    private String exDesc;
}

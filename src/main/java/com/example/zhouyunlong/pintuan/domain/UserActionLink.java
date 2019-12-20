package com.example.zhouyunlong.pintuan.domain;

import com.baomidou.mybatisplus.annotations.KeySequence;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * 2 * @Author: zhouyunlong
 * 3 * @Date: 2019/12/20 22:55
 * 4
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value = "user_action_outer_link")
@KeySequence(value = "user_action_outer_link_seq",clazz = Integer.class)
public class UserActionLink implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;
    /*本次连接ID*/
    private String sessionId;
    /*渠道号*/
    private String chnlId;
    /*推荐者ID*/
    private String kikId;
    /*用户ID*/
    private String userId;
    // 来源ID
    private String sourceId;
    // SDK版本号
    private String sdkVersion;
    // 微信版本号
    private String wechatVersion;
    // 电量
    private String batteryLevel;
    // 品牌
    private String brand;
    // 型号
    private String model;
    // 设备平台
    private String platform;
    // 系统版本
    private String system;
    // 报错信息
    private String errMsg;
    // 字号
    private String fontSizeSetting;
    // 语言
    private String language;
    // 像素密度
    private String pixelRatio;
    // 设备高度像素
    private String screenHeight;
    // 设备宽度像素
    private String screenWidth;
    // 状态栏高度
    private String statusBarHeight;
    // 窗口高度
    private String widowHeight;
    // 窗口宽度
    private String windowWidth;
    // 安全区域顶部
    private String safeTop;
    // 安全区域右侧
    private String safeRight;
    // 安全区域左侧
    private String safeLeft;
    // 安全区域底部
    private String safeBottom;
    // 连接开始时间
    private String startTime;
    // 连接结束时间
    private String endTime;
}

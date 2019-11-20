package com.example.zhouyunlong.pintuan.log.event;

import com.example.zhouyunlong.pintuan.domain.SysLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

/**
 * 2 * @Author: zhouyunlong
 * 3 * @Date: 2019/11/16 15:56
 * 4
 */
public class SysLogEvent extends ApplicationEvent {

    public SysLogEvent(SysLog sysLog) {
        super(sysLog);
    }
}
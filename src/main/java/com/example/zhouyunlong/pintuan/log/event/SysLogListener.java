package com.example.zhouyunlong.pintuan.log.event;

import com.example.zhouyunlong.pintuan.domain.SysLog;
import com.example.zhouyunlong.pintuan.service.SysLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 2 * @Author: zhouyunlong
 * 3 * @Date: 2019/11/16 16:01
 * 4
 */
@Slf4j
@Component
public class SysLogListener {

    @Autowired
    private SysLogService sysLogService;

    @Async
    @Order
    @EventListener(SysLogEvent.class)
    public void saveSysLog(SysLogEvent event) {
        SysLog sysLog = (SysLog) event.getSource();
        // 保存日志
        sysLogService.insert(sysLog);
    }
}

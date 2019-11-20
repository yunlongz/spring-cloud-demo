package com.example.zhouyunlong.pintuan.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.example.zhouyunlong.pintuan.domain.SysLog;

/**
 * 2 * @Author: zhouyunlong
 * 3 * @Date: 2019/11/16 16:04
 * 4
 */
public interface SysLogService extends IService<SysLog> {

    /**
     * 分页查询日志
     * @param page
     * @param opType
     * @return
     */
    Page<SysLog> selectSysLogPage(Page<SysLog> page, Integer opType);
}

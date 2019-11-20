package com.example.zhouyunlong.pintuan.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.zhouyunlong.pintuan.dao.SysLogMapper;
import com.example.zhouyunlong.pintuan.domain.SysLog;
import com.example.zhouyunlong.pintuan.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 2 * @Author: zhouyunlong
 * 3 * @Date: 2019/11/16 16:50
 * 4
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {
    @Autowired
    SysLogMapper sysLogMapper;
    @Override
    public Page<SysLog> selectSysLogPage(Page<SysLog> page, Integer opType) {
        // 不进行 count sql 优化，解决 MP 无法自动优化 SQL 问题
        // page.setOptimizeCountSql(false);
        // 不查询总记录数
        // page.setSearchCount(false);
        // 注意！！ 分页 total 是经过插件自动 回写 到传入 page 对象
        return page.setRecords(sysLogMapper.selectSysLogList(page, opType));
    }
}

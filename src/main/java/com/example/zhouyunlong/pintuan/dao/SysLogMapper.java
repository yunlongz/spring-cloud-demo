package com.example.zhouyunlong.pintuan.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.example.zhouyunlong.pintuan.domain.SysLog;
import com.example.zhouyunlong.pintuan.entity.GroupMember;

import java.security.acl.Group;
import java.util.List;

/**
 * 2 * @Author: zhouyunlong
 * 3 * @Date: 2019/11/16 16:53
 * 4
 */
public interface SysLogMapper extends BaseMapper<SysLog> {
    /**
     * 和Mybatis使用方法一致
     * @param page
     * @param opType
     * @return
     */
    List<SysLog> selectSysLogList(Pagination page, Integer opType);
}

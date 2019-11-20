package com.example.zhouyunlong.pintuan.service;

import com.alicp.jetcache.anno.Cached;
import com.example.zhouyunlong.pintuan.entity.GroupMember;

/**
 * 2 * @Author: zhouyunlong
 * 3 * @Date: 2019/11/10 20:50
 * 4
 */
public interface MemberService {
    /**
     * 查询用户
     * @param memberId
     * @return
     */
    @Cached(name="MemberService.getGroupMemberById", expire = 3600)
    GroupMember getGroupMemberById(long memberId);
}

package com.example.zhouyunlong.pintuan.service.impl;

import com.example.zhouyunlong.pintuan.dao.GroupMemberMapper;
import com.example.zhouyunlong.pintuan.entity.GroupMember;
import com.example.zhouyunlong.pintuan.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 2 * @Author: zhouyunlong
 * 3 * @Date: 2019/11/10 21:09
 * 4
 */
@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    GroupMemberMapper groupMemberMapper;
    @Override
    public GroupMember getGroupMemberById(long memberId) {
         return groupMemberMapper.selectById(memberId);
    }

}

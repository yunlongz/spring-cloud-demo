package com.example.zhouyunlong.pintuan.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.zhouyunlong.pintuan.entity.GroupActivityRel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 2 * @Author: zhouyunlong
 * 3 * @Date: 2019/11/9 17:28
 * 4
 */
public interface JoinPintuanMapper extends BaseMapper<GroupActivityRel> {
    /**
     * 和Mybatis使用方法一致
     * @param id
     * @return
     */

    List<GroupActivityRel> selectByMemberIdForUpdate(@Param("group_member_id") Long id);
    /**
     * 和Mybatis使用方法一致
     * @param memberId
     * @param groupId
     * @return
     */
    List<GroupActivityRel> selectByMemberIdAndGroupIdForUpdate(@Param("group_member_id") Long memberId, @Param("id") Long groupId);

    /**
     * 和Mybatis使用方法一致
     * @param groupId
     * @return
     */
    GroupActivityRel selectByGroupIdForUpdate(@Param("id") Long groupId);
}

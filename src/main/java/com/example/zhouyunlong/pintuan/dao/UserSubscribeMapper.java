package com.example.zhouyunlong.pintuan.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.zhouyunlong.pintuan.entity.UserSubscribe;

import java.util.List;

/**
 * 2 * @Author: zhouyunlong
 * 3 * @Date: 2019/11/23 14:30
 * 4
 */
public interface UserSubscribeMapper extends BaseMapper<UserSubscribe> {
    List<UserSubscribe> list();
}

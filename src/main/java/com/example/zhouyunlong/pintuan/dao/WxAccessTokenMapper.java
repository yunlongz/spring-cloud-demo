package com.example.zhouyunlong.pintuan.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.zhouyunlong.pintuan.entity.WxAccessToken;
import org.apache.ibatis.annotations.Param;

/**
 * 2 * @Author: zhouyunlong
 * 3 * @Date: 2019/11/15 13:43
 * 4
 */
public interface WxAccessTokenMapper extends BaseMapper<WxAccessToken> {
    /**
     * 和Mybatis使用方法一致
     * @param appid
     * @return
     */
    WxAccessToken selectByAppidForUpdate(@Param("appid") String appid);
}

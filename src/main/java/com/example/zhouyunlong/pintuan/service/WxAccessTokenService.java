package com.example.zhouyunlong.pintuan.service;

import com.alicp.jetcache.anno.CacheRefresh;
import com.alicp.jetcache.anno.Cached;
import com.example.zhouyunlong.pintuan.entity.GroupMember;
import com.example.zhouyunlong.pintuan.entity.WxAccessToken;
import org.apache.tomcat.util.descriptor.web.WebXml;

import java.util.concurrent.TimeUnit;

/**
 * 2 * @Author: zhouyunlong
 * 3 * @Date: 2019/11/15 13:57
 * 4
 */
public interface WxAccessTokenService {
    /**
     * 查询微信用access_token
     * @param appid
     * @return WxAccessToken
     */
    @Cached(name="WxAccessTokenService.getWxAccessToken", expire = 20 ,timeUnit = TimeUnit.SECONDS)

    WxAccessToken getWxAccessToken(String appid);

    /**
     * 刷新 access_token
     * @param dateStr
     * @return
     */
    void refreshToken(String dateStr);
}

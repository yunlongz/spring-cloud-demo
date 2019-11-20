package com.example.zhouyunlong.pintuan.controller;
import	java.security.KeyStore;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.alibaba.fastjson.JSONObject;
import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CreateCache;
import com.example.zhouyunlong.pintuan.api.CommonResult;
import com.example.zhouyunlong.pintuan.config.WxMaConfiguration;
import com.example.zhouyunlong.pintuan.entity.GroupMember;
import com.example.zhouyunlong.pintuan.log.util.RedisUtil;
import com.example.zhouyunlong.pintuan.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 2 * @Author: zhouyunlong
 * 3 * @Date: 2019/11/15 20:09
 * 4
 */
@Slf4j
@RestController
@RequestMapping("/wx/user/{appid}")
public class WxMaUserController {
    @Autowired
    RedisUtil redisUtil;
    @CreateCache(name = "formCache-", expire = 3600)
    private Cache<String, String> formCache;
    /**
     * 登陆接口
     * @return
     */
    @GetMapping("/login")
    public CommonResult login(@PathVariable String appid, String code) {
        log.info("code {}", code);
        if (StringUtils.isBlank(code)) {
            return CommonResult.failed("empty jscode");
        }

        final WxMaService wxService = WxMaConfiguration.getMaService(appid);

        try {
            WxMaJscode2SessionResult session = wxService.getUserService().getSessionInfo(code);
            formCache.put(session.getOpenid(), session.getSessionKey());
            redisUtil.set(session.getOpenid(), session.getSessionKey());
            log.info("formCache open {}", formCache.get(session.getOpenid()));
            //TODO 可以增加自己的逻辑，关联业务相关数据

            return CommonResult.ok(session);
        } catch (WxErrorException e) {
            log.error(e.getMessage(), e);
            return CommonResult.failed(e.getMessage());
        }
    }

    /**
     * <pre>
     * 获取用户信息接口
     * </pre>
     */
    @GetMapping("/info")
    public String info(@PathVariable String appid, String sessionKey,
                       String signature, String rawData, String encryptedData, String iv) {
        final WxMaService wxService = WxMaConfiguration.getMaService(appid);

        // 用户信息校验
        if (!wxService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
            return "user check failed";
        }
        // 解密用户信息
        WxMaUserInfo userInfo = wxService.getUserService().getUserInfo(sessionKey, encryptedData, iv);

        return JsonUtils.toJson(userInfo);
    }

    /**
     * <pre>
     * 获取用户绑定手机号信息
     * </pre>
     */
    @GetMapping("/phone")
    public String phone(@PathVariable String appid, String sessionKey, String signature,
                        String rawData, String encryptedData, String iv) {
        final WxMaService wxService = WxMaConfiguration.getMaService(appid);

        // 用户信息校验
        if (!wxService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
            return "user check failed";
        }

        // 解密
        WxMaPhoneNumberInfo phoneNoInfo = wxService.getUserService().getPhoneNoInfo(sessionKey, encryptedData, iv);

        return JsonUtils.toJson(phoneNoInfo);
    }

}

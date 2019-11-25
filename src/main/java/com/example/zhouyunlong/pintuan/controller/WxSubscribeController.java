package com.example.zhouyunlong.pintuan.controller;
import	java.awt.font.NumericShaper.Range;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.example.zhouyunlong.pintuan.api.CommonResult;
import com.example.zhouyunlong.pintuan.entity.UserSubscribe;
import com.example.zhouyunlong.pintuan.log.annotation.SysLog;
import com.example.zhouyunlong.pintuan.service.UserSubscribeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Objects;

/**
 * 2 * @Author: zhouyunlong
 * 3 * @Date: 2019/11/15 19:19
 * 4
 */
@Slf4j
@RestController
@RequestMapping("/wx/subscribe/{appid}")
public class WxSubscribeController {
    @Autowired
    UserSubscribeService userSubscribeService;
    @SysLog(descrption = "订阅发奖通知")
    @PostMapping("/sub")
    public CommonResult subscribe(@PathVariable String appid, @RequestBody UserSubscribe us)  {

        EntityWrapper<UserSubscribe> wrapper = new EntityWrapper();
        UserSubscribe userSubscribe = new UserSubscribe();
        userSubscribe.setOpenid(us.getOpenid());
        userSubscribe.setUpdateTime(new Date());
        wrapper.eq("openid",userSubscribe.getOpenid())
            .eq("appid",appid);
        boolean updateResult = userSubscribeService.update(userSubscribe,wrapper);
        if(!updateResult){
            userSubscribe.setTemplateId(us.getTemplateId());
            userSubscribe.setSendStatus(0);
            userSubscribe.setSubscribeStatus(1);
            userSubscribe.setCreateTime(new Date());
            userSubscribe.setAppid(appid);
            try {
                userSubscribe.setHostIp(InetAddress.getLocalHost().getHostAddress());
                userSubscribeService.insert(userSubscribe);
            }catch (Exception e){
                return CommonResult.failed("订阅失败");
            }
        }
        return CommonResult.ok("订阅成功");
    }
}

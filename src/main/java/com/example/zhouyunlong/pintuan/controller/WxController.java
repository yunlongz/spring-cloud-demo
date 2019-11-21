package com.example.zhouyunlong.pintuan.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaSubscribeData;
import cn.binarywang.wx.miniapp.bean.WxMaSubscribeMessage;
import cn.binarywang.wx.miniapp.bean.WxMaTemplateData;
import cn.binarywang.wx.miniapp.bean.WxMaTemplateMessage;
import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CreateCache;
import com.example.zhouyunlong.pintuan.api.CommonResult;
import com.example.zhouyunlong.pintuan.config.WxMaConfiguration;
import com.example.zhouyunlong.pintuan.config.WxMaProperties;
import com.example.zhouyunlong.pintuan.entity.WxAccessToken;
import com.example.zhouyunlong.pintuan.log.annotation.SysLog;
import com.example.zhouyunlong.pintuan.log.util.RedisUtil;
import com.example.zhouyunlong.pintuan.service.WxAccessTokenService;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * 2 * @Author: zhouyunlong
 * 3 * @Date: 2019/11/15 17:01
 * 4
 */
@Slf4j
@RestController
@RequestMapping("/wx")
public class WxController {
    @CreateCache(name = "formCache-", expire = 3600)
    private Cache<String, String> formCache;

    @Autowired
    WxAccessTokenService wxAccessTokenService;
    @Autowired
    WxMaProperties wxMaProperties;
    @Autowired
    RedisUtil redisUtil;


    @SysLog(descrption = "测试获取用户token")
    @ApiOperation(value="测试获取用户token", notes="")
    @GetMapping("/accesstoken")
    public CommonResult getAccessToken(@RequestParam String openid){
        log.info("formCache {}",formCache.get("formCache-"+openid));
        log.info("redisUtil {}", redisUtil.get(openid));
        WxAccessToken wxAccessToken = wxAccessTokenService.getWxAccessToken("123");
        return CommonResult.ok(wxAccessToken);
    }
    @SysLog(descrption = "用户领奖")
    @GetMapping("/push")
    public void push(@RequestParam String openid, @RequestParam String formid) throws WxErrorException{
//        d3a211eea6794b89be26f930a57dfb02
        List<WxMaProperties.Config> configs = wxMaProperties.getConfigs();
        configs.forEach(config -> {
            final WxMaService wxMaService = WxMaConfiguration.getMaService(config.getAppid());
            WxMaTemplateMessage templateMessage = WxMaTemplateMessage.builder()
                    .templateId("vbkIDYLdzcSME1Wr398nmTVKzeNNfDjg14APpeDVkCg")
                    .formId(formid)
                    .data(Lists.newArrayList(
                            new WxMaTemplateData("keyword1", "339208499", "#173177")))
                    .toUser(openid)
                    .build();
            try {
                wxMaService.getMsgService().sendTemplateMsg(templateMessage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    @SysLog(descrption = "推送用户订阅领奖")
    @GetMapping("/push/subscribe")
    public void pushSubscribe(@RequestParam String openid) throws WxErrorException{
//        d3a211eea6794b89be26f930a57dfb02
        List<WxMaSubscribeData> list = Lists.newArrayList();
        list.add(new WxMaSubscribeData("time2", "2019年12月29日 18:00", "#173177"));
        list.add(new WxMaSubscribeData("number3", String.valueOf(30), "#173177"));
        list.add(new WxMaSubscribeData("thing4", "龙粉大奖发布了", "#173177"));
        list.add(new WxMaSubscribeData("thing5", "呵呵呵呵呵呵....", "#173177"));
        List<WxMaProperties.Config> configs = wxMaProperties.getConfigs();
        configs.forEach(config -> {
            final WxMaService wxMaService = WxMaConfiguration.getMaService(config.getAppid());
            WxMaSubscribeMessage wxMaSubscribeMessage = WxMaSubscribeMessage.builder()
                    .templateId("vbkIDYLdzcSME1Wr398nmTVKzeNNfDjg14APpeDVkCg")
                    .page("index")
                    .data(list)
                    .toUser(openid)
                    .build();
            try {
                wxMaService.getMsgService().sendSubscribeMsg(wxMaSubscribeMessage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @SysLog(descrption = "生成小程序海报")
    @GetMapping("/create/qrcode")
    public void createQrCode() throws WxErrorException{
//        d3a211eea6794b89be26f930a57dfb02
        List<WxMaProperties.Config> configs = wxMaProperties.getConfigs();
        configs.forEach(config -> {
            final WxMaService wxMaService = WxMaConfiguration.getMaService(config.getAppid());
            try {
                wxMaService.getQrcodeService().createWxaCodeUnlimit("a=1","pages/index/index");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}

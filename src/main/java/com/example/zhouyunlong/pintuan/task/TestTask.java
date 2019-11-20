//package com.example.zhouyunlong.pintuan.task;
//
//import cn.binarywang.wx.miniapp.api.WxMaService;
//import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
//import cn.binarywang.wx.miniapp.config.WxMaConfig;
//import cn.binarywang.wx.miniapp.config.impl.WxMaRedisConfigImpl;
//import cn.hutool.Hutool;
//import cn.hutool.core.bean.BeanUtil;
//import cn.hutool.core.date.DateUnit;
//import cn.hutool.core.date.DateUtil;
//import cn.hutool.core.thread.ThreadUtil;
//import com.example.zhouyunlong.pintuan.config.WxMaConfiguration;
//import com.example.zhouyunlong.pintuan.config.WxMaProperties;
//import com.example.zhouyunlong.pintuan.dao.WxAccessTokenMapper;
//import com.example.zhouyunlong.pintuan.entity.WxAccessToken;
//import com.example.zhouyunlong.pintuan.service.WxAccessTokenService;
//import lombok.extern.slf4j.Slf4j;
//import me.chanjar.weixin.common.error.WxErrorException;
//import org.checkerframework.checker.units.qual.A;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.management.*;
//import java.net.InetAddress;
//import java.net.UnknownHostException;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Set;
//
///**
// * 2 * @Author: zhouyunlong
// * 3 * @Date: 2019/11/13 21:49
// * 4
// */
//@Slf4j
//@Component
//public class TestTask {
//    @Autowired
//    WxAccessTokenService wxAccessTokenService;
//    @Autowired
//    WxMaProperties wxMaProperties;
//    @Value("${server.port}")
//    private String port;
//    @Scheduled(cron = "* */20 * * * ?")
//    void process() throws UnknownHostException {
////        InetAddress.getLocalHost().getHostAddress();
////        String format = DateUtil.format(new Date(), "yyyy-MM-dd HH:mm");
////        wxAccessTokenService.refreshToken(port + "//" + format);
////        ThreadUtil.safeSleep(2000);
//        log.info("执行完毕");
//    }
//
//    @Scheduled(cron = "*/20 * * * * ?")
//    void refreshToken()  {
//
//        List<WxMaProperties.Config> configs = wxMaProperties.getConfigs();
//        configs.forEach(config -> {
//            final WxMaService wxService = WxMaConfiguration.getMaService(config.getAppid());
//            try {
//
//                log.info("access_token :{}", wxService.getAccessToken());
//
//            } catch (WxErrorException e) {
//                e.printStackTrace();
//            }
//        });
//    }
//}

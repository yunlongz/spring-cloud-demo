package com.example.zhouyunlong.pintuan.service;

import com.example.zhouyunlong.pintuan.dao.WxAccessTokenMapper;
import com.example.zhouyunlong.pintuan.entity.WxAccessToken;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * 2 * @Author: zhouyunlong
 * 3 * @Date: 2019/11/15 14:02
 * 4
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class WxAccessTokenServiceTest {
    @Autowired
    WxAccessTokenService wxAccessTokenService;
    @Autowired
    WxAccessTokenMapper wxAccessTokenMapper;
    @Test
    public void testGetWxToken(){
        WxAccessToken wxAccessToken = wxAccessTokenService.getWxAccessToken("123");
        log.info("获取到的token是 {}",wxAccessToken.getAccessToken());
    }

    @Test
    public void addToken(){
        WxAccessToken wxAccessToken = new WxAccessToken();
        wxAccessToken.setAccessToken("skdlajfksl;dajfsdjkfl;jskadl;fj");
        wxAccessToken.setAppid("123");
        wxAccessToken.setCreateTime(new Date());
        wxAccessToken.setUpdateTime(new Date());

        wxAccessTokenMapper.insert(wxAccessToken);
    }
}

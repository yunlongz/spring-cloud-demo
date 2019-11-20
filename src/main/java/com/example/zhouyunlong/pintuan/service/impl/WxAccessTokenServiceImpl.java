package com.example.zhouyunlong.pintuan.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.example.zhouyunlong.pintuan.dao.WxAccessTokenMapper;
import com.example.zhouyunlong.pintuan.entity.WxAccessToken;
import com.example.zhouyunlong.pintuan.service.WxAccessTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 2 * @Author: zhouyunlong
 * 3 * @Date: 2019/11/15 13:57
 * 4
 */
@Slf4j
@Service
public class WxAccessTokenServiceImpl implements WxAccessTokenService {

    @Autowired WxAccessTokenMapper wxAccessTokenMapper;

    /**
     * 查询微信用access_token
     *
     * @return WxAccessToken
     */
    @Override
    public WxAccessToken getWxAccessToken(String appid) {
//        QueryWrapper<WxAccessToken> query = new QueryWrapper<>();
//        query.eq("appid", appid);
//        return wxAccessTokenMapper.selectOne(query);
        return null;
    }


    @Override
    @Transactional(rollbackFor = Exception.class, propagation= Propagation.REQUIRED)
    public void refreshToken(String dateStr) {
        WxAccessToken wxAccessToken = wxAccessTokenMapper.selectByAppidForUpdate("123");
        long betweenDay = DateUtil.between(wxAccessToken.getUpdateTime(), new Date(), DateUnit.MINUTE);
        log.info("token 间隔时间？{}", betweenDay);
        if(betweenDay > 1){
            WxAccessToken wxAccessTokenNew = new WxAccessToken();
            BeanUtil.copyProperties(wxAccessToken, wxAccessTokenNew);
            wxAccessTokenNew.setUpdateTime(new Date());
            wxAccessTokenNew.setAccessToken("token "+ dateStr);
            log.info("新的是什么？{}",wxAccessTokenNew.getAccessToken());
            log.info("新的是什么？{}",wxAccessTokenNew.getId());
            int i = wxAccessTokenMapper.updateById(wxAccessTokenNew);
            log.info("更新一次token {}", i);
        }
    }


}

package com.example.zhouyunlong.pintuan.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.example.zhouyunlong.pintuan.api.CommonResult;
import com.example.zhouyunlong.pintuan.domain.SysLog;
import com.example.zhouyunlong.pintuan.service.SysLogService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 2 * @Author: zhouyunlong
 * 3 * @Date: 2019/12/20 22:46
 * 4
 */
@Slf4j
@RestController
@RequestMapping("/user/log")
public class UserActionLogController {
    @Autowired
    SysLogService sysLogService;

    @ApiOperation(value="获取用户列表", notes="")
    @GetMapping("/gif")
    public void getSysList(PathVariable UserActionLink ){

    }
}

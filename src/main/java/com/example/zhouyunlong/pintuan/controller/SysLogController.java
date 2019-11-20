package com.example.zhouyunlong.pintuan.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.example.zhouyunlong.pintuan.api.CommonResult;
import com.example.zhouyunlong.pintuan.domain.SysLog;
import com.example.zhouyunlong.pintuan.service.SysLogService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 2 * @Author: zhouyunlong
 * 3 * @Date: 2019/11/19 21:40
 * 4
 */
@Slf4j
@RestController
@RequestMapping("/syslog")
public class SysLogController {
    @Autowired
    SysLogService sysLogService;
    @com.example.zhouyunlong.pintuan.log.annotation.SysLog(descrption = "获取用户列表")
    @ApiOperation(value="获取用户列表", notes="")
    @GetMapping("/list")
    public CommonResult getSysList(@RequestParam int currentPage , @RequestParam int size){
        Page<SysLog> page = new Page<>(currentPage,size);
        return CommonResult.ok(sysLogService.selectSysLogPage(page, 1));
    }
}

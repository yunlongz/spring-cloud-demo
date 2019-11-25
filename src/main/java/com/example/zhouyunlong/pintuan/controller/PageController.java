package com.example.zhouyunlong.pintuan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 2 * @Author: zhouyunlong
 * 3 * @Date: 2019/11/25 22:48
 * 4
 */
@Controller
@RequestMapping(value = "/page")
public class PageController {
    @RequestMapping(value = "/file/index", method = RequestMethod.GET)
    public String index()
    {
        return "upload";
    }
}

package com.tank.design.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: TODO
 * @author hzm
 * @Date 2019/9/17
 * @version V1.0
 */

@RestController
@RequestMapping(value = "demo")
public class DemoController {

    @GetMapping("/test")
    public String test(){
        return "hello";
    }
}

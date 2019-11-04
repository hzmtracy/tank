package com.tank.springvalidator.web.controller;

import com.tank.springvalidator.web.model.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author hzm
 * @version V1.0
 * @description: TODO
 * @Date 2019/9/29
 */

@RestController
@Validated
public class TestController {

    @GetMapping("/test1")
    @Cacheable
    public String test1(@NotBlank(message = "{required}") String name, @Email(message = "{invalid}") String email) {
        return "success";
    }

    @GetMapping("/test2")
    public String test2(@Valid User user){
        return "success";
    }
}

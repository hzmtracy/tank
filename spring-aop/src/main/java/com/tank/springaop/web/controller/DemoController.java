package com.tank.springaop.web.controller;

import com.tank.springaop.domain.DivisionRequest;
import com.tank.springaop.domain.DivisionResponse;
import com.tank.springaop.web.service.DemoService;
import com.tank.springaop.web.service.NumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: TODO
 * @author hzm
 * @Date 2019/9/9
 * @version V1.0
 */

@RestController
public class DemoController {

    @Autowired
    private DemoService demoService;

    @Autowired
    private NumberService numberService;

    @GetMapping("division.do")
    public DivisionResponse doDivisionRequest(@RequestParam int a,
                                              @RequestParam int b) throws Exception{
        // 构建请求
        DivisionRequest request = new DivisionRequest();
        request.setDividend(a);
        request.setDivisor(b);

        // 执行
        return demoService.divide(request);
    }

    @GetMapping("another.do")
    public Integer doAnotherDivision(@RequestParam int a,
                                     @RequestParam int b) throws Exception {
        return numberService.divide(a, b);
    }
}

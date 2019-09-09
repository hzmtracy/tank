package com.tank.springaop.web.service.impl;

import com.tank.springaop.aspect.annotation.MethodAspectAnnotation;
import com.tank.springaop.domain.DivisionRequest;
import com.tank.springaop.domain.DivisionResponse;
import com.tank.springaop.web.service.DemoService;
import com.tank.springaop.web.service.aspect.DemoServiceMethodAspectProcessor;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author hzm
 * @version V1.0
 * @description: TODO
 * @Date 2019/9/9
 */
@Service
public class DemoServiceImpl implements DemoService {
    @Override
    @MethodAspectAnnotation(DemoServiceMethodAspectProcessor.class)
    public DivisionResponse divide(DivisionRequest request) throws Exception {

        DivisionResponse response = new DivisionResponse();
        // 请求参数
        int dividend = request.getDividend();
        int divisor = request.getDivisor();

        // 模拟检查业务参数
        // ...检查业务参数...
        TimeUnit.MILLISECONDS.sleep(300);

        // 模拟执行业务
        int result = dividend / divisor;

        // 设置业务执行结果
        response.setData(result);

        return response;

    }
}

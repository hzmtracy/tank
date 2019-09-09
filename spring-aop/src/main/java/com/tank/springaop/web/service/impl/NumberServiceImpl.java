package com.tank.springaop.web.service.impl;

import com.tank.springaop.aspect.annotation.MethodAspectAnnotation;
import com.tank.springaop.domain.DivisionResponse;
import com.tank.springaop.web.service.NumberService;
import com.tank.springaop.web.service.aspect.DemoServiceMethodAspectProcessor;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author hzm
 * @version V1.0
 * @description: TODO
 * @Date 2019-09-06
 */

@Service
public class NumberServiceImpl implements NumberService {
    @MethodAspectAnnotation(DemoServiceMethodAspectProcessor.class)
    @Override
    public Integer divide(int dividend, int divisor) throws Exception {
        // 模拟检查业务参数
        // ...检查业务参数...
        TimeUnit.MILLISECONDS.sleep(300);

        // 模拟执行业务
        int result = dividend / divisor;

        DivisionResponse divisionResponse = new DivisionResponse();
        divisionResponse.setData(result);
        return result;
    }
}

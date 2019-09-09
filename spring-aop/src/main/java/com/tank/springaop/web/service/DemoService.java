package com.tank.springaop.web.service;

import com.tank.springaop.domain.DivisionRequest;
import com.tank.springaop.domain.DivisionResponse;

/**
 * @author hzm
 * @version V1.0
 * @description: TODO
 * @Date 2019/9/9
 */

public interface DemoService {

    DivisionResponse divide(DivisionRequest request) throws Exception;
}

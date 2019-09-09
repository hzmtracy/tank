package com.tank.springaop.web.service;

import com.tank.springaop.domain.DivisionResponse;

/**
 * @description: TODO
 * @author hzm
 * @Date 2019-09-06
 * @version V1.0
 */

public interface NumberService {
    /**
     * @description: 除法运算
     * @param dividend, divisor
     * @return: 
     * @author: hzm
     * @createTime: 2019-09-06 17:08
     * @version: v1.0
    */
    Integer divide(int dividend, int divisor) throws Exception;
}

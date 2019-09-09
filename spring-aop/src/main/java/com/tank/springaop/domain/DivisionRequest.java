package com.tank.springaop.domain;

import com.tank.springaop.domain.base.BaseRequest;
import lombok.Data;

/**
 * @description: TODO
 * @author hzm
 * @Date 2019-09-06
 * @version V1.0
 */
@Data
public class DivisionRequest extends BaseRequest {
    private static final long serialVersionUID = 2430324037465409630L;

    private int dividend;

    private int divisor;
}

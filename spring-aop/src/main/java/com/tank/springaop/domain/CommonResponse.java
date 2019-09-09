package com.tank.springaop.domain;

import com.tank.springaop.domain.base.BaseResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @description: TODO
 * @author hzm
 * @Date 2019/9/9
 * @version V1.0
 */

@Setter
@Getter
@ToString
public class CommonResponse<T> extends BaseResponse<T> {

    private static final long serialVersionUID = -2851246705205927716L;
}

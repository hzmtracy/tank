package com.tank.springaop.base;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @description: 返回状态基本封装
 * @author hzm
 * @Date 2019-09-06
 * @version V1.0
 */

@Getter
@Setter
@ToString
public class BaseResponse<T> implements Serializable {

    private static final long serialVersionUID = -3168362572730377741L;



}

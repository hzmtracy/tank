package com.tank.springaop.domain.base;

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

    /**
     * 业务返回值
     */
    protected T data;

    /**
     * 业务成功与否
     */
    protected boolean success;

    /**
     * 业务错误码
     */
    protected int errorCode;

    /**
     * 业务错误信息
     */
    protected String errorMsg;

    /**
     * 页面用户提示
     */
    protected String prompt;

    /**
     * 调用时间
     */
    protected long sysTime;

    /**
     * 调用耗时
     */
    protected long costTime;

    /**
     * 服务器名称（用于错误定位）
     */
    protected String host;

}

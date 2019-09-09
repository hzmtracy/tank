package com.tank.springaop.web.service.aspect;

import com.tank.springaop.aspect.annotation.SharableAnnotation;
import com.tank.springaop.aspect.processor.impl.AbstractMethodAspectProcessor;
import com.tank.springaop.domain.CommonResponse;
import com.tank.springaop.domain.base.BaseRequest;
import com.tank.springaop.domain.base.BaseResponse;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;

import java.util.UUID;

/**
 * @author hzm
 * @version V1.0
 * @description: ServiceMethodAspectProcessor
 * @Date 2019/9/9
 */

@SharableAnnotation
public class DemoServiceMethodAspectProcessor extends AbstractMethodAspectProcessor<BaseResponse> {

    /**
     * 是否是要处理的方法<br/>
     * 限定方法类型入参匹配 BaseRequest，返回值匹配 BaseResponse
     *
     * @param point 方法的连接点
     * @return 是要处理的方法返回 true，否则返回 false
     */
    @Override
    public boolean isMatched(ProceedingJoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Class returnType = signature.getReturnType();
        if (BaseResponse.class.isAssignableFrom(returnType)) {
            Class[] parameterTypes = signature.getParameterTypes();
            return parameterTypes.length == 1 && BaseRequest.class.isAssignableFrom(parameterTypes[0]);
        }
        return false;
    }

    /**
     * 构建抛出异常时的返回值<br/>
     * 不知道起什么名字好，如果大家有好的建议，欢迎留言
     *
     * @param point 方法的连接点
     * @param e     抛出的异常
     * @return 抛出异常时的返回值
     */
    @Override
    public BaseResponse returnWhenThrowing(ProceedingJoinPoint point, Throwable e) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Class<? extends BaseResponse> returnType = signature.getReturnType();

        // 构造抛出异常时的返回值
        BaseResponse response = newInstance(returnType);
        response.setPrompt(e.getMessage());
        response.setSuccess(false);
        return response;
    }

    /**
     * 切面完成时，执行的动作
     *
     * @param point     方法的连接点
     * @param startTime 执行的开始时间
     * @param result    执行获得的结果
     */
    @Override
    public void onComplete(ProceedingJoinPoint point, long startTime, BaseResponse result) {
        BaseResponse baseResponse = (BaseResponse) result;
        baseResponse.setSysTime(startTime);
        baseResponse.setHost(getHost());
        baseResponse.setCostTime(System.currentTimeMillis() - startTime);
        Logger logger = getLogger(point);

        // point.getArgs() 获得方法调用入参
        Object request = point.getArgs()[0];

        // 记录方法调用信息
        logger.info("{}, request={}, response={}", getLogTag(point), request, baseResponse);
    }

    /**
     * 正常返回时，执行的动作
     *
     * @param point  方法的连接点
     * @param result 方法返回的结果
     */
    @Override
    public void onReturn(ProceedingJoinPoint point, BaseResponse result) {
        String logTag = getLogTag(point);
        Logger logger = getLogger(point);

        result.setSuccess(true);
        logger.info("{} 正常调用", logTag);
    }

    private BaseResponse newInstance(Class<? extends BaseResponse> returnType) {
        try {
            return returnType.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            return new CommonResponse();
        }
    }

    /**
     * 模拟获得服务器名称
     */
    private String getHost() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
}

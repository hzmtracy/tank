package com.tank.springaop.aspect.processor;

import com.tank.springaop.aspect.annotation.ServiceMethodAspectAnnotation;
import com.tank.springaop.base.BaseResponse;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author hzm
 * @version V1.0
 * @Date 2019-09-04
 * @description: TODO
 */
@Component
@Aspect
public class ServiceMethodAspect {

    /**
     * 方法连接点 (处理被 @ServiceMethodAspectAnnotation 注解的方法)
     */
    @Pointcut("@annotation(com.tank.springaop.aspect.annotation.ServiceMethodAspectAnnotation)")
    public void methodPointcut() {
    }

    /**
     * 切入被 @ServiceMethodAspectAnnotation 注解的方法
     *
     * @param joinPoint 连接点
     * @return 方法返回值
     * @throws Throwable
     */
    @Around("methodPointcut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        if (isMatched(joinPoint)) {
            onMisMatch(joinPoint);
            return joinPoint.proceed();
        }

        long startTime = System.currentTimeMillis();
        Object result = null;
        try {
            // 执行目标方法
            result = joinPoint.proceed();
            // 正常返回
            onReturn(joinPoint, result);
        } catch (Exception e) {
            // 异常处理
            onThrow(joinPoint, e);
            // 抛出异常的情况下，则构造一个返回值的实例，用于业务服务方法的返回
            result = returnWhenThrowing(joinPoint, e);
        }

        // 切面结束
        onComplete(joinPoint, startTime, result);

        return result;
    }

    private void onComplete(ProceedingJoinPoint joinPoint, long startTime, Object result) {
    }

    private BaseResponse returnWhenThrowing(ProceedingJoinPoint joinPoint, Exception e) {
    }

    private void onThrow(ProceedingJoinPoint joinPoint, Exception e) {

    }

    /**
     * @description: 正常返回时，执行的动作
     * @param joinPoint
     * @return: 
     * @author: hzm
     * @createTime: 2019-09-06 10:13
     * @version: v1.0
    */
    private void onReturn(ProceedingJoinPoint joinPoint, Object result) {
        String logTag = getLogTag(joinPoint);
        Logger logger = getLogger(joinPoint);
        result
    }

    /**
     * @param joinPoint
     * @description: 如果是不要处理的方法，执行的动作
     * @return:
     * @author: hzm
     * @createTime: 2019-09-05 17:21
     * @version: v1.0
     */
    private void onMisMatch(ProceedingJoinPoint joinPoint) {
        Logger logger = getLogger(joinPoint);
        String logTag = getLogTag(joinPoint);
        logger.warn("{} 不是 @{} 可以处理的方法", logTag, ServiceMethodAspectAnnotation.class.getSimpleName());

    }

  /**
   * @description: 是否是匹配的方法; 限定方法类型入参匹配 BaseRequest，返回值匹配 BaseResponse
   * @param joinPoint
   * @return: 是可以处理的方法返回 true，否则返回 false
   * @author: hzm
   * @createTime: 2019-09-06 10:10
   * @version: v1.0
  */
    private boolean isMatched(ProceedingJoinPoint joinPoint) {
        return false;
    }

    /**
     * @description: 获得被代理对象的 Logger
     * @param joinPoint
     * @return:
     * @author: hzm
     * @createTime: 2019-09-06 10:11
     * @version: v1.0
    */
    private Logger getLogger(ProceedingJoinPoint joinPoint) {
        Object target = joinPoint.getTarget();
        return LoggerFactory.getLogger(target.getClass());
    }

    /**
     * @description: LogTag = 类名.方法名
     * @param joinPoint
     * @return: 目标类名.执行方法名
     * @author: hzm
     * @createTime: 2019-09-06 10:12
     * @version: v1.0
    */
    private String getLogTag(ProceedingJoinPoint joinPoint) {
        Object target = joinPoint.getTarget();
        String className = target.getClass().getSimpleName();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getName();
        return className + '.' + methodName;
    }

}

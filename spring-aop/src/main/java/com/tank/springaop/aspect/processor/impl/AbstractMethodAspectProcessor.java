package com.tank.springaop.aspect.processor.impl;

import com.tank.springaop.aspect.annotation.MethodAspectAnnotation;
import com.tank.springaop.aspect.processor.MethodAspectProcessor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @author hzm
 * @version V1.0
 * @description: TODO
 * @Date 2019-09-09
 */

public abstract class AbstractMethodAspectProcessor<R> implements MethodAspectProcessor<R> {

    @Override
    public void onMismatch(ProceedingJoinPoint point) {
        Logger logger = getLogger(point);
        String logTag = getLogTag(point);

        // 获得方法签名
        MethodSignature signature = (MethodSignature) point.getSignature();
        // 获得方法
        Method method = signature.getMethod();
        // 获得方法的 @MethodAspectAnnotation 注解
        MethodAspectAnnotation methodAspectAnnotation = method.getAnnotation(MethodAspectAnnotation.class);
        // 获得方法切面处理器的 Class
        Class<? extends MethodAspectProcessor> processorType = methodAspectAnnotation.value();

        // 如果是接口或者抽象类
        if (processorType.isInterface() || Modifier.isAbstract(processorType.getModifiers())) {
            logger.warn("{} 需要指定具体的切面处理器，因为 {} 是接口或者抽象类", logTag, processorType.getSimpleName());
            return;
        }

        logger.warn("{} 不是 {} 可以处理的方法", logTag, processorType.getSimpleName());
    }

    @Override
    public void onThrow(ProceedingJoinPoint point, Throwable e) {
        Logger logger = getLogger(point);
        String logTag = getLogTag(point);

        logger.error("{} 执行时出错", logTag, e);
    }

    /**
     * 获得被代理类的 Logger
     *
     * @param point 连接点
     * @return 被代理类的 Logger
     */
    protected Logger getLogger(ProceedingJoinPoint point) {
        Object target = point.getTarget();
        return LoggerFactory.getLogger(target.getClass());
    }

    /**
     * LogTag = 类名.方法名
     *
     * @param point 连接点
     * @return 目标类名.执行方法名
     */
    protected String getLogTag(ProceedingJoinPoint point) {
        Object target = point.getTarget();
        String className = target.getClass().getSimpleName();

        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        String methodName = methodSignature.getName();
        return className + "." + methodName;
    }
}

package com.tank.springaop.aspect;

import com.tank.springaop.aspect.annotation.MethodAspectAnnotation;
import com.tank.springaop.aspect.processor.MethodAspectProcessor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author hzm
 * @version V1.0
 * @description: TODO
 * @Date 2019-09-09
 */
@Aspect
@Component
public class MethodAspect {

    /**
     * 方法连接点(处理被 @MethodAspectAnnotation 注解的方法)
     */
    @Pointcut("@annotation(com.tank.springaop.aspect.annotation.MethodAspectAnnotation)")
    public void methodPointcut() {
    }

    /**
     * 切入被 @MethodAspectAnno 注解的方法
     *
     * @param point                  连接点
     * @param methodAspectAnnotation 注解
     * @return 方法返回值
     * @throws Throwable 可能抛出的异常
     */
    @Around("methodPointcut() && @annotation(methodAspectAnnotation)")
    public Object doAround(ProceedingJoinPoint point, MethodAspectAnnotation methodAspectAnnotation) throws Throwable {
        MethodAspectProcessor processor = MethodAspectProcessor.from(methodAspectAnnotation);

        // 通过注解获取处理器
        if (!processor.isMatched(point)) {
            // 方法不匹配时的执行动作
            processor.onMismatch(point);
            // 直接执行该方法并返回结果
            return point.proceed();
        }
        long startTime = System.currentTimeMillis();
        Object result = null;
        try {
            // 执行目标方法
            result = point.proceed();
            // 正常返回
            processor.onReturn(point, result);
        } catch (Throwable throwable) {
            processor.onThrow(point, throwable);
            // 抛出异常的情况下，则构造一个返回值的实例，用于业务服务方法的返回
            result = processor.returnWhenThrowing(point, throwable);
        }
        // 切面结束
        processor.onComplete(point, startTime, result);
        return result;
    }
}

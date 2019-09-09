package com.tank.springaop.aspect.processor;

import com.tank.springaop.aspect.annotation.MethodAspectAnnotation;
import com.tank.springaop.aspect.annotation.SharableAnnotation;
import com.tank.springaop.aspect.processor.impl.MismatchMethodAspectProcessor;
import org.aspectj.lang.ProceedingJoinPoint;

import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @param <R> 方法返回值
 * @author hzm
 * @version V1.0
 * @description: TODO
 * @Date 2019-09-09
 */

public interface MethodAspectProcessor<R> {

    /**
     * 用于缓存被@Sharable 注解的MethodAspectProcessor (即线程安全可共享的)
     */
    Map<Class, MethodAspectProcessor> PROCESSOR_CACHE = new ConcurrentHashMap<>();

    /**
     * 是否是要处理的方法
     *
     * @param point 方法的连接点
     * @return 是要处理的方法返回 true，否则返回 false
     */
    boolean isMatched(ProceedingJoinPoint point);

    /**
     * 如果是不要处理的方法，执行的动作
     *
     * @param point 方法的连接点
     */
    default void onMismatch(ProceedingJoinPoint point) {
    }


    /**
     * 执行之前的动作
     *
     * @param point 方法的连接点
     */
    default void onBefore(ProceedingJoinPoint point) {
    }


    /**
     * 正常返回时，执行的动作
     *
     * @param point  方法的连接点
     * @param result 方法返回的结果
     */
    default void onReturn(ProceedingJoinPoint point, R result) {

    }

    /**
     * 抛出异常时，执行的动作
     *
     * @param point 方法的连接点
     * @param e     抛出的异常
     */
    void onThrow(ProceedingJoinPoint point, Throwable e);

    /**
     * 构建抛出异常时的返回值
     *
     * @param point 方法的连接点
     * @param e     抛出的异常
     * @return 抛出异常时的返回值
     */
    R returnWhenThrowing(ProceedingJoinPoint point, Throwable e);

    /**
     * 切面完成时，执行的动作
     *
     * @param point     方法的连接点
     * @param startTime 执行的开始时间
     * @param result    执行获得的结果
     */
    void onComplete(ProceedingJoinPoint point, long startTime, R result);


    /**
     * 获取 和被注解方法匹配的 切面处理器
     *
     * @param methodAspectAnnotation 注解
     * @return 匹配的切面处理器
     * @throws Exception 反射创建切面处理器时的异常
     */
    static MethodAspectProcessor from(MethodAspectAnnotation methodAspectAnnotation) throws IllegalAccessException, InstantiationException {
        Class<? extends MethodAspectProcessor> processorType = methodAspectAnnotation.value();
        SharableAnnotation sharableAnnotation = processorType.getAnnotation(SharableAnnotation.class);

        // processorType 上存在 @Sharable 注解，方法处理器可共享
        if (sharableAnnotation != null) {
            // 尝试先从缓存中获取
            MethodAspectProcessor processor = PROCESSOR_CACHE.get(processorType);
            // 缓存中存在对应的方法处理器
            if (processor != null) {
                return processor;
            }
        }

        // 如果指定的处理器类是接口或者抽象类
        if (processorType.isInterface() || Modifier.isAbstract(processorType.getModifiers())) {
            processorType = MismatchMethodAspectProcessor.class;
        }

        // 通过反射新建一个对应的方法处理器
        MethodAspectProcessor processor = processorType.newInstance();

        // 处理器可共享
        if (sharableAnnotation != null) {
            // 对方法处理器进行缓存
            PROCESSOR_CACHE.put(processorType, processor);
        }
        return processor;
    }

}

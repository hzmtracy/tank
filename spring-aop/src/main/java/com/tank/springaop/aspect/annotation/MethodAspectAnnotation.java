package com.tank.springaop.aspect.annotation;

import com.tank.springaop.aspect.processor.MethodAspectProcessor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description: TODO
 * @author hzm
 * @Date 2019-09-09
 * @version V1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MethodAspectAnnotation {

    Class<? extends MethodAspectProcessor> value();
}

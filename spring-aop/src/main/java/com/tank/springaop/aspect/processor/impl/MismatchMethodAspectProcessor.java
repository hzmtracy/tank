package com.tank.springaop.aspect.processor.impl;

import com.tank.springaop.aspect.annotation.SharableAnnotation;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @description: TODO
 * @author hzm
 * @Date 2019-09-09
 * @version V1.0
 */

@SharableAnnotation
public class MismatchMethodAspectProcessor<R> extends AbstractMethodAspectProcessor<R> {
    @Override
    public boolean isMatched(ProceedingJoinPoint point) {
        return false;
    }

    @Override
    public R returnWhenThrowing(ProceedingJoinPoint point, Throwable e) {
        return null;
    }

    @Override
    public void onComplete(ProceedingJoinPoint point, long startTime, R result) {

    }
}

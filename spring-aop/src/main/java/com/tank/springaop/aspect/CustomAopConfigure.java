package com.tank.springaop.aspect;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @description: TODO
 * @author hzm
 * @Date 2019-09-09
 * @version V1.0
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan("com.tank.springaop.aspect")
public class CustomAopConfigure {
}

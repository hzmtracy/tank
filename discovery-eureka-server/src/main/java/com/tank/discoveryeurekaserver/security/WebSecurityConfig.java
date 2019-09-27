package com.tank.discoveryeurekaserver.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @description: eureka安全策略，忽略eureka正常访问
 * @author hzm
 * @Date 2019/9/23
 * @version V1.0
 */

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().ignoringAntMatchers("/eureka/**");
        super.configure(httpSecurity);
    }
}

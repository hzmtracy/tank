package com.tank.springaop.common;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @description: swagger2 配置
 * @author hzm
 * @Date 2019-09-06
 * @version V1.0
 */

@Configuration
@EnableSwagger2
public class Swagger2 {
    
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.tank.spring.web.controller"))
                .paths(PathSelectors.any())
                .build();
        
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("spring aop 切面 restful api")
                .version("1.0")
                .description("aop")
                .build();
    }
}

package com.tank.springvalidator.code;


/**
 * @description: TODO
 * @author hzm
 * @Date 2019/9/30
 * @version V1.0
 */


import org.hibernate.validator.constraints.Length;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AliasFor;

import javax.validation.constraints.NotBlank;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@NotBlank()
//@Length(max = 1)
public @interface GroupAnnotation {
//    @AliasFor(value = NotBlank.class)
//    Object n() default {};
}

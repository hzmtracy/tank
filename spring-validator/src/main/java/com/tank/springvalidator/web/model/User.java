package com.tank.springvalidator.web.model;

import com.tank.springvalidator.code.GroupAnnotation;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @description: TODO
 * @author hzm
 * @Date 2019/9/29
 * @version V1.0
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 72190965946193833L;
    @NotBlank(message = "{required}")
    @Length(max = 2)
    private String name;

    @Email(message = "{invalid}")
    @Length
    private String email;

}

package com.diudiu.applet.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.groups.Default;

@Data
public class LoginDto {

    @NotEmpty(message = "短信UUID不能为空", groups = Default.class)
    private String smsUuid;

    @NotEmpty(message = "手机号不能为空", groups = Default.class)
    private String userTel;

    @NotEmpty(message = "验证码不能为空", groups = Default.class)
    private String smsCode;

}

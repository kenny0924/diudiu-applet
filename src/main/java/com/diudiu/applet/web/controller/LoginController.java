package com.diudiu.applet.web.controller;

import com.diudiu.applet.dto.LoginDto;
import com.diudiu.applet.dto.Message;
import com.diudiu.applet.entity.SmsCode;
import com.diudiu.applet.service.SmsService;
import com.diudiu.applet.utils.ObjectUtil;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.groups.Default;
import java.util.concurrent.TimeUnit;

@RestController
public class LoginController {
    @Autowired
    private SmsService smsService;

    /**
     * 登陆
     */
    public Message login(@RequestBody @Validated(Default.class) LoginDto dto) {
        SmsCode smsCode = smsService.selectBySmsUuid(dto.getSmsUuid());
        if (ObjectUtil.notEmpty(smsCode)
                &&
                TimeUnit.MILLISECONDS.toMinutes(DateTime.now().getMillis() -
                        smsCode.getCreatedTime().getTime()) <= 5) {
            // 验证
            if (dto.getSmsCode().equals(smsCode.getSmsCode())) {
                // 查询用户是否存在
            }
        }
        return Message.SMS_ERROR.newMessage("验证码错误或者过期");
    }
}

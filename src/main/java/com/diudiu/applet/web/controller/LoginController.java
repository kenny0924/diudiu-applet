package com.diudiu.applet.web.controller;

import com.diudiu.applet.dto.JWTInfo;
import com.diudiu.applet.dto.LoginDto;
import com.diudiu.applet.dto.Message;
import com.diudiu.applet.entity.SmsCode;
import com.diudiu.applet.entity.User;
import com.diudiu.applet.global.QP;
import com.diudiu.applet.service.JwtService;
import com.diudiu.applet.service.SmsService;
import com.diudiu.applet.service.UserService;
import com.diudiu.applet.utils.ObjectUtil;
import com.diudiu.applet.web.jwt.JwtHelper;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.groups.Default;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
public class LoginController {
    @Autowired
    private SmsService smsService;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtHelper jwtHelper;

    /**
     * 登陆
     */
    @PostMapping("/login")
    public Message login(@RequestBody @Validated(Default.class) LoginDto dto) throws Exception {
        SmsCode smsCode = smsService.selectBySmsUuid(dto.getSmsUuid());
        if (ObjectUtil.notEmpty(smsCode)
                &&
                TimeUnit.MILLISECONDS.toMinutes(DateTime.now().getMillis() -
                        smsCode.getCreateTime().getTime()) <= 5) {
            // 验证
            if (dto.getSmsCode().equals(smsCode.getSmsCode())) {
                // 查询用户是否存在
                Map<String, Object> params = QP.create().append("user_tel", dto.getUserTel());


                List<User> users = userService.selectByMap(params);
                JWTInfo info;
                if (ObjectUtil.notEmpty(users)) {
                    // 登陆
                    User user = users.get(0);
                    info = new JWTInfo(user.getId(), user.getName(), null, user.getUserTel());
                    info = jwtHelper.createAuthenticationToken(info);
                } else {
                    // 注册
                    info = userService.doCreateUserAndToken(dto);
                }
                info.setToken("Bearer " + info.getToken());
                return Message.successData(info);
            }
        }
        return Message.SMS_ERROR.newMessage("验证码错误或者过期");
    }
}

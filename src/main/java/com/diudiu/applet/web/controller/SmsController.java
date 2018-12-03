package com.diudiu.applet.web.controller;

import com.diudiu.applet.dto.Message;
import com.diudiu.applet.entity.SmsCode;
import com.diudiu.applet.service.SmsService;
import com.diudiu.applet.utils.ObjectUtil;
import com.diudiu.applet.utils.RandomUtil;
import com.diudiu.applet.utils.UUIDUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/sms")
public class SmsController {

    private static final Logger log = LoggerFactory.getLogger(SmsController.class);
    @Autowired
    private SmsService smsMessageService;

    /**
     * 发送短信
     *
     * @param tel 手机号码
     * @author Zhibin Liu
     * @time 9/22/17 11:29
     */
    @RequestMapping(value = "/sendSms", method = RequestMethod.GET)
    public ResponseEntity<Message> sendSms(@RequestParam String tel,
                                           @RequestParam Integer t) throws Exception {

        Integer random = RandomUtil.generateRandom(4);

        // TODO: 12/3/18 发送短信逻辑
//        boolean success = commonQueue.sendSms(String.valueOf(random), tel);
        SmsCode userSmsCode = new SmsCode();
        userSmsCode.setSmsCode(random.toString());
        userSmsCode.setCreatedTime(new Date());
        userSmsCode.setUserTel(tel);
        userSmsCode.setUuid(UUIDUtils.generateUuid());
        // 保存记录
        smsMessageService.doCreate(userSmsCode);
        return ResponseEntity.ok(Message.successData(userSmsCode.getUuid()));
    }
}

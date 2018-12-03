package com.diudiu.applet.service;

import com.diudiu.applet.entity.SmsCode;

/**
 * @author 刘智斌
 * @version 0.1
 * @time 12/3/18
 * @since 0.1
 */
public interface SmsService {

    /**
     * 创建
     */
    void doCreate(SmsCode smsCode);

    /**
     * 根据UUID查询短信验证码
     */
    SmsCode selectBySmsUuid(String smsUuid);
}

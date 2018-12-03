package com.diudiu.applet.service.impl;

import com.diudiu.applet.entity.SmsCode;
import com.diudiu.applet.mapper.SmsMapper;
import com.diudiu.applet.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 刘智斌
 * @version 0.1
 * @time 12/3/18
 * @since 0.1
 */
@Service
@Transactional
public class SmsServiceImpl implements SmsService {

    @Autowired
    private SmsMapper smsMapper;


    @Override
    public void doCreate(SmsCode smsCode) {
        smsMapper.insert(smsCode);
    }

    @Override
    public SmsCode selectBySmsUuid(String smsUuid) {
        return smsMapper.selectBySmsUuid(smsUuid);
    }
}

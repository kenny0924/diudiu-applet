package com.diudiu.applet.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.diudiu.applet.entity.SmsCode;
import org.apache.ibatis.annotations.Select;

/**
 * @author 刘智斌
 * @version 0.1
 * @time 12/3/18
 * @since 0.1
 */
public interface SmsMapper extends BaseMapper<SmsCode> {


    /**
     * 根据UUID查询短信验证码
     */
    @Select("")
    SmsCode selectBySmsUuid(String smsUuid);
}

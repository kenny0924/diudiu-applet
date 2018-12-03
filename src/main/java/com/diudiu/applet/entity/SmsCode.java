package com.diudiu.applet.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("user_sms_code")
public class SmsCode {

    /**
     * 主键ID
     **/
    private Long id;

    /**
     * 短信UUID
     */
    private String uuid;

    /**
     * 验证码
     **/
    @TableField("sms_code")
    private String smsCode;

    /**
     * 手机号
     **/
    @TableField("user_tel")
    private String userTel;

    /**
     * 创建时间
     **/
    private Date createdTime;

}

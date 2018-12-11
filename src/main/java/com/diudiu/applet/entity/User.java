package com.diudiu.applet.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author 刘智斌
 * @version 0.1
 * @time 12/3/18
 * @since 0.1
 */
@Data
@TableName("users")
public class User {
    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("user_tel")
    private String userTel;

    private String name;

    // 会员级别 0:不是会员 1:普通会员 2:商户会员 商户会员要在后台管理录入 与当前用户绑定
    @TableField("vip_level")
    private Integer vipLevel;

    // 丢丢币
    @TableField("diudiu_coin")
    private Integer diudiuCoin;

    // 头像
    @TableField("head_portrait")
    private String headPortrait;

    @TableField("wc_open_id")
    private String wcOpenId;

    @TableField("create_time")
    private Date createTime;

    @TableField("modify_time")
    private Date modifyTime;
}

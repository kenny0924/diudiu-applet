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

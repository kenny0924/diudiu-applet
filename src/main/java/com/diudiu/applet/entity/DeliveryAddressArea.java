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
 * @time 12/2/18
 * @since 0.1
 */
@Data
@TableName("delivery_address_area")
public class DeliveryAddressArea {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String pid;

    private String name;

    private String pname;

    @TableField("create_date")
    private Date createDate;

    @TableField("create_date")
    private Date updateate;

    @TableField("del_flag")
    private Integer delFlag;

    private Character type;

}

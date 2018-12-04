package com.diudiu.applet.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @author 刘智斌
 * @version 0.1
 * @time 12/2/18
 * @since 0.1
 */
@Data
public class RetailStore {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("store_name")
    private String storeName;

    @TableField("store_tel")
    private String storeTel;

    @TableField("province_id")
    private Long provinceId;

    @TableField("city_id")
    private Long cityId;

    private String addr;

    @TableField("business_hours")
    private String businessHours;

    @TableField("create_time")
    private Date createTime;

    @TableField("modify_time")
    private Date modifyTime;
}

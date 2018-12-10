package com.diudiu.applet.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author 刘智斌
 * @version 0.1
 * @time 12/9/18
 * @since 0.1
 */
@Data
@TableName("banner")
public class Banner {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String title;

    @TableField("img_url")
    private String imgUrl;

    @TableField("tag_id")
    @JsonProperty("path")
    private Long tagId;

    // 0:关闭 1:开启
    private Integer status;

    @TableField("create_time")
    private Date createTime;
}

package com.diudiu.applet.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@Data
@TableName("images")
public class Image {

    @JsonIgnore
    private Long id;

    @TableField("foreign_id")
    @JsonIgnore
    private Long foreignId;

    @JsonIgnore
    private Integer type;

    private String source;

    @TableField("create_time")
    @JsonIgnore
    private Date createTime;
}

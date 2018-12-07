package com.diudiu.applet.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.beans.Transient;
import java.util.Date;
import java.util.List;

@Data
@TableName("goods_type")
public class GoodsType {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    @TableField(exist = false)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<GoodsType> options;

    @JsonIgnore
    private Long pid;

    @JsonIgnore
    private Integer level;

    @JsonIgnore
    @TableField("create_time")
    private Date createTime;
}

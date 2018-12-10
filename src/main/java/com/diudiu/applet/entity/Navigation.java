package com.diudiu.applet.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author 刘智斌
 * @version 0.1
 * @time 12/9/18
 * @since 0.1
 */
@Data
@TableName("navigation")
public class Navigation {
    public static final Navigation EMPTY = new Navigation();

    private Long id;

    private String title;

    @TableField(exist = false)
    private List<Image> images;

    @JsonIgnore
    @TableField("tag_id")
    private Long tagId;

    @JsonIgnore
    private Integer status;

    @JsonIgnore
    @TableField("create_time")
    private Date createTime;

    @JsonIgnore
    @TableField("modify_time")
    private Date modifyTime;
}

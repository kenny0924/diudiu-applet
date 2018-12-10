package com.diudiu.applet.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author 刘智斌
 * @version 0.1
 * @time 12/9/18
 * @since 0.1
 */
@Data
@TableName("tag")
public class Tag {

    private Long id;

    private String name;

    @TableField("is_dir")
    private Integer isDir;

    @TableField("create_time")
    private Date createTime;
}

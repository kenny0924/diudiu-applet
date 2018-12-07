package com.diudiu.applet.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@Data
@TableName("goods_content")
public class GoodsContent {

    @TableId(type = IdType.AUTO)
    @JsonIgnore
    private Long id;

    @TableField("goods_id")
    @JsonIgnore
    private Long goodsId;

    private Integer type;

    private String paths;

    @TableField("video_url")
    private String videoUrl;

    @TableField("cover_url")
    private String coverUrl;

    @TableField("create_time")
    @JsonIgnore
    private Date createTime;

}

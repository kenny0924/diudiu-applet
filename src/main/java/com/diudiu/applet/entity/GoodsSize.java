package com.diudiu.applet.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@Data
@TableName("goods_size")
public class GoodsSize {

    private Long id;

    @JsonIgnore
    @TableField("goods_id")
    private Long goodsId;

    private String name;

    // 有效库存
    @TableField("available_stock")
    private Integer availableStock;

    // 尺码
    private String size;

    @JsonIgnore
    @TableField("create_time")
    private Date createTime;
}

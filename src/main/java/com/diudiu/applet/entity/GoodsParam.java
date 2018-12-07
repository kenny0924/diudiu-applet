package com.diudiu.applet.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("goods_param")
public class GoodsParam {

    private Long id;

    @TableField("goods_id")
    private String goodsId;

    private String name;

    private String value;

}

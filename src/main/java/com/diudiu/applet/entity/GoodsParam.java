package com.diudiu.applet.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("goods_param")
public class GoodsParam {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("goods_id")
    private String goodsId;

    private String name;

    private String value;

}

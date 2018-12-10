package com.diudiu.applet.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 刘智斌
 * @version 0.1
 * @time 12/9/18
 * @since 0.1
 */
@Data
@TableName("goods_recommend")
public class GoodsRecommend {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("goods_id")
    private Long goodsId;

    @TableField("recommend_id")
    private Long recommendId;
}

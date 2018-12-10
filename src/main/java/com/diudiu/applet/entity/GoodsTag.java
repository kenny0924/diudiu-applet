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
@TableName("goods_tag")
public class GoodsTag {

    private Long id;

    @TableField("goods_id")
    private Long goodsId;

    @TableField("tag_id")
    private Long tagId;

    @TableField("create_time")
    private Date createTime;
}

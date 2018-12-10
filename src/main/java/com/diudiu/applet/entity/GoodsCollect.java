package com.diudiu.applet.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.util.Date;

/**
 * @author 刘智斌
 * @version 0.1
 * @time 12/7/18
 * @since 0.1
 */
@Data
@TableName("goods_collect")
public class GoodsCollect {

    @TableId(type = IdType.AUTO)
    private Long id;

    @NotNull(groups = Default.class, message = "商品ID不能为空")
    @TableField("goods_id")
    private Long goodsId;

    @TableField("user_id")
    private Long userId;

    @TableField("create_time")
    private Date createTime;


}

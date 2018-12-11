package com.diudiu.applet.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.diudiu.applet.utils.ObjectUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.scenario.effect.impl.prism.PrRenderInfo;
import lombok.Data;

import java.util.*;

@Data
@TableName("goods")
public class Goods {
    /*{
        "price":599,
            "memberPrice":599,
            "diudiuPrice":100000,
            "image":"http://charleskeith.oss-cn-shanghai.aliyuncs.com/upload/image/201801/fabc8847-3324-4b52-9a04-b872c6cf1eab-medium.jpg",
            "name":"铭牌挂饰都市手提包",
            "id":7579,
            "sn":"CK2-50270047"
    },*/

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    // 商品的产品号 一个商品可以有多种颜色/尺寸 但是产品号是一样的
    private String sn;

    @TableField("real_price")
    private Double price;

    @TableField("discount_price")
    private Double memberPrice;

    @TableField("diudiu_price")
    private Double diudiuPrice;

    private String image;

    @JsonIgnore
    @TableField("sale_vol")
    private Integer saleVol;

    @JsonIgnore
    @TableField("goods_type")
    private String goodsTypeIds;

    // 商品Banner图片列表
    @TableField(exist = false)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Image> goodsBannerImages;

    // 商品内容
    @TableField(exist = false)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<GoodsContent> goodsContent;

    // 商品尺码
    @TableField(exist = false)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<GoodsSize> size;

    // 商品参数
    @TableField(exist = false)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Map<String, String>> parameters;

    @JsonIgnore
    @TableField("create_time")
    private Date createTime;

    @JsonIgnore
    @TableField("modify_time")
    private Date modifyTime;

    @JsonIgnore
    public void setToParams(List<GoodsParam> goodsParams) {
        if (ObjectUtil.notEmpty(goodsParams)) {
            List<Map<String, String>> maps = new ArrayList<>(goodsParams.size());
            goodsParams.stream().forEach((gp) -> {
                Map<String, String> map = new HashMap<>();
                map.put(gp.getName(), gp.getValue());
                maps.add(map);
            });
            this.parameters = maps;
        }
    }
}

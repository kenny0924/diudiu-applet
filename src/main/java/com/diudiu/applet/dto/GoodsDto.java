package com.diudiu.applet.dto;

import com.diudiu.applet.entity.Goods;
import com.diudiu.applet.entity.GoodsType;
import lombok.Data;

import java.util.List;

@Data
public class GoodsDto {

    private List<GoodsType> filters;

    private List<Goods> goods;

}

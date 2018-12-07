package com.diudiu.applet.service;

import com.diudiu.applet.entity.GoodsContent;
import com.diudiu.applet.entity.GoodsParam;

import java.util.List;

public interface GoodsParamService {

    /**
     *
     * 根据商品ID查询
     * @return
     */
    List<GoodsParam> selectByGoodsId(long goodsId);
}

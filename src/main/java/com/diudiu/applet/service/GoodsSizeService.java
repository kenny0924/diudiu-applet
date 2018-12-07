package com.diudiu.applet.service;

import com.diudiu.applet.entity.GoodsContent;
import com.diudiu.applet.entity.GoodsSize;

import java.util.List;

public interface GoodsSizeService {


    /**
     *
     * 根据商品ID查询
     * @return
     */
    List<GoodsSize> selectByGoodsId(long goodsId);

}

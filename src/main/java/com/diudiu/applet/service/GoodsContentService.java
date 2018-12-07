package com.diudiu.applet.service;

import com.diudiu.applet.entity.GoodsContent;

import java.util.List;

public interface GoodsContentService {

    /**
     *
     * 根据商品ID查询
     * @return
     */
    List<GoodsContent> selectByGoodsId(long goodsId);
}

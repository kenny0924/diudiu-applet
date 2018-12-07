package com.diudiu.applet.service;

import com.diudiu.applet.entity.Goods;
import com.diudiu.applet.global.QueryParams;

import java.util.List;

public interface GoodsService extends BaseService{

    /**
     * 根据ID查询商品
     * @param id
     * @return
     */
    Goods selectById(long id);

    /**
     * 商品所有关联数据
     */
    Goods selectDetailById(long id);

    /**
     * 根据条件查询商品
     */
    List<Goods> selectByMap(QueryParams queryParams);
}

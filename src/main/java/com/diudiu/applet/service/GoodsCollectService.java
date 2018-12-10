package com.diudiu.applet.service;

import com.diudiu.applet.entity.Goods;
import com.diudiu.applet.entity.GoodsCollect;

import java.util.List;

/**
 * @author 刘智斌
 * @version 0.1
 * @time 12/7/18
 * @since 0.1
 */
public interface GoodsCollectService {

    List<Goods> selectByUserId(long userId);

    GoodsCollect selectByUserIdAndGoodsId(Long userId, Long goodsId);

    Integer insert(GoodsCollect create);

    GoodsCollect selectById(Long id);

    Integer deleteById(Long id);
}

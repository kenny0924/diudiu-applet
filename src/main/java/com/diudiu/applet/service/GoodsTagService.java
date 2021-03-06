package com.diudiu.applet.service;

import com.diudiu.applet.entity.Goods;
import com.diudiu.applet.entity.GoodsTag;

import java.util.List;

/**
 * @author 刘智斌
 * @version 0.1
 * @time 12/9/18
 * @since 0.1
 */
public interface GoodsTagService {

    List<Goods> selectByTagId(long tagId);
}

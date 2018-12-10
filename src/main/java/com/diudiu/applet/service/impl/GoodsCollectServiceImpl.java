package com.diudiu.applet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.diudiu.applet.entity.Goods;
import com.diudiu.applet.entity.GoodsCollect;
import com.diudiu.applet.global.QP;
import com.diudiu.applet.mapper.GoodsCollectMapper;
import com.diudiu.applet.service.GoodsCollectService;
import com.diudiu.applet.service.GoodsService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Wrapper;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 刘智斌
 * @version 0.1
 * @time 12/7/18
 * @since 0.1
 */
@Service
@Transactional
public class GoodsCollectServiceImpl implements GoodsCollectService {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private GoodsCollectMapper goodsCollectMapper;


    @Override
    public List<Goods> selectByUserId(long userId) {
        LambdaQueryWrapper<GoodsCollect> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(GoodsCollect::getUserId, userId);
        List<GoodsCollect> goodsCollects = goodsCollectMapper.selectList(wrapper);
        List<Long> goodsIds = goodsCollects.stream().map(GoodsCollect::getGoodsId)
                .collect(Collectors.toList());

        return goodsService.selectByIds(goodsIds);
    }

    @Override
    public GoodsCollect selectByUserIdAndGoodsId(Long userId, Long goodsId) {
        LambdaQueryWrapper<GoodsCollect> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(GoodsCollect::getGoodsId, goodsId)
                .eq(GoodsCollect::getUserId, userId);
        return goodsCollectMapper.selectOne(wrapper);
    }

    @Override
    public Integer insert(GoodsCollect create) {
        return goodsCollectMapper.insert(create);
    }

    @Override
    public GoodsCollect selectById(Long id) {
        return goodsCollectMapper.selectById(id);
    }

    @Override
    public Integer deleteById(Long id) {
        return goodsCollectMapper.deleteById(id);
    }
}

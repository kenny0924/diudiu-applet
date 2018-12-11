package com.diudiu.applet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.diudiu.applet.constants.Sort;
import com.diudiu.applet.constants.Type;
import com.diudiu.applet.entity.Goods;
import com.diudiu.applet.global.QueryParams;
import com.diudiu.applet.mapper.GoodsMapper;
import com.diudiu.applet.service.*;
import com.diudiu.applet.utils.ObjectUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GoodsServiceImpl extends BaseServiceImpl<Goods> implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private GoodsParamService goodsParamService;
    @Autowired
    private GoodsSizeService goodsSizeService;
    @Autowired
    private GoodsContentService goodsContentService;
    @Autowired
    private ImageService imageService;


    @Override
    public Goods selectById(long id) {
        return goodsMapper.selectById(id);
    }

    @Override
    public Goods selectDetailById(long id) {
        Goods goods = selectById(id);
        if (goods != null) {
            goods.setToParams(goodsParamService.selectByGoodsId(id));
            goods.setGoodsContent(goodsContentService.selectByGoodsId(id));
            goods.setGoodsBannerImages(imageService.selectByForeignAndType(id, Type.ImageType.GOODS));
            goods.setSize(goodsSizeService.selectByGoodsId(id));
        }
        return goods;
    }

    @Override
    public List<Goods> selectByMap(QueryParams queryParams) {
        LambdaQueryWrapper<Goods> wrapper = new LambdaQueryWrapper<>();
        if (queryParams.get("goodsTypes") != null) {
            List<Integer> goodsTypes = (List<Integer>) queryParams.get("goodsTypes");
            wrapper.in(Goods::getGoodsTypeIds, goodsTypes);
        }
        if (queryParams.get("sortType") != null) {
            Integer sortType = (Integer) queryParams.get("sortType");
            if (Sort.PRICE_LOW_TO_UP.id.equals(sortType)) {
                wrapper.orderBy(true, true, Goods::getPrice);
            } else if (Sort.PRICE_UP_TO_LOW.id.equals(sortType)) {
                wrapper.orderBy(true, false, Goods::getPrice);
            } else if (Sort.SELL_UP_TO_LOW.id.equals(sortType)) {
                wrapper.orderBy(true, false, Goods::getSaleVol);
            }
        }
        return startPage(queryParams, () -> goodsMapper.selectList(wrapper));
    }

    @Override
    public List<Goods> selectByIds(List<Long> goodsIds) {
        if (ObjectUtil.isEmpty(goodsIds)) return Collections.EMPTY_LIST;
        return goodsMapper.selectBatchIds(goodsIds);
    }
}
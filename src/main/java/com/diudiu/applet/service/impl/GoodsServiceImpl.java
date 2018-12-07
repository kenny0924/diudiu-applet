package com.diudiu.applet.service.impl;

import com.diudiu.applet.constants.Type;
import com.diudiu.applet.entity.Goods;
import com.diudiu.applet.global.QueryParams;
import com.diudiu.applet.mapper.GoodsMapper;
import com.diudiu.applet.service.*;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        return startPage(queryParams, () -> goodsMapper.selectByMap(queryParams));
    }
}
package com.diudiu.applet.service.impl;

import com.diudiu.applet.entity.GoodsContent;
import com.diudiu.applet.global.QP;
import com.diudiu.applet.mapper.GoodsContentMapper;
import com.diudiu.applet.service.GoodsContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GoodsContentServiceImpl implements GoodsContentService {

    @Autowired
    private GoodsContentMapper goodsContentMapper;

    @Override
    public List<GoodsContent> selectByGoodsId(long goodsId) {
        return goodsContentMapper.selectByMap(QP.create("goods_id", goodsId));
    }
}
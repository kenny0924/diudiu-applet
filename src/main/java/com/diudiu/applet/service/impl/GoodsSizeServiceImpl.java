package com.diudiu.applet.service.impl;

import com.diudiu.applet.entity.GoodsSize;
import com.diudiu.applet.global.QP;
import com.diudiu.applet.mapper.GoodsSizeMapper;
import com.diudiu.applet.service.GoodsSizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GoodsSizeServiceImpl implements GoodsSizeService {

    @Autowired
    private GoodsSizeMapper goodsSizeMapper;

    @Override
    public List<GoodsSize> selectByGoodsId(long goodsId) {
        return goodsSizeMapper.selectByMap(QP.create("goods_id", goodsId));
    }
}

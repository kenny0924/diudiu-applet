package com.diudiu.applet.service.impl;

import com.diudiu.applet.entity.GoodsParam;
import com.diudiu.applet.global.QP;
import com.diudiu.applet.mapper.GoodsParamMapper;
import com.diudiu.applet.service.GoodsParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GoodsParamServiceImpl implements GoodsParamService {

    @Autowired
    private GoodsParamMapper goodsParamMapper;

    @Override
    public List<GoodsParam> selectByGoodsId(long goodsId) {
        return goodsParamMapper.selectByMap(QP.create("goods_id", goodsId));
    }
}

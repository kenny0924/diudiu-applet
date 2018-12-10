package com.diudiu.applet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.diudiu.applet.entity.Goods;
import com.diudiu.applet.entity.GoodsTag;
import com.diudiu.applet.mapper.GoodsTagMapper;
import com.diudiu.applet.service.GoodsService;
import com.diudiu.applet.service.GoodsTagService;
import com.diudiu.applet.utils.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 刘智斌
 * @version 0.1
 * @time 12/9/18
 * @since 0.1
 */
@Service
@Transactional
public class GoodsTagServiceImpl implements GoodsTagService {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private GoodsTagMapper goodsTagMapper;

    @Override
    public List<Goods> selectByTagId(long tagId) {

        LambdaQueryWrapper<GoodsTag> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(GoodsTag::getTagId, tagId);
        List<GoodsTag> tags = goodsTagMapper.selectList(wrapper);
        List<Long> goodsIds = tags.stream().map(GoodsTag::getGoodsId)
                .collect(Collectors.toList());
        if (ObjectUtil.notEmpty(goodsIds))
            return goodsService.selectByIds(goodsIds);
        return Collections.emptyList();
    }
}

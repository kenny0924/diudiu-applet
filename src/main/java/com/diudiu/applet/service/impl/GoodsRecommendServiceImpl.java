package com.diudiu.applet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.diudiu.applet.entity.GoodsRecommend;
import com.diudiu.applet.mapper.GoodsRecommendMapper;
import com.diudiu.applet.service.GoodsRecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 刘智斌
 * @version 0.1
 * @time 12/9/18
 * @since 0.1
 */
@Service
@Transactional
public class GoodsRecommendServiceImpl implements GoodsRecommendService {
    @Autowired
    private GoodsRecommendMapper goodsRecommendMapper;

    @Override
    public List<GoodsRecommend> selectByRecommendId(long recommendId) {
        LambdaQueryWrapper<GoodsRecommend>
                wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(GoodsRecommend::getRecommendId, recommendId);
        return goodsRecommendMapper.selectList(wrapper);
    }
}

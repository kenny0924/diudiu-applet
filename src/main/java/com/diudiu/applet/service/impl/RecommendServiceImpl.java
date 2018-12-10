package com.diudiu.applet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.diudiu.applet.entity.Goods;
import com.diudiu.applet.entity.GoodsRecommend;
import com.diudiu.applet.entity.Navigation;
import com.diudiu.applet.entity.Recommend;
import com.diudiu.applet.mapper.GoodsRecommendMapper;
import com.diudiu.applet.mapper.RecommendMapper;
import com.diudiu.applet.service.GoodsRecommendService;
import com.diudiu.applet.service.GoodsService;
import com.diudiu.applet.service.RecommendService;
import com.diudiu.applet.utils.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class RecommendServiceImpl implements RecommendService {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private RecommendMapper recommendMapper;
    @Autowired
    private GoodsRecommendService goodsRecommendService;

    @Override
    public Recommend selectBy(int status) {
        LambdaQueryWrapper<Recommend> wrapper = new LambdaQueryWrapper();
        wrapper.eq(Recommend::getStatus, status);

        List<Recommend> recommends = recommendMapper.selectList(wrapper);
        if (ObjectUtil.notEmpty(recommends)) {
            Recommend recommend = recommends.get(0);
            List<GoodsRecommend> goodsRecommends = goodsRecommendService.selectByRecommendId(recommend.getId());
            List<Long> goodsIds = goodsRecommends.stream()
                    .map(GoodsRecommend::getGoodsId)
                    .collect(Collectors.toList());
            recommend.setGoods(goodsService.selectByIds(goodsIds));
            return recommend;
        }
        return Recommend.EMPTY;
    }
}

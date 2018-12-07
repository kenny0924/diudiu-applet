package com.diudiu.applet.service.impl;

import com.diudiu.applet.entity.GoodsType;
import com.diudiu.applet.mapper.GoodsTypeMapper;
import com.diudiu.applet.service.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class GoodsTypeServiceImpl implements GoodsTypeService {

    @Autowired
    private GoodsTypeMapper goodsTypeMapper;

    @Override
    public List<GoodsType> selectTypeAndSubByMap(Map<String, Object> params) {
        List<GoodsType> goodsTypes = goodsTypeMapper.selectByMap(params);
        Map<Long, List<GoodsType>> map = goodsTypes.stream().collect(Collectors.groupingBy(GoodsType::getPid));

        List<GoodsType> topGoodsType = map.get(0l);
        for (Map.Entry<Long, List<GoodsType>> entry : map.entrySet()) {
            if (entry.getKey().equals(0l)) continue; // filter top

            for (GoodsType type : topGoodsType) {
                if (entry.getKey().equals(type.getId())) {
                    type.setOptions(entry.getValue());
                    break;
                }
            }
        }
        return topGoodsType;
    }
}

package com.diudiu.applet.web.controller;

import com.diudiu.applet.dto.GoodsDto;
import com.diudiu.applet.dto.Message;
import com.diudiu.applet.entity.Goods;
import com.diudiu.applet.entity.GoodsType;
import com.diudiu.applet.global.CurrentUserHolder;
import com.diudiu.applet.global.QP;
import com.diudiu.applet.global.QueryParams;
import com.diudiu.applet.service.GoodsCollectService;
import com.diudiu.applet.service.GoodsService;
import com.diudiu.applet.service.GoodsTypeService;
import com.diudiu.applet.web.annotation.TokenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private GoodsTypeService goodsTypeService;
    @Autowired
    private GoodsCollectService goodsCollectService;

    @GetMapping("/all")
    public Message queryGoodsAll() {
        List<GoodsType> goodsTypes = goodsTypeService.selectTypeAndSubByMap(new HashMap<>());
        GoodsDto dto = new GoodsDto();
        dto.setFilters(goodsTypes);
        dto.setGoods(goodsService.selectByMap(QP.EMPTY));
        return Message.successData(dto);
    }

    @GetMapping("/{id}")
    public Message queryById(@PathVariable Long id) {
        Goods goods = goodsService.selectDetailById(id);
        return Message.successData(goods);
    }

    @GetMapping
    public Message queryGoods(
            @RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
            @RequestParam(required = false) Integer sortType,
            @RequestParam(required = false) List<Integer> goodsTypes
    ) {
        QueryParams qp = QP.create()
                .pageNum(pageNum).pageSize(pageSize)
                .append("sortType", sortType)
                .append("goodsTypes", goodsTypes);
        List<Goods> goods = goodsService.selectByMap(qp);
        return Message.page(goods);
    }


}
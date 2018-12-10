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

    public enum Sort {
        DEF(1, "默认排序"),
        PRICE_LOW_TO_UP(2, "价格从低到高"),
        PRICE_UP_TO_LOW(3, "价格从高到底"),
        SELL_UP_TO_LOW(4, "销量从高到底");
        private Integer id;

        private String name;

        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        Sort(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        public static List<Map<String, Object>> toList() {
            return Arrays.asList(Sort.values()).stream()
                    .map((s) -> {
                        Map<String, Object> map = new LinkedHashMap<>();
                        map.put("id", s.id);
                        map.put("name", s.name);
                        return map;
                    }).collect(Collectors.toList());
        }
    }

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
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize
    ) {
        QueryParams qp = QP.create()
                .pageNum(pageNum).pageSize(pageSize);
        List<Goods> goods = goodsService.selectByMap(qp);
        return Message.page(goods);
    }


}
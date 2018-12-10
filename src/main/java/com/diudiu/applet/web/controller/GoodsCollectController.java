package com.diudiu.applet.web.controller;

import com.diudiu.applet.dto.Message;
import com.diudiu.applet.entity.Goods;
import com.diudiu.applet.entity.GoodsCollect;
import com.diudiu.applet.global.CurrentUserHolder;
import com.diudiu.applet.service.GoodsCollectService;
import com.diudiu.applet.service.GoodsService;
import com.diudiu.applet.web.annotation.TokenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author 刘智斌
 * @version 0.1
 * @time 12/8/18
 * @since 0.1
 */
@RestController
@RequestMapping("/goods/collect")
public class GoodsCollectController {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private GoodsCollectService goodsCollectService;

    /**
     * 新增收藏
     */
    @TokenApi
    @PostMapping
    public Message postCollect(@RequestBody GoodsCollect create) {
        GoodsCollect old = goodsCollectService.selectByUserIdAndGoodsId(CurrentUserHolder.getUserId(), create.getGoodsId());
        if (old != null) return Message.DUP_COLLECT;
        Goods goods = goodsService.selectById(create.getGoodsId());
        if (goods == null) return Message.GOODS_NOT_EXISTS;

        create.setUserId(CurrentUserHolder.getUserId());
        create.setCreateTime(new Date());
        goodsCollectService.insert(create);
        return Message.successData(create);
    }

    /**
     * 删除收藏商品失败
     */
    @TokenApi
    @PostMapping("/{id}")
    public Message delCollect(@PathVariable Long id) {
        GoodsCollect old = goodsCollectService.selectById(id);
        if (old != null && old.getUserId().equals(CurrentUserHolder.getUserId())) {
            goodsCollectService.deleteById(id);
            return Message.successData(old);
        }
        return Message.DEL_COLLECT_ERROR;
    }

    /**
     * 查询收藏商品
     */
    @TokenApi
    @GetMapping
    public Message goodsCollect() {
        List<Goods> goods = goodsCollectService.selectByUserId(CurrentUserHolder.getUserId());
        return Message.successData(goods);
    }
}
package com.diudiu.applet.service.impl;

import com.diudiu.applet.entity.Goods;
import com.diudiu.applet.global.QP;
import com.diudiu.applet.service.GoodsService;
import com.diudiu.applet.utils.JsonUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsServiceImplTest {

    @Autowired
    GoodsService goodsService;

    @Test
    public void selectById() {
        Goods goods = goodsService.selectById(1l);
        assertNotNull(goods);
    }

    @Test
    public void selectAllById() {
        Goods goods = goodsService.selectDetailById(1);
        assertNotNull(goods);
        System.out.println(JsonUtils.object2Json(goods));
    }

    @Test
    public void selectByMap() {
        List<Goods> goods = goodsService.selectByMap(QP.create());
        assertNotNull(goods);
    }
}
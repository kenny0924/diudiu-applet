package com.diudiu.applet.service;

import com.diudiu.applet.entity.Goods;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author 刘智斌
 * @version 0.1
 * @time 12/7/18
 * @since 0.1
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsCollectServiceTest {

    @Autowired
    GoodsCollectService goodsCollectService;

    @Test
    public void selectByUserId() {
        List<Goods> goods = goodsCollectService.selectByUserId(1);
        assertNotNull(goods);
    }
}
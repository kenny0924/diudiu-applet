package com.diudiu.applet.service;

import com.diudiu.applet.entity.GoodsSize;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsSizeServiceTest {

    @Autowired
    GoodsSizeService goodsSizeService;

    @Test
    public void selectByGoodsId() {
        List<GoodsSize> goodsSizes = goodsSizeService.selectByGoodsId(1);
        assertNotNull(goodsSizes);
    }
}
package com.diudiu.applet.service;

import com.diudiu.applet.entity.GoodsParam;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsParamServiceTest {

    @Autowired
    GoodsParamService goodsParamService;
    @Test
    public void selectByGoodsId() {
        List<GoodsParam> goodsParams = goodsParamService.selectByGoodsId(1);
        assertNotNull(goodsParams);
    }

}
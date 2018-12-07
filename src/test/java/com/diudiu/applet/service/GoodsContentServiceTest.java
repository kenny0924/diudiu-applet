package com.diudiu.applet.service;

import com.diudiu.applet.entity.GoodsContent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsContentServiceTest {

    @Autowired
    GoodsContentService goodsContentService;

    @Test
    public void selectByGoodsId() {
        List<GoodsContent> contents = goodsContentService.selectByGoodsId(1l);
        assertNotNull(contents);
    }
}
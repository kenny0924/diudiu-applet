package com.diudiu.applet.service;

import com.diudiu.applet.dto.GoodsDto;
import com.diudiu.applet.entity.GoodsType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsTypeServiceTest {


    @Autowired
    GoodsTypeService goodsTypeService;

    @Test
    public void selectTypeAndSubByMap() {
        List<GoodsType> goodsTypes = goodsTypeService.selectTypeAndSubByMap(new HashMap<>());

    }
}
package com.diudiu.applet.service;

import com.diudiu.applet.entity.RetailStore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.Assert.*;

/**
 * @author 刘智斌
 * @version 0.1
 * @time 12/2/18
 * @since 0.1
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RetailStoreServiceTest {

    @Autowired
    RetailStoreService retailStoreService;

    @Test
    public void selectByMap() {
        Map<String, Object> map = new ConcurrentHashMap<>();
        //map.put("province_id", 4);
        List<RetailStore> retailStores = retailStoreService.selectByMap(map);
        assertNotNull(retailStores);
    }
}
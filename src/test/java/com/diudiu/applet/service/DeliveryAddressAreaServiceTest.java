package com.diudiu.applet.service;

import com.diudiu.applet.entity.DeliveryAddressArea;
import com.diudiu.applet.entity.Province;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

/**
 * @author 刘智斌
 * @version 0.1
 * @time 12/2/18
 * @since 0.1
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DeliveryAddressAreaServiceTest {

    @Autowired
    DeliveryAddressAreaService deliveryAddressAreaService;

    @Test
    public void query() {
        Map<String, Object> map = new ConcurrentHashMap<>();
        map.put("pid", 10);
        List<DeliveryAddressArea> addressAreas = deliveryAddressAreaService.selectByMap(map);
        assertNotNull(addressAreas);
    }

    @Test
    public void selectRetailStoreArea() {
        List<Province> provinces = deliveryAddressAreaService.selectRetailStoreArea();
        assertNotNull(provinces);
    }

    @Test
    public void selectByNames() {
        List<DeliveryAddressArea> areas = deliveryAddressAreaService.selectByNames("上海", "上海市");
        Map m = areas.stream()
                .collect(Collectors.groupingBy(DeliveryAddressArea::getType));
        assertNotNull(areas);
    }
}
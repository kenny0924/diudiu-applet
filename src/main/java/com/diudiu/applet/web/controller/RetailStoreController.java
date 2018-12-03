package com.diudiu.applet.web.controller;

import com.diudiu.applet.dto.Message;
import com.diudiu.applet.entity.Province;
import com.diudiu.applet.entity.RetailStore;
import com.diudiu.applet.service.DeliveryAddressAreaService;
import com.diudiu.applet.service.RetailStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 刘智斌
 * @version 0.1
 * @time 12/2/18
 * @since 0.1
 */
@RestController
@RequestMapping("/retail/store")
public class RetailStoreController {



    @Autowired
    private RetailStoreService retailStoreService;
    @Autowired
    private DeliveryAddressAreaService deliveryAddressAreaService;

    @GetMapping("/area")
    public Message queryRetailStoreArea() {
        List<Province> provinces = deliveryAddressAreaService.selectRetailStoreArea();
        return Message.successData(provinces);
    }

    @GetMapping
    public Message queryRetailStore(
            @RequestParam(value = "provinceId", required = false) Integer provinceId,
            @RequestParam(value = "cityId", required = false) Integer cityId
    ) {
        Map<String, Object> params = new HashMap<>();
        if (provinceId != null) params.put("province_id", provinceId);
        if (cityId != null) params.put("city_id", cityId);
        List<RetailStore> areas = retailStoreService.selectByMap(params);
        return Message.successData(areas);
    }
}
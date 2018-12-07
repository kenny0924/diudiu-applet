package com.diudiu.applet.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.diudiu.applet.dto.Message;
import com.diudiu.applet.dto.RetailStoreDto;
import com.diudiu.applet.entity.DeliveryAddressArea;
import com.diudiu.applet.entity.Province;
import com.diudiu.applet.entity.RetailStore;
import com.diudiu.applet.service.DeliveryAddressAreaService;
import com.diudiu.applet.service.RetailStoreService;
import com.diudiu.applet.utils.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @GetMapping("/all")
    public Message queryRetailStoreAreaAll() {
        List<Province> provinces = deliveryAddressAreaService.selectRetailStoreArea();
        List<RetailStore> areas = retailStoreService.selectByWrapper(new LambdaQueryWrapper<>());
        RetailStoreDto dto = new RetailStoreDto();
        dto.setFilters(provinces);
        dto.setRetailStores(areas);
        return Message.successData(dto);
    }

    @GetMapping
    public Message queryRetailStore(
            @RequestParam(value = "provinceId", required = false) Integer provinceId,
            @RequestParam(value = "cityId", required = false) Integer cityId,
            @RequestParam(required = false) String provinceName,
            @RequestParam(required = false) String cityName
    ) {
        List<DeliveryAddressArea> areas = deliveryAddressAreaService.selectByNames(provinceName, cityName);

        LambdaQueryWrapper<RetailStore> wrapper = new LambdaQueryWrapper();
        if (provinceId != null) wrapper.eq(RetailStore::getProvinceId, provinceId);
        if (cityId != null) wrapper.eq(RetailStore::getCityId, cityId);
        if (ObjectUtil.notEmpty(areas)) {
            Map<Character, List<DeliveryAddressArea>> areaMap = areas.stream().collect(Collectors.groupingBy(DeliveryAddressArea::getType));
            List<DeliveryAddressArea> def = new ArrayList<>();
            List<Long> provinceIds = areaMap.getOrDefault('0', def).stream().map(DeliveryAddressArea::getId).collect(Collectors.toList());
            List<Long> cityIds = areaMap.getOrDefault('1', def).stream().map(DeliveryAddressArea::getId).collect(Collectors.toList());
            if (ObjectUtil.notEmpty(provinceIds)) wrapper.in(RetailStore::getProvinceId, provinceIds);
            if (ObjectUtil.notEmpty(cityIds)) wrapper.in(RetailStore::getCityId, cityIds);
        }
        List<RetailStore> retailStores = retailStoreService.selectByWrapper(wrapper);
        return Message.successData(retailStores);
    }
}
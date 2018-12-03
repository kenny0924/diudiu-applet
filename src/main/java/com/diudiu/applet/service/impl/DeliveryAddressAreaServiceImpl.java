package com.diudiu.applet.service.impl;

import com.diudiu.applet.entity.City;
import com.diudiu.applet.entity.DeliveryAddressArea;
import com.diudiu.applet.entity.Province;
import com.diudiu.applet.mapper.DeliveryAddressAreaMapper;
import com.diudiu.applet.service.DeliveryAddressAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.net.PortUnreachableException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author 刘智斌
 * @version 0.1
 * @time 12/2/18
 * @since 0.1
 */
@Service
@Transactional
public class DeliveryAddressAreaServiceImpl implements DeliveryAddressAreaService {
    @Autowired
    private DeliveryAddressAreaMapper deliveryAddressAreaMapper;


    @Override
    public List<DeliveryAddressArea> selectByMap(Map<String, Object> map) {
        return deliveryAddressAreaMapper.selectByMap(map);
    }

    @Override
    public List<Province> selectRetailStoreArea() {
        List<Province> provinces = new ArrayList<>();

        List<Map<Integer, Integer>> list = deliveryAddressAreaMapper.selectRetailStoreArea();

        Map<Integer, List<Integer>> provinceMaps = new HashMap<>();
        for (Map<Integer, Integer> map : list) {
            Integer provinceId = map.get("provinceId");
            Integer cityId = map.get("cityId");

            List<Integer> cities = provinceMaps.get(provinceId);
            if (CollectionUtils.isEmpty(cities)) {
                cities = new ArrayList<>();
                provinceMaps.put(provinceId, cities);
            }
            cities.add(cityId);
        }
        for (Map.Entry<Integer, List<Integer>> entry : provinceMaps.entrySet()) {
            Integer key = entry.getKey();
            List<Integer> value = entry.getValue();
            DeliveryAddressArea area = deliveryAddressAreaMapper.selectById(key);
            List<DeliveryAddressArea> areas = deliveryAddressAreaMapper.selectBatchIds(value);
            if (!ObjectUtils.isEmpty(area) && !CollectionUtils.isEmpty(areas)) {
                Province province = new Province(area.getId(), area.getName());
                province.setCities(new ArrayList<>(value.size()));
                for (int i = 0; i < areas.size(); i++) {
                    area = areas.get(i);
                    province.getCities().add(new City(area.getId(), Long.valueOf(area.getPid()), area.getName()));
                }
                provinces.add(province);
            }
        }
        return provinces;
    }
}

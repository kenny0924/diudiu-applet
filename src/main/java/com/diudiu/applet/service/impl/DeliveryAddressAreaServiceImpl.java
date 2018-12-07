package com.diudiu.applet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.diudiu.applet.entity.City;
import com.diudiu.applet.entity.DeliveryAddressArea;
import com.diudiu.applet.entity.Province;
import com.diudiu.applet.mapper.DeliveryAddressAreaMapper;
import com.diudiu.applet.service.DeliveryAddressAreaService;
import com.diudiu.applet.utils.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.net.PortUnreachableException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;
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

    @Override
    public List<DeliveryAddressArea> selectByNames(String provinceName, String cityName) {
        if (ObjectUtil.isEmpty(provinceName) && ObjectUtil.isEmpty(cityName))
            return null;

        List<DeliveryAddressArea> provinceAreas = deliveryAddressAreaMapper.selectList(new LambdaQueryWrapper<DeliveryAddressArea>()
                .like(DeliveryAddressArea::getName, filterName(provinceName))
                .eq(DeliveryAddressArea::getType, 0));

        List<DeliveryAddressArea> cityAreas = deliveryAddressAreaMapper.selectList(
                new LambdaQueryWrapper<DeliveryAddressArea>()
                        .like(DeliveryAddressArea::getName, filterName(cityName))
                        .eq(DeliveryAddressArea::getType, 1)
        );
        cityAreas.addAll(provinceAreas);
        return cityAreas;
    }

    private static final Pattern PATTERN = Pattern.compile("(直辖|市|维吾尔族|壮族|自治区|省|特别行政区)");

    /**
     *
     * 将含有后缀的名称修改成空
     * @return
     */
    private static String filterName(String name) {
        if (ObjectUtil.isEmpty(name)) return null;
        return PATTERN.matcher(name).replaceAll("");
    }
}

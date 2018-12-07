package com.diudiu.applet.service;

import com.diudiu.applet.entity.DeliveryAddressArea;
import com.diudiu.applet.entity.Province;

import java.util.List;
import java.util.Map;

/**
 * @author 刘智斌
 * @version 0.1
 * @time 12/2/18
 * @since 0.1
 */
public interface DeliveryAddressAreaService {

    List<DeliveryAddressArea> selectByMap(Map<String, Object> map);

    /**
     * 查询有零售店的区域
     */
    List<Province> selectRetailStoreArea();

    /**
     * 根据省份或者城市名称查找
     * @param provinceName
     * @param cityName
     * @return
     */
    List<DeliveryAddressArea> selectByNames(String provinceName, String cityName);
}

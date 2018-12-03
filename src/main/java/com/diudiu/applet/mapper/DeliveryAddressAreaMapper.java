package com.diudiu.applet.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.diudiu.applet.entity.DeliveryAddressArea;
import com.diudiu.applet.entity.Province;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author 刘智斌
 * @version 0.1
 * @time 12/2/18
 * @since 0.1
 */
public interface DeliveryAddressAreaMapper extends BaseMapper<DeliveryAddressArea> {
    /**
     * 查询有零售店的区域
     */
    @Select("select province_id as provinceId, city_id as cityId from retail_store group by province_id, city_id")
    List<Map<Integer, Integer>> selectRetailStoreArea();
}

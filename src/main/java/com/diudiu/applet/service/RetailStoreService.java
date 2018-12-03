package com.diudiu.applet.service;

import com.diudiu.applet.entity.DeliveryAddressArea;
import com.diudiu.applet.entity.RetailStore;

import java.util.List;
import java.util.Map;

/**
 * @author 刘智斌
 * @version 0.1
 * @time 12/2/18
 * @since 0.1
 */
public interface RetailStoreService {


    List<RetailStore> selectByMap(Map<String, Object> map);
}

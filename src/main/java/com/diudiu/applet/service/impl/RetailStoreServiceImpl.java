package com.diudiu.applet.service.impl;

import com.diudiu.applet.entity.DeliveryAddressArea;
import com.diudiu.applet.entity.RetailStore;
import com.diudiu.applet.mapper.RetailStoreMapper;
import com.diudiu.applet.service.RetailStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.net.ResourceManager;

import java.util.List;
import java.util.Map;

/**
 * @author 刘智斌
 * @version 0.1
 * @time 12/2/18
 * @since 0.1
 */
@Service
@Transactional
public class RetailStoreServiceImpl implements RetailStoreService {

    @Autowired
    private RetailStoreMapper retailStoreMapper;

    @Override
    public List<RetailStore> selectByMap(Map<String, Object> map) {
        return retailStoreMapper.selectByMap(map);
    }
}

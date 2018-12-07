package com.diudiu.applet.service;

import com.diudiu.applet.entity.GoodsType;

import java.util.List;
import java.util.Map;

public interface GoodsTypeService {


    List<GoodsType> selectTypeAndSubByMap(Map<String, Object> map);

}
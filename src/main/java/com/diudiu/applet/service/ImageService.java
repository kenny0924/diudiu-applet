package com.diudiu.applet.service;

import com.diudiu.applet.entity.Image;

import java.util.List;

public interface ImageService {

    /**
     * 根据外键和类型查询图片
     *
     * @return
     */
    List<Image> selectByForeignAndType(long foreignId, int type);
}

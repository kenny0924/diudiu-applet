package com.diudiu.applet.service.impl;

import com.diudiu.applet.entity.Image;
import com.diudiu.applet.global.QP;
import com.diudiu.applet.global.QueryParams;
import com.diudiu.applet.mapper.ImageMapper;
import com.diudiu.applet.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageMapper imageMapper;

    @Override
    public List<Image> selectByForeignAndType(long foreignId, int type) {
        QueryParams qp = QP.create("foreign_id", foreignId)
                .append("type", type);
        return imageMapper.selectByMap(qp);
    }
}

package com.diudiu.applet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.diudiu.applet.constants.Type;
import com.diudiu.applet.entity.Navigation;
import com.diudiu.applet.mapper.NavigationMapper;
import com.diudiu.applet.service.ImageService;
import com.diudiu.applet.service.NavigationService;
import com.diudiu.applet.utils.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 刘智斌
 * @version 0.1
 * @time 12/9/18
 * @since 0.1
 */
@Service
@Transactional
public class NavigationServiceImpl implements NavigationService {

    @Autowired
    private NavigationMapper navigationMapper;
    @Autowired
    private ImageService imageService;


    @Override
    public Navigation selectBy(int status) {
        LambdaQueryWrapper<Navigation> wrapper = new LambdaQueryWrapper();
        wrapper.eq(Navigation::getStatus, status);

        List<Navigation> navigations = navigationMapper.selectList(wrapper);
        if (ObjectUtil.notEmpty(navigations)) {
            Navigation navigation = navigations.get(0);
            navigation.setImages(imageService.selectByForeignAndType(navigation.getId(), Type.ImageType.NAVIGATION));
            return navigation;
        }
        return Navigation.EMPTY;
    }
}

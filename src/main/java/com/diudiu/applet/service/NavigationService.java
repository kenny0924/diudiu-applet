package com.diudiu.applet.service;

import com.diudiu.applet.entity.Navigation;

/**
 * @author 刘智斌
 * @version 0.1
 * @time 12/9/18
 * @since 0.1
 */
public interface NavigationService {

    Navigation selectBy(int status);
}

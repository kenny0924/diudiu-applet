package com.diudiu.applet.service;

import com.diudiu.applet.entity.Navigation;
import com.diudiu.applet.utils.JsonUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author 刘智斌
 * @version 0.1
 * @time 12/9/18
 * @since 0.1
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class NavigationServiceTest {

    @Autowired
    NavigationService navigationService;

    @Test
    public void selectBy() {
        Navigation navigation = navigationService.selectBy(1);
        assertNotEquals(navigation, Navigation.EMPTY);
        System.out.println(JsonUtils.object2Json(navigation));
    }
}
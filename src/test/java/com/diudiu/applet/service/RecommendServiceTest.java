package com.diudiu.applet.service;

import com.diudiu.applet.entity.Recommend;
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
public class RecommendServiceTest {
    @Autowired
    RecommendService recommendService;

    @Test
    public void selectBy() {
        Recommend recommend =
                recommendService.selectBy(1);
        assertNotEquals(recommend, Recommend.EMPTY);
    }
}
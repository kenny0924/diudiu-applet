package com.diudiu.applet.service;

import com.diudiu.applet.entity.Image;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ImageServiceTest {

    @Autowired
    ImageService imageService;

    @Test
    public void selectByForeignAndType() {

        List<Image> images = imageService.selectByForeignAndType(1l, 1);
        assertNotNull(images);
    }

}
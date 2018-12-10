package com.diudiu.applet.web.controller;

import com.diudiu.applet.dto.IndexDto;
import com.diudiu.applet.dto.Message;
import com.diudiu.applet.service.BannerService;
import com.diudiu.applet.service.NavigationService;
import com.diudiu.applet.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 刘智斌
 * @version 0.1
 * @time 12/9/18
 * @since 0.1
 */
@RestController
@RequestMapping("/index")
public class IndexController {
    @Autowired
    private BannerService bannerService;
    @Autowired
    private RecommendService recommendService;
    @Autowired
    private NavigationService navigationService;

    @GetMapping
    public Message index() {
        IndexDto dto = new IndexDto();
        dto.setBanner(bannerService.selectBy(1));
        dto.setNavigation(navigationService.selectBy(1));
        dto.setRecommend(recommendService.selectBy(1));
        return Message.successData(dto);
    }
}

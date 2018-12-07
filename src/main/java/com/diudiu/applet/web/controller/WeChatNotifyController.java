package com.diudiu.applet.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/wechat/notify")
public class WeChatNotifyController {


    @PostMapping("/pay")
    public void payNotify(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(request);
        System.out.println(response);
    }

}

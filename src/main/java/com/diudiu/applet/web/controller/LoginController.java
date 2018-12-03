package com.diudiu.applet.web.controller;

import com.diudiu.applet.dto.LoginDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.groups.Default;

@RestController
public class LoginController {

    public void login(@RequestBody @Validated(Default.class) LoginDto dto) {

    }
}

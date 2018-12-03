package com.diudiu.applet;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.diudiu.applet.mapper")
public class DiudiuAppletApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiudiuAppletApplication.class, args);
    }
}

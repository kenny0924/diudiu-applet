package com.diudiu.applet.entity;

import lombok.Data;

/**
 * @author 刘智斌
 * @version 0.1
 * @time 12/2/18
 * @since 0.1
 */
@Data
public class City {
    public City() {
    }

    public City(Long id, Long pid, String name) {
        this.id = id;
        this.pid = pid;
        this.name = name;
    }

    private Long id;

    private Long pid;

    private String name;

}

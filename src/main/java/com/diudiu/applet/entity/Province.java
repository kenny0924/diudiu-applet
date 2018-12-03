package com.diudiu.applet.entity;

import lombok.Data;

import java.util.List;

/**
 * @author 刘智斌
 * @version 0.1
 * @time 12/2/18
 * @since 0.1
 */
@Data
public class Province {
    public Province() {
    }

    public Province(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    private Long id;

    private String name;

    private List<City> cities;
}

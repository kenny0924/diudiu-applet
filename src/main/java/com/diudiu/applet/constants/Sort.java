package com.diudiu.applet.constants;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public enum Sort {
        DEF(1, "默认排序"),
        PRICE_LOW_TO_UP(2, "价格从低到高"),
        PRICE_UP_TO_LOW(3, "价格从高到底"),
        SELL_UP_TO_LOW(4, "销量从高到底");
        public Integer id;

        public String name;

        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        Sort(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        public static List<Map<String, Object>> toList() {
            return Arrays.asList(Sort.values()).stream()
                    .map((s) -> {
                        Map<String, Object> map = new LinkedHashMap<>();
                        map.put("id", s.id);
                        map.put("name", s.name);
                        return map;
                    }).collect(Collectors.toList());
        }
    }
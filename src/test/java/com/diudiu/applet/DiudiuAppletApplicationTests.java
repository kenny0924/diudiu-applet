package com.diudiu.applet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.ObservableMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

public class DiudiuAppletApplicationTests {

    @Test
    public void contextLoads() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        List<Map<String, String>> maps = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Map<String, String> map = new HashMap<>();
            map.put(String.valueOf(i), String.valueOf(i));
            maps.add(map);
        }
        System.out.println(objectMapper.writeValueAsString(maps));
    }
}
package com.diudiu.applet.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author 刘智斌
 * @version 0.1
 * @time 8/23/17
 * @since 0.1
 */
public class JsonUtils {
    public static final ObjectMapper CONFIG_MAPPER = ObjectMapperInstance.CONFIG_MAPPER;

    public static String object2Json(Object o, ObjectMapper objectMapper) {
        try {
            return objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String object2Json(Object o) {
        try {
            return ObjectMapperInstance.objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T json2Object(String json, Class<T> clazz) {
        return json2Object(json, ObjectMapperInstance.objectMapper, clazz);
    }

    public static <T> T json2Object(String json, ObjectMapper objectMapper, Class<T> clazz) {
        try {
            return ObjectUtil.notEmpty(json) ? objectMapper.readValue(json, clazz) : null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> json2List(String json, Class<T> tClass) {
        try {
            return ObjectUtil.notEmpty(json) ? (List<T>) ObjectMapperInstance.objectMapper.readValue(json, getCollectionType(ObjectMapperInstance.objectMapper, List.class, tClass)) : null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取泛型的Collection Type
     *
     * @param collectionClass 泛型的Collection
     * @param elementClasses  元素类
     * @return JavaType Java类型
     * @since 1.0
     */
    public static JavaType getCollectionType(ObjectMapper mapper, Class<?> collectionClass, Class<?>... elementClasses) {
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    static class ObjectMapperInstance {
        public final static ObjectMapper objectMapper = new ObjectMapper();
        public final static ObjectMapper CONFIG_MAPPER = new ObjectMapper();

        static {
            CONFIG_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
            CONFIG_MAPPER.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
            CONFIG_MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            CONFIG_MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        }
    }
}

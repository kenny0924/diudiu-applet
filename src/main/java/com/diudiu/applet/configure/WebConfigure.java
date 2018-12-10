package com.diudiu.applet.configure;

import com.diudiu.applet.web.interceptor.AuthTokenInterceptor;
import com.diudiu.applet.web.interceptor.CrossInterceptor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.text.SimpleDateFormat;
import java.util.Arrays;

/**
 * @author 刘智斌
 * @version 0.1
 * @time 12/23/17
 * @since 0.1
 */
@Configuration
public class WebConfigure extends WebMvcConfigurerAdapter {

    @Bean
    public MappingJackson2HttpMessageConverter customMappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter m = new MappingJackson2HttpMessageConverter();
        m.setSupportedMediaTypes(Arrays.asList(MediaType.ALL));
        m.setObjectMapper(objectMapper());
        return m;
    }

    @Bean
    public CrossInterceptor crossInterceptor() {
        return new CrossInterceptor();
    }

    @Bean
    public AuthTokenInterceptor authTokenInterceptor() {
        return new AuthTokenInterceptor();
    }

    /************************************** Interceptor **************************************************/
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(crossInterceptor());
        registry.addInterceptor(authTokenInterceptor());
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return objectMapper;
    }
}

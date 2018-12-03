package com.diudiu.applet.web.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author 刘智斌
 * @version 0.1
 * @time 3/22/18
 * @since 0.1
 */
public abstract class BaseInterceptor implements HandlerInterceptor {


    public static void responseJson(String json, HttpServletResponse response) {
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Charset", "UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "No-cache");
        response.setDateHeader("Expires", 0L);
        try {
            PrintWriter e = response.getWriter();
            e.write(json);
            e.flush();
            e.close();
        } catch (IOException var3) {

        }
    }
}

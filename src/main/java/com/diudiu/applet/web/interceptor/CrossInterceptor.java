package com.diudiu.applet.web.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 刘智斌
 * @version 0.1
 * @time 12/23/17
 * @since 0.1
 */
public class CrossInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers",
                "Origin, No-Cache, X-Requested-With, If-Modified-Since, Last-Modified, Expires, Content-Type,Authorization,User-Agent,Auth_Token, Pragma,Cache-Control, VERSION-CODE, REQ-SOURCE");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        /*String authorization = request.getHeader("Authorization");
        JWTInfo jwtInfo = jwtTokenClient.checkAuthenticationToken(authorization);*/
        if (request.getMethod().equals("OPTIONS")) {
            return true;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}

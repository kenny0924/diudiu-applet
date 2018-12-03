package com.diudiu.applet.web.interceptor;

import com.diudiu.applet.dto.JWTInfo;
import com.diudiu.applet.dto.Message;
import com.diudiu.applet.utils.JsonUtils;
import com.diudiu.applet.utils.ObjectUtil;
import com.diudiu.applet.web.annotation.TokenApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 刘智斌
 * @version 0.1
 * @time 12/29/17
 * @since 0.1
 */
public class AuthTokenInterceptor extends BaseInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthTokenInterceptor.class);
    private static final String FORBIDDEN = JsonUtils.object2Json(Message.FORBIDDEN_ERROR);
    private final Map<HandlerMethod, Auth> authorityCache = new ConcurrentHashMap<>(256);
    @Autowired(required = false)
    public JwtHelper jwtHelper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod && jwtHelper != null) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Auth auth = authorityCache.get(handlerMethod);
            if (auth == null) {
                // 检查类上面是否有注解
                TokenApi authority = handlerMethod.getBeanType().getAnnotation(TokenApi.class);
                authority = authority == null ? handlerMethod.getMethod().getAnnotation(TokenApi.class) : authority;

                auth = new Auth();
                auth.isForce = authority == null || !authority.required() ? false : true;
                auth.tokenApi = authority;
                authorityCache.put(handlerMethod, auth);
            }
            if (auth.tokenApi != null) {
                TokenApi authority = auth.tokenApi;
                String authorization = request.getHeader("Authorization");
                String token = ObjectUtil.isEmpty(authorization) ? null : authorization.substring(authorization.indexOf("Bearer") + 7);
                if (    // 强制验证
                        authority.required() ||
                                // 如果不是强制验证 但是Token又不为空 说明前段传了Token过来 则验证
                                (!authority.required() && ObjectUtil.notEmpty(token))) {
                    if (token != null) {

                        JWTInfo jwtInfo;
                        try {
                            jwtInfo = jwtHelper.checkAuthenticationToken(token);
                            request.setAttribute(RequestConstant.CURRENT_USER_NAME, jwtInfo.getUsername());
                            request.setAttribute(RequestConstant.CURRENT_USER_ID, jwtInfo.getUserId());
                            request.setAttribute(RequestConstant.CURRENT_USER_TEL, jwtInfo.getTel());
                            request.setAttribute(RequestConstant.CURRENT_USER_TOKEN, authorization);
                        } catch (Exception e) {
                            LOGGER.error("校验Token报错", e);
                            responseJson(FORBIDDEN, response);
                            return false;
                        }
                    } else {
                        responseJson(FORBIDDEN, response);
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    class Auth {
        TokenApi tokenApi;
        boolean isForce;
    }
}

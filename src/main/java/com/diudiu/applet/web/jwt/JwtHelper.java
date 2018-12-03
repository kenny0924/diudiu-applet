package com.diudiu.applet.web.jwt;


import com.diudiu.applet.dto.JWTInfo;
import com.diudiu.applet.utils.ObjectUtil;

/**
 * @author 刘智斌
 * @version 0.1
 * @time 9/19/18
 * @since 0.1
 */
public interface JwtHelper {

    /**
     * 创建JwtInfo
     *
     * @author Zhibin Liu
     * @time 9/19/18 17:19
     */
    JWTInfo createAuthenticationToken(JWTInfo jwtInfo) throws Exception;

    /**
     * 检查Token
     *
     * @author Zhibin Liu
     * @time 9/19/18 17:11
     */
    JWTInfo checkAuthenticationToken(String token) ;

    /**
     * 删除Token
     *
     * @author Zhibin Liu
     * @date 9/26/18 21:41
     */
    void deleteAuthenticationToken(String token) throws Exception;

    /**
     * 去除Token
     */
    default String trimToken(String token) {
        if (ObjectUtil.isEmpty(token)) {
            return null;
        }
        if (token.indexOf(" ") > 0)
            return token.substring(token.indexOf(" ") + 1);
        return token;
    }
}
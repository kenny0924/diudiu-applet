package com.diudiu.applet.global;

import com.diudiu.applet.utils.ObjectUtil;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import static com.diudiu.applet.constants.RequestConstant.*;


/**
 * 当前请求的用户信息
 *
 * @author 刘智斌
 * @version 0.1
 * @time 1/4/18
 * @since 0.1
 */
public class CurrentUserHolder {

    /**
     * 获取用户名称
     *
     * @author Zhibin Liu
     * @time 3/14/18 16:10
     */
    public static String getUserName() {
        Object o = getCurrentRequest().getAttribute(CURRENT_USER_NAME);
        return o == null ? null : o.toString();
    }

    /**
     * 获取用户ID
     *
     * @author Zhibin Liu
     * @time 3/14/18 16:10
     */
    public static Long getUserId() {
        Object o = getCurrentRequest().getAttribute(CURRENT_USER_ID);
        return o == null ? null : Long.parseLong(o.toString());
    }


    /**
     * 获取用户手机号
     *
     * @author Zhibin Liu
     * @time 3/14/18 16:10
     */
    public static String getUserTel() {
        Object o = getCurrentRequest().getAttribute(CURRENT_USER_TEL);
        return o == null ? null : o.toString();
    }

    /**
     * 获取用户TOKEN
     *
     * @author Zhibin Liu
     * @time 3/14/18 16:10
     */
    public static String getUserToken() {
        Object o = getCurrentRequest().getAttribute(CURRENT_USER_TOKEN);
        return o == null ? null : o.toString();
    }

    /**
     * 获取请求的IP
     *
     * @author Zhibin Liu
     * @time 3/14/18 16:10
     */
    public static String getIp() {
        HttpServletRequest request = getCurrentRequest();
        String ip;
        ip = ObjectUtil.notEmpty(ip = request.getHeader("X-Real-IP")) ? ip : ObjectUtil.notEmpty(ip = request.getRemoteAddr()) ? ip : "0.0.0.0";
        return ip;
    }

    /**
     * 获取当前用户请求
     *
     * @author Zhibin Liu
     * @time 3/14/18 16:11
     */
    private static HttpServletRequest getCurrentRequest() {
        ServletRequestAttributes sras = null;
        RequestAttributes ras = RequestContextHolder.currentRequestAttributes();
        if (ras instanceof ServletRequestAttributes) {
            sras = (ServletRequestAttributes) ras;
            HttpServletRequest httpServletRequest = sras.getRequest();
            return httpServletRequest;
        }
        return null;
    }

}

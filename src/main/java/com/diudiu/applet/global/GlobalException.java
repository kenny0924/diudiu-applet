package com.diudiu.applet.global;

import com.diudiu.applet.dto.Message;
import com.diudiu.applet.exception.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理
 *
 * @author 刘智斌
 * @version 0.1
 * @time 1/26/18
 * @since 0.1
 */
@ControllerAdvice
public class GlobalException {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalException.class);

    @ExceptionHandler(BaseException.class)
    @ResponseBody
    public Message handleBizException(HttpServletRequest request, BaseException biz) {
        // 请求来源
        String reqSource = request.getHeader("REQ-SOURCE");
        // 请求版本
        String versionCode = request.getHeader("VERSION-CODE");
        LOGGER.error("=====================业务错误====================" +
                        "请求地址: " + getRequestUri() +
                        "登陆用户: " + CurrentUserHolder.getUserId() +
                        "错误消息: " + biz.getMsg() + ", 请求来源:" + reqSource + ", 请求版本:" + versionCode,
                biz);
        Message message = new Message(biz.getResCode(), biz.getMsg());
        return message;
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Message handleException(HttpServletRequest request, Exception e) {
        if (e instanceof MethodArgumentNotValidException) {
            try {
                MethodArgumentNotValidException argsException = (MethodArgumentNotValidException) e;
                String message = argsException.getBindingResult().getAllErrors().get(0).getDefaultMessage();
                return new Message(Message.ARGS_ERROR.getResCode(), message);
            } catch (Exception newE) {
                return Message.ARGS_ERROR;
            }
        }
        // 请求来源
        String reqSource = request.getHeader("REQ-SOURCE");
        // 请求版本
        String versionCode = request.getHeader("VERSION-CODE");
        LOGGER.error("=====================系统错误====================\n" +
                "请求地址: " + getRequestUri() +
                "登陆用户: " + CurrentUserHolder.getUserId() +
                "请求来源:" + reqSource + ", 请求版本:" + versionCode, e);
        return Message.SYS_ERROR;
    }

    private String getRequestUri() {
        ServletRequestAttributes sras;
        RequestAttributes ras = RequestContextHolder.currentRequestAttributes();
        if (ras instanceof ServletRequestAttributes) {
            sras = (ServletRequestAttributes) ras;
            HttpServletRequest httpServletRequest = sras.getRequest();
            return httpServletRequest.getRequestURI();
        }
        return "";
    }
}

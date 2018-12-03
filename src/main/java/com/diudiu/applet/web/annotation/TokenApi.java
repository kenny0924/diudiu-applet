package com.diudiu.applet.web.annotation;

import java.lang.annotation.*;


/**
 * 用于方法某个方法验证jwt token
 * <p>
 * 如果注释在类上面，表示当前Controller的所有RequestMapping方法都需要验证jwt token
 * 如果注释在方法上面，表示当前RequestMapping方法需要验证jwt token
 *
 * @author 刘智斌
 * @version 0.1
 * @time 2017/12/29
 * @since 0.1
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TokenApi {


    /**
     * required
     * 表示是否需要验证Token
     * true: 表示接口一定需要验证权限 默认值
     * false: 表示接口不一定需要验证权限 可以容许Token为空 但是当有Token的时候则进行解析Token
     */
    boolean required() default true;
}

package com.diudiu.applet.web.jwt;

/**
 * Created by ace on 2017/9/10.
 */
public interface IJWTInfo {
    /**
     * 获取用户名 user_uuid
     **/
    String getUniqueName();

    /**
     * 获取用户ID
     **/
    String getId();

    /**
     * Token uuid
     **/
    String getUuid();

    /**
     * 获取名称
     **/
    String getName();
}

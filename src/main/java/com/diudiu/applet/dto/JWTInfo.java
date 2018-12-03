package com.diudiu.applet.dto;

import java.io.Serializable;

/**
 * token info
 *
 * @author Zhibin Liu
 * @time 12/28/17 15:22
 */
public class JWTInfo implements Serializable {
    /**  */
	private static final long serialVersionUID = -4504298077118712132L;
	/**
     * 用户名
     **/
    private String username;
    /**
     * Token
     **/
    private String token;
    /**
     * 用户ID
     **/
    private Long userId;
    /**
     * Token Uuid
     **/
    private String uuid;
    /**
     * 手机号
     **/
    private String tel;

    public JWTInfo() {
    }

    public JWTInfo(Long userId, String username, String uuid, String tel) {
        this.userId = userId;
        this.username = username;
        this.uuid = uuid;
        this.tel = tel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}

package com.diudiu.applet.entity;

import java.util.Date;

/**
 * @author 刘智斌
 * @version 0.1
 * @time 12/28/17
 * @since 0.1
 */
public class JwtToken {

    /**
     * 主键
     **/
    private Long id;
    /**
     * 用户ID
     **/
    private String tokenUuid;
    /**
     * 用户ID
     **/
    private Long userId;
    /**
     * Token
     **/
    private String token;
    /**
     * 修改时间
     **/
    private Date modifyTime;

    public JwtToken() {
    }

    public JwtToken(String tokenUuid, Long userId, String token) {
        this.tokenUuid = tokenUuid;
        this.userId = userId;
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTokenUuid() {
        return tokenUuid;
    }

    public void setTokenUuid(String tokenUuid) {
        this.tokenUuid = tokenUuid;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}

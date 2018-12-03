package com.diudiu.applet.service;


import com.diudiu.applet.entity.JwtToken;

/**
 * @author 刘智斌
 * @version 0.1
 * @time 12/28/17
 * @since 0.1
 */
public interface JwtService {


    /**
     * 查询用户是否已经有Token
     *
     * @param userId
     * @author Zhibin Liu
     * @time 12/29/17 13:51
     */
    JwtToken queryJwtTokenByUserUuid(Long userId);


    /**
     * 获取 token id
     *
     * @param jwtToken
     * @author Zhibin Liu
     * @time 12/28/17 18:05
     */
    Long doCreateJwt(JwtToken jwtToken);

    /**
     * 删除Token
     *
     * @param uuid
     * @author Zhibin Liu
     * @time 12/28/17 18:17
     */
    Integer doDeleteJwtByTokenUuid(String uuid);
}

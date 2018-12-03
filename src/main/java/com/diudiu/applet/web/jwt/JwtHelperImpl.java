package com.diudiu.applet.web.jwt;

import com.diudiu.applet.constants.CommonConstants;
import com.diudiu.applet.dto.JWTInfo;
import com.diudiu.applet.dto.Message;
import com.diudiu.applet.entity.JwtToken;
import com.diudiu.applet.exception.BaseException;
import com.diudiu.applet.service.JwtService;
import com.diudiu.applet.utils.UUIDUtils;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Base64;

/**
 * @author 刘智斌
 * @version 0.1
 * @time 9/19/18
 * @since 0.1
 */
@Component
public class JwtHelperImpl implements JwtHelper, InitializingBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtHelperImpl.class);
    protected String tokenRsaPri = null;
    protected String tokenRsaPub = null;
    private byte[] userPriKey = null;
    private byte[] userPubKey = null;
    private int expire = 5184000;  // 默认 毫秒

    @Autowired(required = false)
    private JwtService jwtService;

    /**
     * 设置token私钥
     **/
    @Value("${jwt.token.rsa-pri}")
    public void setTokenRsaPri(String tokenRsaPri) {
        this.tokenRsaPri = tokenRsaPri;
    }

    /**
     * 设置token公钥
     **/
    @Value("${jwt.token.rsa-pub}")
    public void setTokenRsaPub(String tokenRsaPub) {
        this.tokenRsaPub = tokenRsaPub;
    }

    /**
     * 设置过期时间
     **/
    public void setExpire(int expire) {
        this.expire = expire;
    }

    @Override
    public JWTInfo createAuthenticationToken(JWTInfo jwtInfo) throws Exception {

        String uuid;
        String token = generateToken(new JWTInfo(jwtInfo.getUserId(),
                jwtInfo.getUsername(), uuid = UUIDUtils.generateUuid(), jwtInfo.getTel()));

        // 创建Token到数据和Redis中
        Long id = jwtService.doCreateJwt(new JwtToken(uuid, jwtInfo.getUserId(), token));

        jwtInfo.setToken(token);
        return jwtInfo;
    }

    /**
     * genToken
     *
     * @author Zhibin Liu
     * @time 9/19/18 17:28
     */
    private String generateToken(JWTInfo jwtInfo) throws Exception {
        return JWTUtil.generateToken(jwtInfo, userPriKey, expire);
    }

    @Override
    public JWTInfo checkAuthenticationToken(String token) {
        JWTInfo jwtInfo;
        try {
            jwtInfo = JWTUtil.getInfoFromToken(token, userPubKey);
        } catch (ExpiredJwtException e) {
            String tokenUuid = (String) e.getClaims().get(CommonConstants.JWT_KEY_UUID);
            // TODO: 12/3/18  
            throw new BaseException(Message.FORBIDDEN_ERROR);
        } catch (Exception e) {
            LOGGER.error("检查Token报错", e);
            throw new BaseException(Message.FORBIDDEN_ERROR);
        }
        // 先查询Redis
        // TODO: 12/3/18
        JwtToken jwt = jwtService.queryJwtTokenByUserUuid(jwtInfo.getUserId());
        if (jwt != null) {
            String tk = jwt.getToken();
            /*if (tk == null || "".equals(tk)) {
                // 根据用户Id查询tk
                JwtToken jwtToken = jwtService.queryJwtTokenByUserUuid(jwtInfo.getUserId());
                if (jwtToken == null) {
                    // 说明不存在这个token
                    throw new BaseException(Message.FORBIDDEN_ERROR);
                } else {
                    tk = jwtToken.getToken();
                    if (token.equals(tk)) {
                        // 相等说明Token正确
                        tokenTemplate.opsForValue().set(jwtToken.getTokenUuid(), jwtToken.getToken());
                        return jwtInfo;
                    } else {
                        // 说明token是以前的token
                        throw new BaseException(Message.FORBIDDEN_ERROR);
                    }
                }
            }*/
            if (token.equals(tk))
                return jwtInfo;
        }
        throw new BaseException(Message.FORBIDDEN_ERROR);
    }

    @Override
    public void deleteAuthenticationToken(String token) {
        JWTInfo jwtInfo = checkAuthenticationToken(token);
        jwtService.doDeleteJwtByTokenUuid(jwtInfo.getUuid());
    }

    @Override
    public void afterPropertiesSet() {
        if (tokenRsaPri != null) {
            userPriKey = Base64.getDecoder().decode(tokenRsaPri);
        }
        if (tokenRsaPub != null) {
            userPubKey = Base64.getDecoder().decode(tokenRsaPub);
        }
    }
}

package com.diudiu.applet.web.jwt;

import com.diudiu.applet.constants.CommonConstants;
import com.diudiu.applet.dto.JWTInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.joda.time.DateTime;

/**
 * Created by ace on 2017/9/10.
 */
public class JWTUtil {
    private static RsaKeyHelper rsaKeyHelper = new RsaKeyHelper();

    /**
     * 密钥加密token
     *
     * @param jwtInfo
     * @param priKey
     * @param expire
     * @return
     * @throws Exception
     */
    static String generateToken(JWTInfo jwtInfo, byte priKey[], int expire) throws Exception {

        // Token 保持在凌晨3点过期

        String compactJws = Jwts.builder()
                .setSubject(String.valueOf(jwtInfo.getUserId()))    // 用户UUID
                .claim(CommonConstants.JWT_KEY_NAME, jwtInfo.getUsername())   // 用户名
                .claim(CommonConstants.JWT_KEY_UUID, jwtInfo.getUuid())   // Token UUID
                .claim(CommonConstants.JWT_KEY_TEL, jwtInfo.getTel())   // 手机号
                .setExpiration(
                        DateTime.now().plusSeconds(expire).withHourOfDay(3).withMinuteOfHour(30).withSecondOfMinute(0).toDate()
                )
                .signWith(SignatureAlgorithm.RS256, rsaKeyHelper.getPrivateKey(priKey))
                .compact();
        return compactJws;
    }

    /**
     * 公钥解析token
     *
     * @param token
     * @return
     * @throws Exception
     */
    public static Jws<Claims> parserToken(String token, String pubKeyPath) throws Exception {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(rsaKeyHelper.getPublicKey(pubKeyPath)).parseClaimsJws(token);
        return claimsJws;
    }

    /**
     * 公钥解析token
     *
     * @param token
     * @return
     * @throws Exception
     */
    static Jws<Claims> parserToken(String token, byte[] pubKey) throws Exception {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(rsaKeyHelper.getPublicKey(pubKey)).parseClaimsJws(token);
        return claimsJws;
    }

    /**
     * 获取token中的用户信息
     *
     * @param token
     * @param pubKey
     * @return
     * @throws Exception
     */
    static JWTInfo getInfoFromToken(String token, byte[] pubKey) throws Exception {
        Jws<Claims> claimsJws = parserToken(token, pubKey);
        Claims body = claimsJws.getBody();
        return new JWTInfo(Long.valueOf(body.getSubject()),
                getObjectValue(body.get(CommonConstants.JWT_KEY_NAME)), getObjectValue(body.get(CommonConstants.JWT_KEY_UUID)),
                getObjectValue(body.get(CommonConstants.JWT_KEY_TEL)));
    }

    /**
     * 字符串取值
     *
     * @param obj
     * @return
     */
    public static String getObjectValue(Object obj) {
        return obj == null ? "" : obj.toString();
    }
}

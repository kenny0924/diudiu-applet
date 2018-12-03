package com.diudiu.applet.web.jwt;

import com.diudiu.applet.dto.JWTInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author 刘智斌
 * @version 0.1
 * @time 12/3/18
 * @since 0.1
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JwtHelperTest {

    @Autowired
    JwtHelper jwtHelper;

    @Test
    public void createAuthenticationToken() throws Exception {
        JWTInfo jwtInfo = new JWTInfo();
        jwtInfo.setTel("15116277873");
        jwtInfo.setUserId(1l);
        jwtInfo.setUsername("Kenny");
        jwtInfo = jwtHelper.createAuthenticationToken(jwtInfo);
        assertNotNull(jwtInfo);
    }

    @Test
    public void checkAuthenticationToken() {
    }

    @Test
    public void deleteAuthenticationToken() {
    }

    @Test
    public void trimToken() {
    }
}
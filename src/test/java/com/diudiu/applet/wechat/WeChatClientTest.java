package com.diudiu.applet.wechat;

import com.diudiu.applet.dto.wechat.AccessToken;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;

import java.io.IOException;
import java.nio.channels.AcceptPendingException;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class WeChatClientTest {
    @Autowired
    private WeChatClient weChatClient;

    @Test
    public void getAccessToken() {
        AccessToken token = weChatClient.getAccessToken();
        assertNotNull(token);
        System.out.println(token);
    }

    @Test
    public void testQRCode() throws IOException {
        WeChatClient client = new WeChatClient();
/*
        AccessToken accessToken = client.getAccessToken();
        System.out.println(accessToken.getAccess_token());

        String token = accessToken.getAccess_token();*/
        client.getWXCodeUnlimit("");
    }
}
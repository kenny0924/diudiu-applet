package com.diudiu.applet.wechat;

import okhttp3.*;
import org.junit.Test;

import java.io.IOException;

public class WeChatTest {

    OkHttpClient client = new OkHttpClient();
    @Test
    public void getAccessToken() throws IOException {
        Request request = new Request.Builder()
                .get()
                .url("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=1&secret=2")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println(response.body().string());
            }
        });
        System.in.read();
    }
}

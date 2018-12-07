package com.diudiu.applet.wechat;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class Client {

    public static class Inner{
        public final static OkHttpClient CLIENT = new OkHttpClient();
    }

    /**
     * 同步调用
     */
    public static Response synCall(Request request) {
        try {
            return Inner.CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
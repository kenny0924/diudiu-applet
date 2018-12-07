package com.diudiu.applet.wechat;

import com.diudiu.applet.dto.wechat.AccessToken;
import com.diudiu.applet.dto.wechat.UnifiedPay;
import com.diudiu.applet.utils.IDUtils;
import com.diudiu.applet.utils.JsonUtils;
import com.diudiu.applet.utils.UUIDUtils;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.Xpp3Driver;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;

import static com.diudiu.applet.utils.MD5Utils.MD5Encode;

@Component
public class WeChatClient extends WXPayConfig implements InitializingBean {

    @Value("${config.wechat.appid}")
    private String appid = "1";
    @Value("${config.wechat.secret}")
    private String secret = "2";
    @Value("${config.wechat.mchId}")
    private String mchId = "3";
    @Value("${config.wechat.paySecret}")
    private String paySecret = "4";

    private static String access_token_url;

    /**
     * 获取访问token
    * @return
     */
    public AccessToken getAccessToken() {
        Request request = new Request.Builder()
                .get()
                .url(access_token_url)
                .build();
        Response response = Client.synCall(request);
        if (response.code() == 200) {
            try {
                return JsonUtils.json2Object(response.body().string(), AccessToken.class);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    public void getWXCodeUnlimit(String accessToken) throws IOException {
        Map<String, String> map = new HashMap<>();
        map.put("scene", "dsadsadsa");
        map.put("width", "300");
        String reqBody = JsonUtils.object2Json(map);

        RequestBody body = RequestBody.create(MediaType.get("application/json"), reqBody);
        Request request = new Request.Builder()
                .url("https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + accessToken)
                .post(body)
                .build();

        Response response = Client.synCall(request);

        InputStream stream = response.body().byteStream();
        OutputStream outputStream = new FileOutputStream(new File("C:\\Users\\liuzhibin\\Desktop\\img\\qr.png"));
        int b;
        while ((b = stream.read()) != -1) {
            outputStream.write(b);
        }
        stream.close();
        outputStream.close();
    }

    public void unifiedPay() {
        Map<String, String> params = new TreeMap<>();
        params.put("appid", appid);
        params.put("mch_id", mchId);
        params.put("device_info", "WEB");
        params.put("nonce_str", "f5678b31548a4183Ae07e536fb4b156a");
        params.put("body", "123");
        params.put("out_trade_no", "78179736962076672");
        params.put("total_fee", "1");
        params.put("spbill_create_ip", "127.0.0.1");
        params.put("notify_url", "https://kennylua.imwork.net/wechat/notify/pay");
        params.put("trade_type", "JSAPI");
        try{
            String sign = WXPayUtil.generateSignature(params, paySecret);
            params.put("sign", sign);
            String reqXml = WXPayUtil.mapToXml(params);

            RequestBody body = RequestBody.create(MediaType.get("application/xml"), reqXml);
            Request request = new Request.Builder()
                    .url("https://api.mch.weixin.qq.com/pay/unifiedorder")
                    .post(body)
                    .build();
            Response response = Client.synCall(request);
            System.out.println(response.body().string());
        } catch (Exception e) {

        }


        /*
        UnifiedPay pay = UnifiedPay.convertFrom(params);
        XStream xstream = new XStream(new Xpp3Driver(new NoNameCoder()));
        xstream.alias("xml", UnifiedPay.class);
        String reqXml = xstream.toXML(pay);

        try {
            RequestBody body = RequestBody.create(MediaType.get("application/xml"), reqXml);
            Request request = new Request.Builder()
                    .url("https://api.mch.weixin.qq.com/pay/unifiedorder")
                    .post(body)
                    .build();
            Response response = Client.synCall(request);
            System.out.println(response.body().string());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }*/
    }

    public String createSign(String characterEncoding,Map<String,String> parameters){
        StringBuffer sb = new StringBuffer();
        Set es = parameters.entrySet();//所有参与传参的参数按照accsii排序（升序）
        Iterator it = es.iterator();
        while(it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            String k = (String)entry.getKey();
            Object v = entry.getValue();
            if(null != v && !"".equals(v)
                    && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + secret);
        String sign = MD5Encode(sb.toString(), characterEncoding).toUpperCase();
        return sign;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        access_token_url = new StringBuilder("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential")
                            .append("&appid=").append(appid)
                            .append("&secret=").append(secret).toString();
    }

    public static void main(String[] args) throws Exception {
        WeChatClient chatClient = new WeChatClient();
        WXPay wxpay = new WXPay(chatClient);

        Map<String, String> data = new HashMap<String, String>();
        data.put("body", "tx");
        data.put("out_trade_no", "2016090910595900000012");
        data.put("device_info", "WEB");
        data.put("fee_type", "CNY");
        data.put("total_fee", "1");
        data.put("spbill_create_ip", "123.12.12.123");
        data.put("notify_url", "http://www.example.com/wxpay/notify");
        data.put("trade_type", "NATIVE");  // 此处指定为扫码支付

        try {
            Map<String, String> resp = wxpay.unifiedOrder(data);
            System.out.println(resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    String getAppID() {
        return appid;
    }

    @Override
    String getMchID() {
        return mchId;
    }

    @Override
    String getKey() {
        return paySecret;
    }

    @Override
    InputStream getCertStream() {
        return null;
    }

    @Override
    IWXPayDomain getWXPayDomain() {
        return new IWXPayDomain() {
            @Override
            public void report(String domain, long elapsedTimeMillis, Exception ex) {

            }

            @Override
            public DomainInfo getDomain(WXPayConfig config) {
                return new DomainInfo("api.mch.weixin.qq.com", true);
            }
        };
    }
}
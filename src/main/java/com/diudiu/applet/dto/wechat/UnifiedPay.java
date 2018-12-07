package com.diudiu.applet.dto.wechat;

import com.diudiu.applet.utils.IDUtils;
import com.diudiu.applet.utils.UUIDUtils;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

import java.util.Map;

@Data
public class UnifiedPay {

    @XStreamAlias("appid")
    private String appid;

    @XStreamAlias("mch_id")
    private String mch_id;

    private String device_info;

    private String nonce_str;

    private String sign_type;

    private String sign;

    private String body;

    private String out_trade_no;

    // 分为单位
    private String total_fee;

    private String spbill_create_ip;

    private String notify_url;

    private String trade_type;

    public static UnifiedPay convertFrom(Map<String, String> map) {
        UnifiedPay pay = new UnifiedPay();
        pay.setAppid(map.get("appid"));
        pay.setMch_id(map.get("mch_id"));
        pay.setDevice_info(map.get("device_info"));
        pay.setNonce_str(map.get("nonce_str"));
        pay.setSign_type(map.get("sign_type"));
        pay.setSign(map.get("sign"));
        pay.setBody(map.get("body"));
        pay.setOut_trade_no(map.get("out_trade_no"));
        pay.setTotal_fee(map.get("total_fee"));
        pay.setSpbill_create_ip(map.get("spbill_create_ip"));
        pay.setNotify_url(map.get("notify_url"));
        pay.setTrade_type(map.get("trade_type"));
        return pay;
    }
}

package com.diudiu.applet.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 刘智斌
 * @version 0.1
 * @time 9/12/18
 * @since 0.1
 */
public class AliSmsUtils {

    static {
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
    }
    public static void main(String[] args) throws ClientException {
        sendSms(Arrays.asList("15116277873"), "1235");
    }
    /**
     * 批量发送短信
     * @author Zhibin Liu
     * @time 9/12/18 18:21
     */
    public static boolean sendSms(List<String> tels, String code) throws ClientException{
        List<List<String>> splitList = splitTels(tels, 999);
        for (List<String> list : splitList) {
            // 不想做配置文件了 懒
            final String product = "Dysmsapi";
            final String domain = "dysmsapi.aliyuncs.com";
            final String accessKeyId = "1";
            final String accessKeySecret = "1";
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
                    accessKeySecret);
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
            IAcsClient acsClient = new DefaultAcsClient(profile);

            SendSmsRequest request = new SendSmsRequest();
            request.setMethod(MethodType.POST);
            //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式；发送国际/港澳台消息时，接收号码格式为00+国际区号+号码，如“0085200000000”
            request.setPhoneNumbers(appendTels(list));
            //必填:短信签名-可在短信控制台中找到
            request.setSignName("1");   // 精灵提醒
            //必填:短信模板-可在短信控制台中找到
            request.setTemplateCode("1");
            request.setTemplateParam("{\"code\":\"" + code + "\"}");
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
            if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
                return true;
            }
            throw new RuntimeException(String.format("短信发送失败: message = %s", sendSmsResponse.getMessage()));
        }
        return true;
    }

    /**
     * 根据手机号发送短信
     * @author Zhibin Liu
     * @time 9/12/18 18:06
     */
    public static boolean sendSms(String tel) throws ClientException {
        return sendSms(Arrays.asList(tel), "1235");
    }

    static String appendTels(List<String> tels) {
        StringBuilder sb = new StringBuilder();
        for (String tel: tels) {
            sb.append(tel).append(",");
        }
        return sb.substring(0, sb.length() - 1);
    }

    /**
     * 分隔手机号集合
     * @author Zhibin Liu
     * @time 9/12/18 18:39
     */
    static List<List<String>> splitTels(List<String> tels, int split) {
        if (tels.size() <= split)
            return Arrays.asList(tels);
        List<List<String>> splitList = new ArrayList<>();
        if (tels.size() > 1000) {
            int be;
            int size = tels.size();
            int loop = (int) Math.ceil((double)tels.size() / split);
            List<String> subList;

            for (int i = 0; i < loop; i++) {
                int remaining = size - (be = (i * split));

                if (remaining > split) {
                    subList = tels.subList(be, (i + 1) * split);
                } else {
                    subList = tels.subList(be, be + remaining);
                }
                splitList.add(subList);
            }
        }
        return splitList;
    }
}

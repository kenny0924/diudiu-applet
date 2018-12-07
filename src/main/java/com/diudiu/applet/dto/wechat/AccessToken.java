package com.diudiu.applet.dto.wechat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccessToken extends Base{

    // 访问token
    private String access_token;

    // 存活时间 以秒为单位
    private Long expires_in;
}

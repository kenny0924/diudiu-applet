package com.diudiu.applet.dto.wechat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Base {
    private String errcode;

    public boolean isSuccess() {
        return errcode == null;
    }
}

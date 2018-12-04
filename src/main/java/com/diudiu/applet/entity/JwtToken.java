package com.diudiu.applet.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @author 刘智斌
 * @version 0.1
 * @time 12/28/17
 * @since 0.1
 */
@Data
public class JwtToken {

    /**
     * 主键
     **/
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 用户ID
     **/
    private String tokenUuid;
    /**
     * 用户ID
     **/
    private Long userId;
    /**
     * Token
     **/
    private String token;

    public JwtToken() {
    }

    public JwtToken(String tokenUuid, Long userId, String token) {
        this.tokenUuid = tokenUuid;
        this.userId = userId;
        this.token = token;
    }

}

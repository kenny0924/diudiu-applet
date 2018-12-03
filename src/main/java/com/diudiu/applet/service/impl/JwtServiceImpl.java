package com.diudiu.applet.service.impl;

import com.diudiu.applet.dto.JWTInfo;
import com.diudiu.applet.entity.JwtToken;
import com.diudiu.applet.mapper.JwtMapper;
import com.diudiu.applet.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @author 刘智斌
 * @version 0.1
 * @time 12/28/17
 * @since 0.1
 */
@Service
@Transactional
public class JwtServiceImpl implements JwtService {

    @Autowired
    JwtMapper jwtMapper;

    @Override
    public JwtToken queryJwtTokenByUserUuid(Long userId) {
        return jwtMapper.queryJwtTokenByUserUuid(userId);
    }

    @Override
    public Long doCreateJwt(JwtToken jwtToken) {
        JwtToken token = jwtMapper.queryJwtTokenByUserUuid(jwtToken.getUserId());
        if (token != null) {
            // 存在则修改
            jwtMapper.doModifyJwtByUserUuid(jwtToken);
            // 删除Redis原来Token的数据
            // TODO: 12/3/18
//            tokenTemplate.delete(token.getTokenUuid());
        } else {
            // 不存在创建一条Token
            jwtMapper.doCreateJwt(jwtToken);
        }
        // 将Token放入Redis
        // TODO: 12/3/18
//        tokenTemplate.opsForValue().set(jwtToken.getTokenUuid(), jwtToken.getToken());
        return jwtToken.getId();
    }

    @Override
    public Integer doDeleteJwtByTokenUuid(String uuid) {
        // Token失效 删除数据
//        tokenTemplate.delete(uuid);
        // TODO: 12/3/18
        return jwtMapper.doDeleteJwtByTokenUuid(uuid);
    }

}

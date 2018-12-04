package com.diudiu.applet.service.impl;

import com.diudiu.applet.dto.JWTInfo;
import com.diudiu.applet.dto.LoginDto;
import com.diudiu.applet.entity.User;
import com.diudiu.applet.mapper.UserMapper;
import com.diudiu.applet.service.UserService;
import com.diudiu.applet.web.jwt.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 刘智斌
 * @version 0.1
 * @time 12/3/18
 * @since 0.1
 */
@Transactional
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public List<User> selectByMap(Map<String, Object> map) {
        return userMapper.selectByMap(map);
    }

    @Override
    public JWTInfo doCreateUserAndToken(LoginDto dto) throws Exception {
        User user = new User();
        user.setUserTel(dto.getUserTel());
        user.setCreateTime(new Date());
        int rs = userMapper.insert(user);


        JWTInfo info = new JWTInfo();
        info.setUserId(user.getId());
        info.setTel(dto.getUserTel());
        info = jwtHelper.createAuthenticationToken(info);
        return info;
    }
}

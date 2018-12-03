package com.diudiu.applet.service.impl;

import com.diudiu.applet.entity.User;
import com.diudiu.applet.mapper.UserMapper;
import com.diudiu.applet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public List<User> selectByMap(Map<String, Object> map) {
        return userMapper.selectByMap(map);
    }
}

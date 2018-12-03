package com.diudiu.applet.service;

import com.diudiu.applet.entity.User;

import java.util.List;
import java.util.Map;

/**
 * @author 刘智斌
 * @version 0.1
 * @time 12/3/18
 * @since 0.1
 */
public interface UserService {

    List<User> selectByMap(Map<String, Object> map);
}

package com.butter.admin.service.impl;

import com.butter.admin.bean.User;
import com.butter.admin.mapper.UserMapper;
import com.butter.admin.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@CacheConfig(cacheNames = "user")
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    /**
     * 缓存
     * @param userName
     * @return
     */
    @Cacheable(cacheNames = {"user"}, keyGenerator = "myKeyGenerator", unless = "#a0=='xrd'")
    public User getByUserName(String userName) {
        log.info("查询" + userName + "用户的信息");
        return userMapper.getByUserName(userName);
    }

    public void saveUser(User user) {
        userMapper.insertUser(user);
    }

    public User getById(Long id) {
        return userMapper.getById(id);
    }

}

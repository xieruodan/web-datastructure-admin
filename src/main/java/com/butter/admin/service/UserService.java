package com.butter.admin.service;

import com.butter.admin.bean.User;
import com.butter.admin.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface UserService {

    public User getByUserName(String userName);

    public void saveUser(User user);

    public User getById(Long id);

}

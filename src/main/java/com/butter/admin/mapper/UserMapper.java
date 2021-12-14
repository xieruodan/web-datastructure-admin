package com.butter.admin.mapper;

import com.butter.admin.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select * from user_tbl where user_name=#{userName}")
    public User getByUserName(String userName);

    public void insertUser(User user);

    @Select("select * from user_tbl where id=#{id}")
    public User getById(Long id);

}

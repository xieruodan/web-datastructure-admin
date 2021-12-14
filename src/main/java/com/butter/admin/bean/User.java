package com.butter.admin.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.Serializable;

/**
 * User保存用户信息
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("user_tbl")
public class User implements Serializable {

    private Long id;
    private String userName;
    private String password;
    private String email;
    private String roles; //用户权限

}

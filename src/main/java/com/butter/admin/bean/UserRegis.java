package com.butter.admin.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRegis {

    private String userName;
    private String password1;
    private String password2;
    private String email;

}

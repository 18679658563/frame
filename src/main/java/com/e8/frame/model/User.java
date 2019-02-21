package com.e8.frame.model;

import lombok.Data;

import java.util.Date;

@Data
public class User {

    private String id;

    private String avatar;

    private Date createTime;

    private String email;

    private Boolean enabled;

    private String password;

    private String username;

    private Date lastPasswordResetTime;


}
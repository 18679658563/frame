package com.e8.frame.model;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
public class User {

    private String id;

    private String avatar;

    private Timestamp createTime;

    private String email;

    private Boolean enabled;

    private String password;

    private String username;

    private Date lastPasswordResetTime;


}
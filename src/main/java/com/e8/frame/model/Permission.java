package com.e8.frame.model;

import lombok.Data;

import java.util.Date;

@Data
public class Permission {

    private String id;

    private String alias;

    private Date createTime;

    private String name;

    private String pid;

}
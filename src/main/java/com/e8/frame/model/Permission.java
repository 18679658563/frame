package com.e8.frame.model;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
public class Permission {

    private String id;

    private String alias;

    private Timestamp createTime;

    private String name;

    private String pid;

}
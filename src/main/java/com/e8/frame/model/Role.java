package com.e8.frame.model;

import lombok.Data;

import java.util.Date;

@Data
public class Role {

    private String id;

    private Date createTime;

    private String name;

    private String remark;

}
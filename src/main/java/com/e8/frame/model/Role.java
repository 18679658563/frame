package com.e8.frame.model;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Data
public class Role {

    private String id;

    private Timestamp createTime;

    private String name;

    private String remark;

    private List<String> permissionId;

}
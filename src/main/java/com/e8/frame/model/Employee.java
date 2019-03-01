package com.e8.frame.model;

import lombok.Data;

import java.util.Date;

@Data
public class Employee {

    private String id;

    private String name;

    private String employeeId;//员工编号

    private Boolean sex;

    private Date birthday;

    private String natives;//籍贯

    private String education;//学历

    private String email;

    private Boolean type; //员工类型

    private Date createTime;

    private Boolean stat;//逻辑删除

}
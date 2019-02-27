package com.e8.frame.model;

import lombok.Data;

import java.util.Date;

@Data
public class LogModel {

    private String id;

    private Date createTime;

    private String description;

    private String exceptionDetail;

    private String logType;

    private String method;

    private String params;

    private String requestIp;

    private String time;

    private String username;

}
package com.e8.frame.model;

import lombok.Data;

import java.util.Date;

@Data
public class Menu {

    private String id;

    private Date createTime;

    private Boolean iframe;

    private String name;

    private String component;

    private String pid;

    private Long sort;

    private String icon;

    private String path;

}
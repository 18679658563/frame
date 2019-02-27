package com.e8.frame.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Timestamp;

@Data
public class Menu {

    private String id;

    @CreatedDate
    private Timestamp createTime;

    private Boolean iframe;

    private String name;

    private String component;

    private String pid;

    private Long sort;

    private String icon;

    private String path;

}
package com.e8.frame.model;

import lombok.Data;

import java.util.Date;

@Data
public class Visits {

    private String id;

    private Date createTime;

    private String date;

    private Long ipCounts;

    private Long pvCounts;

    private String weekDay;

}
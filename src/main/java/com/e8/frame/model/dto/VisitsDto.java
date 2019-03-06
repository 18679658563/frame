package com.e8.frame.model.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Auther: sharps
 * @Date: 19-3-5 17:10
 * @Description:
 */
@Data
public class VisitsDto {

    private String id;

    private Date createTime;

    private String date;

    private Long ipCounts;

    private Long pvCounts;

    private String weekDay;

}

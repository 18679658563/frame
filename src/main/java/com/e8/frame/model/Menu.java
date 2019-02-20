package com.e8.frame.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2019-02-20
 * Time: 下午3:17
 */

@Data
public class Menu extends BaseModel implements Serializable {

    private Boolean iFrame;

    private String name;

    private String component;

    private String pid;

    private Integer sort;

    private String icon;

    private String path;
}

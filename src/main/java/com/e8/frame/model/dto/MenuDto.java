package com.e8.frame.model.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2019-02-21
 * Time: 下午3:09
 */
@Data
public class MenuDto {
    private String id;

    private String name;

    private Long sort;

    private String path;

    private String component;

    private String pid;

    private Boolean iFrame = false;

    private String icon;

    private Set<RoleDto> roles;

    private List<MenuDto> children;

    private Timestamp createTime;
}

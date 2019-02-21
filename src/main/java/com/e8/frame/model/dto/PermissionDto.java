package com.e8.frame.model.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2019-02-21
 * Time: 下午3:42
 */
@Data
public class PermissionDto {

    private Long id;

    private String name;

    private Long pid;

    private String alias;

    private Timestamp createTime;

    private List<PermissionDto> children;
}

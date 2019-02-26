package com.e8.frame.model.dto;

import lombok.Data;
import org.w3c.dom.ls.LSException;

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

    private String id;

    private String name;

    private String pid;

    private String alias;

    private Timestamp createTime;

    private List<PermissionDto> children;

    private List<RoleDto> roles;

}

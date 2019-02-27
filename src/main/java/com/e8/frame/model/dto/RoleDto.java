package com.e8.frame.model.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2019-02-21
 * Time: 下午3:10
 */
@Data
public class RoleDto {
    private String id;

    private String name;

    private String remark;

    private List<PermissionDto> permissions;

    private Timestamp createTime;

    private String menuId;

    private String permissionId;

    private String permissionIds;

}

package com.e8.frame.mapper;

import com.e8.frame.model.Permission;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2019-02-20
 * Time: 下午3:31
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    List<Permission> selectByRoleId(String roleId);
}

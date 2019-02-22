package com.e8.frame.service;

import com.e8.frame.model.Permission;

import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2019-02-21
 * Time: 上午9:44
 */
public interface IPermissionService {

    List<Permission> findPermissionByRoleIds(List<String> ids);
}

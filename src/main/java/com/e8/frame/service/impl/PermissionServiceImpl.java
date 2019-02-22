package com.e8.frame.service.impl;

import com.e8.frame.mapper.PermissionMapper;
import com.e8.frame.model.Permission;
import com.e8.frame.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2019-02-21
 * Time: 上午9:45
 */
@Service
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> findPermissionByRoleIds(List<String> ids) {
        List<Permission> permissions = permissionMapper.selectByRoleIds(ids);
        return permissions.stream().distinct().collect(Collectors.toList());
    }

}

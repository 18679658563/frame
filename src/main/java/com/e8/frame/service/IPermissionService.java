package com.e8.frame.service;

import com.e8.frame.model.Permission;
import com.e8.frame.model.dto.PermissionDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
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

    PermissionDto findById(String id);

    List<Permission> findPermission();

    List<PermissionDto> findByDto(PermissionDto dto);

    Object buildTree(List<PermissionDto> permissionDtos);

    Object getPermissionTree(List<Permission> permissions);

    List<Permission> findByPid(String id);


    @Transactional
    void deletePermission(String id);
}

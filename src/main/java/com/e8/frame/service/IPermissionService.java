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

    Object getPermissionTree(List<Permission> permissions);

    List<Permission> findByPid(String pid);

    PermissionDto findById(String id);

    List<Permission> findPermission();

    List<PermissionDto> findByDto(PermissionDto dto);

    Object buildTree(List<PermissionDto> permissionDtos);
    /**
     * add
     * @param permissionDto
     */
    void addPermission(PermissionDto permissionDto);

    void updatePermission(PermissionDto permissionDto);

    @Transactional
    void deletePermission(String id);

    List<PermissionDto> queryAll(String name);
}

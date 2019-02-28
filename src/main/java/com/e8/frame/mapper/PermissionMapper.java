package com.e8.frame.mapper;

import com.e8.frame.model.Permission;
import com.e8.frame.model.Role;
import com.e8.frame.model.dto.PermissionDto;
import com.e8.frame.model.dto.RoleDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

@Mapper
public interface PermissionMapper {

    int deleteByPrimaryKey(String id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PermissionDto record);

    int updateByPrimaryKey(Permission record);

    List<Permission> selectByRoleIds(@Param("ids") List<String> ids);

    List<Permission> selectByRoleId(String roleId);

    int deletePermissionRoleById(String id);

    List<Permission> findPermission();
    List<Permission> selectAll();

    List<Permission> selectByPid(String pid);


    List<Permission> findByPid(String pid);

    List<PermissionDto> selectByPermission(@Param("permission") Permission permission);

    List<Role> selectByPermissionId(String id);

    int deletePerRoleByPermissionId(String id);

    List<PermissionDto> selectByName(@Param("name") String name);
}
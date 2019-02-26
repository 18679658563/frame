package com.e8.frame.mapper;

import com.e8.frame.model.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PermissionMapper {

    int deleteByPrimaryKey(String id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    List<Permission> selectByRoleIds(@Param("ids") List<String> ids);

    List<Permission> selectByRoleId(String roleId);

    int deletePermissionRoleById(String id);

    List<Permission> findPermission();

    List<Permission> findByPid(String pid);

    List<Permission> selectByPermission(@Param("permission") Permission permission);
}
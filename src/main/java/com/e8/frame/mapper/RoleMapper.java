package com.e8.frame.mapper;

import com.e8.frame.model.Role;
import com.e8.frame.model.dto.RoleDto;
import com.e8.frame.tools.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleMapper {

    int deleteByPrimaryKey(String id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> selectAllRoleInfoByUserId(String userId);

    List<Role> selectAll();

    List<Role> selectByMenuId(String menuId);

    List<Role> selectByPage(@Param("role")RoleDto role,@Param("page") Page page);

    Integer count(@Param("role") RoleDto role);
}
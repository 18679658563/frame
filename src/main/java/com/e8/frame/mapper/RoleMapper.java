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

    /**
     * 根据userid查询所有角色
     * @param userId
     * @return
     */
    List<Role> selectAllRoleInfoByUserId(String userId);

    /**
     * 查询所有角色
     * @return
     */
    List<Role> selectAll();

    /**
     * 根据菜单id查询角色
     * @param menuId
     * @return
     */
    List<Role> selectByMenuId(String menuId);

    /**
     * 分页查询
     * @param role
     * @param page
     * @return
     */
    List<Role> selectByPage(@Param("role")RoleDto role,@Param("page") Page page);

    /**
     * 根据信息查询总数目
     * @param role
     * @return
     */
    Integer count(@Param("role") RoleDto role);
}
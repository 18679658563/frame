package com.e8.frame.mapper;

import com.e8.frame.model.Role;
import com.e8.frame.model.dto.RoleDto;
import com.e8.frame.tools.PageUtil;
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
    List<Role> selectByPage(@Param("role")RoleDto role,@Param("page") PageUtil page);

    /**
     * 根据信息查询总数目
     * @param role
     * @return
     */
    Integer count(@Param("role") RoleDto role);


    List<RoleDto> selectByRoleIds(@Param("ids")List<String> ids);

    /**
     * 根据菜单id查询角色菜单中间表信息是否有记录
     * @param id
     * @return
     */
    int selectMenuRoleByRoleId(String id);

    /**
     * 根据菜单id删除角色菜单中间表信息
     * @param id
     * @return
     */
    int deleteMenuRoleByRoleId(String id);

    /**
     * 根据菜单id查询用户角色中间表信息是否有记录
     * @param id
     * @return
     */
    int selectUserRoleByRoleId(String id);

    /**
     * 根据菜单id删除用户角色中间表信息
     * @param id
     * @return
     */
    int deleteUserRoleByRoleId(String id);

    /**
     * 根据菜单id查询角色权限中间表信息是否有记录
     * @param id
     * @return
     */
    int selectRolePermissionByRoleId(String id);


    /**
     * 根据菜单id删除角色权限中间表信息
     * @param id
     * @return
     */

    int deleteRolePermissionByRoleId(String id);

    void insertPermissionRole(@Param("permissionId") String permissionId,@Param("roleId") String roleId);




}
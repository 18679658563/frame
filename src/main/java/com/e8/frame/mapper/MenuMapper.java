package com.e8.frame.mapper;

import com.e8.frame.model.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

@Mapper
public interface MenuMapper {

    int deleteByPrimaryKey(String id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    /**
     * 根据角色集合id查询菜单
     * @param ids
     * @return
     */
    List<Menu> selectByRoleIds(@Param("ids") List<String> ids);

    /**
     * 根据菜单的信息查询菜单
     * @param menu
     * @return
     */
    List<Menu> selectByMenu(@Param("menu") Menu menu);

    /**
     * 根据子id查询菜单
     * @param pid
     * @return
     */
    List<Menu> selectByPid(String pid);

    /**
     * 向角色菜单中间表添加信息
     * @param roleId
     * @param menuId
     * @return
     */
    int insertRoleMenu(@Param("roleId") String roleId , @Param("menuId") String menuId);

    /**
     * 根据菜单id删除角色菜单中间表信息
     * @param id
     * @return
     */
    int deleteMenuRoleByMenuId(String id);

}
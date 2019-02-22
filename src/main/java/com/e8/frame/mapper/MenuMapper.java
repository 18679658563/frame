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

    List<Menu> selectByRoleIds(@Param("ids") List<String> ids);

    List<Menu> selectAll();

    List<Menu> selectByPid(String pid);

    int insertRoleMenu(@Param("roleId") String roleId , @Param("menuId") String menuId);

    int deleteMenuRoleByMenuId(String id);

}
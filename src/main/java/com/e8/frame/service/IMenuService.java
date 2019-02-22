package com.e8.frame.service;

import com.e8.frame.model.Menu;
import com.e8.frame.model.Role;
import com.e8.frame.model.dto.MenuDto;
import com.e8.frame.model.dto.RoleDto;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2019-02-21
 * Time: 下午3:06
 */
public interface IMenuService {


    /**
     * 根据roleid查询所有菜单
     * @param roles
     * @return
     */
    List<MenuDto> findByRoleIds(List<RoleDto> roles);

    List<MenuDto> findAll();

    MenuDto findById(String id);


    List<Menu> findByPid(String id);

    /**
     * build Tree
     * @param menuDTOS
     * @return
     */
    Map buildTree(List<MenuDto> menuDTOS);

    /**
     * buildMenus
     * @param byRoles
     * @return
     */
    Object buildMenus(List<MenuDto> byRoles);

    Object getMenuTree(List<Menu> menus);

    MenuDto addMenu(MenuDto MenuDto);

    void updataMenu(MenuDto menuDto);

    void deleteMenu(String id);

}

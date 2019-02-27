package com.e8.frame.service.impl;

import cn.hutool.core.util.StrUtil;
import com.e8.frame.mapper.MenuMapper;
import com.e8.frame.mapper.PermissionMapper;
import com.e8.frame.mapper.RoleMapper;
import com.e8.frame.model.Menu;
import com.e8.frame.model.Permission;
import com.e8.frame.model.dto.MenuDto;
import com.e8.frame.model.dto.PermissionDto;
import com.e8.frame.model.dto.RoleDto;
import com.e8.frame.model.vo.MenuMetaVo;
import com.e8.frame.model.vo.MenuVo;
import com.e8.frame.service.IMenuService;
import com.e8.frame.tools.BeanUtil;
import com.e8.frame.tools.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * Description: menu菜单业务逻辑的处理
 * User: silence
 * Date: 2019-02-21
 * Time: 下午3:21
 */
@Service
public class MenuServiceImpl implements IMenuService{

    @Autowired
    private MenuMapper menuMapper;

    /**
     * 根据角色集合查询所有菜单
     * @param roles
     * @return
     */
    @Override
    public List<MenuDto> findByRoleIds(List<RoleDto> roles) {
        List<String> ids = new ArrayList<>();
        for(RoleDto role : roles){
            ids.add(role.getId());
        }
        List<Menu> list =  menuMapper.selectByRoleIds(ids);
        List<MenuDto> list1 = BeanUtil.createBeanListByTarget(list,MenuDto.class);
        return list1.stream().distinct().collect(Collectors.toList());
    }

    /**
     * 根据菜单信息查询菜单信息
     * @param menu
     * @return
     */
    @Override
    public List<MenuDto> findByDto(MenuDto menu) {
        List<MenuDto> menuDtoList = menuMapper.selectByMenuDto(menu);
        return menuDtoList;
    }

    /**
     * 通过id查询菜单信息
     * @param id
     * @return
     */
    @Override
    public MenuDto findById(String id) {
        Menu menu = menuMapper.selectByPrimaryKey(id);
        if(menu == null){
            return null;
        }
        return BeanUtil.createBeanByTarget(menu,MenuDto.class);
    }

    /**
     * 通过pid查询menu
     * @param pid
     * @return
     */
    @Override
    public List<Menu> findByPid(String pid) {
        return menuMapper.selectByPid(pid);
    }

    /**
     * 构建菜单树结构
     * @param menuDTOS
     * @return
     */
    @Override
    public Map buildTree(List<MenuDto> menuDTOS) {
        List<MenuDto> trees = new ArrayList<>();
        if(!CollectionUtils.isEmpty(menuDTOS)){
            for (MenuDto menuDTO : menuDTOS) {
                if ("0".equals(menuDTO.getPid())) {
                    trees.add(menuDTO);
                }
                for (MenuDto it : menuDTOS) {
                    if (it.getPid().equals(menuDTO.getId())) {
                        if (menuDTO.getChildren() == null) {
                            menuDTO.setChildren(new ArrayList<MenuDto>());
                        }
                        menuDTO.getChildren().add(it);
                    }
                }
            }
        }
        Integer totalElements = menuDTOS!=null?menuDTOS.size():0;
        Map map = new HashMap();
        map.put("content",trees.size() == 0?menuDTOS:trees);
        map.put("totalElements",totalElements);
        return map;
    }

    /**
     * 构建菜单的总体结构
     * @param menuDTOS
     * @return
     */
    @Override
    public List<MenuVo> buildMenus(List<MenuDto> menuDTOS) {
        Assert.notEmpty(menuDTOS,"菜单不能为空");
        List<MenuVo> list = new LinkedList<>();
        menuDTOS.forEach(menuDTO -> {
                    if (menuDTO!=null){
                        List<MenuDto> menuDTOList = menuDTO.getChildren();
                        MenuVo menuVo = new MenuVo();
                        menuVo.setName(menuDTO.getName());
                        menuVo.setPath(menuDTO.getPath());
                        // 如果不是外链
                        if(!menuDTO.getIframe()){
                            if("0".equals(menuDTO.getPid())){
                                //一级目录需要加斜杠，不然访问不了
                                menuVo.setPath("/" + menuDTO.getPath());
                                menuVo.setComponent(StrUtil.isEmpty(menuDTO.getComponent())?"Layout":menuDTO.getComponent());
                            }else if(!StrUtil.isEmpty(menuDTO.getComponent())){
                                menuVo.setComponent(menuDTO.getComponent());
                            }
                        }
                        menuVo.setMeta(new MenuMetaVo(menuDTO.getName(),menuDTO.getIcon()));
                        if(menuDTOList!=null && menuDTOList.size()!=0){
                            menuVo.setAlwaysShow(true);
                            menuVo.setRedirect("noredirect");
                            menuVo.setChildren(buildMenus(menuDTOList));
                            // 处理是一级菜单并且没有子菜单的情况
                        } else if(menuDTO.getPid().equals(0L)){
                            MenuVo menuVo1 = new MenuVo();
                            menuVo1.setMeta(menuVo.getMeta());
                            // 非外链
                            if(!menuDTO.getIframe()){
                                menuVo1.setPath("index");
                                menuVo1.setName(menuVo.getName());
                                menuVo1.setComponent(menuVo.getComponent());
                            } else {
                                menuVo1.setPath(menuDTO.getPath());
                            }
                            menuVo.setName(null);
                            menuVo.setMeta(null);
                            menuVo.setComponent("Layout");
                            List<MenuVo> list1 = new ArrayList<MenuVo>();
                            list1.add(menuVo1);
                            menuVo.setChildren(list1);
                        }
                        list.add(menuVo);
                    }
                }
        );
        return list;
    }

    /**
     * 获取菜单树结构
     * @param menus
     * @return
     */
    @Override
    public Object getMenuTree(List<Menu> menus) {
        Assert.notEmpty(menus,"菜单不能为空");
        List<Map<String,Object>> list = new LinkedList<>();
        menus.forEach(menu -> {
                    if (menu!=null){
                        List<Menu> menuList = menuMapper.selectByPid(menu.getId());
                        Map<String,Object> map = new HashMap<>();
                        map.put("id",menu.getId());
                        map.put("label",menu.getName());
                        if(menuList!=null && menuList.size()!=0){
                            map.put("children",getMenuTree(menuList));
                        }
                        list.add(map);
                    }
                }
        );
        return list;
    }

    /**
     * 添加菜单-----添加菜单角色关系
     * @param menuDto
     * @return
     */
    @Override
    @Transactional
    public MenuDto addMenu(MenuDto menuDto) {
        menuDto.setCreateTime(new Timestamp(System.currentTimeMillis()));
        menuDto.setId(UUIDUtil.getUUID());
        int menuFlag = menuMapper.insertSelective(BeanUtil.createBeanByTarget(menuDto,Menu.class));
        if(menuFlag > 0){
            if(!CollectionUtils.isEmpty(menuDto.getRoles())){
                List<RoleDto> list = new ArrayList<>();
                for(RoleDto role : menuDto.getRoles()){
                    role.setMenuId(menuDto.getId());
                    list.add(role);
                }
                menuMapper.insertRoleMenuList(list);
            }
        }
        return menuDto;
    }

    /**
     * 修改菜单。修改菜单角色关系
     * @param menuDto
     */
    @Override
    @Transactional
    public void updataMenu(MenuDto menuDto) {
        int menuFlag =  menuMapper.updateByPrimaryKeySelective(BeanUtil.createBeanByTarget(menuDto,Menu.class));
        if(menuFlag > 0){
            menuMapper.deleteMenuRoleByMenuId(menuDto.getId());
            List<RoleDto> list = new ArrayList<>();
            if(!CollectionUtils.isEmpty(menuDto.getRoles())) {
                for (RoleDto role : menuDto.getRoles()) {
                    role.setMenuId(menuDto.getId());
                    list.add(role);
                }
                menuMapper.insertRoleMenuList(list);
            }
        }
    }

    /**
     * 先删除中间表在删除menu表
     * @param id
     */
    @Override
    @Transactional
    public void deleteMenu(String id) {
        menuMapper.deleteMenuRoleByMenuId(id);
        menuMapper.deleteByPrimaryKey(id);
    }
}

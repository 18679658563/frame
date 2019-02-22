package com.e8.frame.service.impl;

import cn.hutool.core.util.StrUtil;
import com.e8.frame.mapper.MenuMapper;
import com.e8.frame.model.Menu;
import com.e8.frame.model.Role;
import com.e8.frame.model.dto.MenuDto;
import com.e8.frame.model.dto.RoleDto;
import com.e8.frame.model.vo.MenuMetaVo;
import com.e8.frame.model.vo.MenuVo;
import com.e8.frame.service.IMenuService;
import com.e8.frame.tools.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2019-02-21
 * Time: 下午3:21
 */
@Service
public class MenuServiceImpl implements IMenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<MenuDto> findByRoleIds(Set<RoleDto> roles) {
        List<String> ids = new ArrayList<>();
        for(RoleDto role : roles){
            System.out.println(role.getId());
            ids.add(role.getId());
        }
        Set<Menu> set =  menuMapper.selectByRoleIds(ids);
        List<Menu> list = new ArrayList<>();
        for(Menu menu : set){
            list.add(menu);
        }
        //取出不重复数据
       // List<String> ids = roles.stream().map(d -> d.getId()).collect(Collectors.toList());
        List<MenuDto> list1 = BeanUtil.createBeanListByTarget(list,MenuDto.class);
        return list1.stream().distinct().collect(Collectors.toList());
    }

    @Override
    public Map buildTree(List<MenuDto> menuDTOS) {
        List<MenuDto> trees = new ArrayList<>();

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

        Integer totalElements = menuDTOS!=null?menuDTOS.size():0;
        Map map = new HashMap();
        map.put("content",trees.size() == 0?menuDTOS:trees);
        map.put("totalElements",totalElements);
        return map;
    }

    @Override
    public List<MenuVo> buildMenus(List<MenuDto> menuDTOS) {
        List<MenuVo> list = new LinkedList<>();
        menuDTOS.forEach(menuDTO -> {
                    if (menuDTO!=null){
                        List<MenuDto> menuDTOList = menuDTO.getChildren();
                        MenuVo menuVo = new MenuVo();
                        menuVo.setName(menuDTO.getName());
                        menuVo.setPath(menuDTO.getPath());
                        // 如果不是外链
                        if(!menuDTO.getIFrame()){
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
                            if(!menuDTO.getIFrame()){
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
}

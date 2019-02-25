package com.e8.frame.service.impl;

import com.e8.frame.mapper.PermissionMapper;
import com.e8.frame.mapper.RoleMapper;
import com.e8.frame.model.Permission;
import com.e8.frame.model.Role;
import com.e8.frame.model.dto.PermissionDto;
import com.e8.frame.model.dto.RoleDto;
import com.e8.frame.service.IRoleService;
import com.e8.frame.tools.BeanUtil;
import com.e8.frame.tools.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Auther: sharps
 * @Date: 19-2-21 16:12
 * @Description:
 */
@Service
public class RoleServiceImpl implements IRoleService{


    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    /**
     * 根据userid查询所有角色信息
     * @param userId
     * @return
     */
    @Override
    public List<RoleDto> findByUserId(String userId) {
        List<Role> list = roleMapper.selectAllRoleInfoByUserId(userId);
        if(CollectionUtils.isEmpty(list)){
            return null;
        }
        return  BeanUtil.createBeanListByTarget(list,RoleDto.class);
    }

    /**
     * 获取角色树结构
     * @return
     */
    @Override
    public Object getRoleTree() {
        List<Role> roleList = roleMapper.selectAll();
        List<Map<String,Object>> list = new ArrayList<>();
        for(Role role : roleList){
            Map<String,Object> map = new HashMap<>();
            map.put("id",role.getId());
            map.put("label",role.getName());
            list.add(map);
        }
        return list;
    }

    /**
     * 根据分页和角色信息分页查询角色
     * @param role
     * @param page
     * @return
     */
    @Override
    public Object findAll(RoleDto role, PageUtil page) {
        List<Role> roleList = roleMapper.selectByPage(role,page);
        List<String> ids = roleList.stream().map(d -> d.getId()).collect(Collectors.toList());
        List<RoleDto> roleDtoList = roleMapper.selectByRoleIds(ids);
        List<Permission> permissionList = permissionMapper.selectAll();
        for(RoleDto roleDto : roleDtoList){
            List<PermissionDto> dtoList = new ArrayList<>();
            if(!StringUtils.isEmpty(roleDto.getPermissionIds())){
                String[] permissionIds = roleDto.getPermissionIds().split(",");
                for(Permission permission : permissionList){
                    for(String permissionId : permissionIds){
                        if((permission.getId()).equals(permissionId)){
                            PermissionDto dto = BeanUtil.createBeanByTarget(permission,PermissionDto.class);
                            dtoList.add(dto);
                        }
                    }
                }
            }
            roleDto.setPermissions(dtoList);
        }
//        for(RoleDto roleDto : result){
//            List<Permission> list = permissionMapper.selectByRoleId(roleDto.getId());
//            List<PermissionDto> permissionDtoList = BeanUtil.createBeanListByTarget(list,PermissionDto.class);
//            roleDto.setPermissions(permissionDtoList);
//        }
        Integer count = roleMapper.count(role);
        page.setList(roleDtoList);
        page.setCount(count);
        return PageUtil.toResult(page);
    }



}

package com.e8.frame.service.impl;

import com.e8.frame.mapper.PermissionMapper;
import com.e8.frame.mapper.RoleMapper;
import com.e8.frame.model.Permission;
import com.e8.frame.model.Role;
import com.e8.frame.model.dto.PermissionDto;
import com.e8.frame.model.dto.RoleDto;
import com.e8.frame.service.IRoleService;
import com.e8.frame.tools.BeanUtil;
import com.e8.frame.tools.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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

    @Override
    public List<RoleDto> findByUserId(String userId) {
        List<Role> list = roleMapper.selectAllRoleInfoByUserId(userId);
        if(CollectionUtils.isEmpty(list)){
            return null;
        }
        return  BeanUtil.createBeanListByTarget(list,RoleDto.class);
    }

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

    @Override
    public Object findAll(RoleDto role, Page page) {
        if(page.getPage() != 0){
            page.setPage(page.getPage()*page.getSize());
        }
        List<Role> roleList = roleMapper.selectByPage(role,page);
        List<RoleDto>  result = BeanUtil.createBeanListByTarget(roleList,RoleDto.class);
        for(RoleDto roleDto : result){
            List<Permission> list = permissionMapper.selectByRoleId(roleDto.getId());
            List<PermissionDto> permissionDtoList = BeanUtil.createBeanListByTarget(list,PermissionDto.class);
            roleDto.setPermissions(permissionDtoList);
        }
        Integer count = roleMapper.count(role);
        page.setList(result);
        page.setCount(count);
        return Page.toResult(page);
    }
}

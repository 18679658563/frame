package com.e8.frame.service.impl;

import com.e8.frame.mapper.PermissionMapper;
import com.e8.frame.mapper.RoleMapper;
import com.e8.frame.model.Menu;
import com.e8.frame.model.Permission;
import com.e8.frame.model.Role;
import com.e8.frame.model.dto.PermissionDto;
import com.e8.frame.model.dto.RoleDto;
import com.e8.frame.service.IRoleService;
import com.e8.frame.tools.BeanUtil;
import com.e8.frame.tools.PageUtil;
import com.e8.frame.tools.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
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
        List<RoleDto> roleDtoList = roleMapper.selectByPage(role,page);
        Integer count = roleMapper.count(role);
        page.setList(roleDtoList);
        page.setCount(count);
        return PageUtil.toResult(page);
    }


    /**
     * 先删除中间表在删除Role表
     * @param id
     */
    @Override
    @Transactional
    public void deleteRole(String id) {
        if(roleMapper.selectMenuRoleByRoleId(id)>0) {
            roleMapper.deleteMenuRoleByRoleId(id);
        }
        if(roleMapper.selectUserRoleByRoleId(id)>0) {
            roleMapper.deleteUserRoleByRoleId(id);
        }
        if(roleMapper.selectRolePermissionByRoleId(id)>0) {
            roleMapper.deleteRolePermissionByRoleId(id);
        }
        roleMapper.deleteByPrimaryKey(id);
    }

    /**
     * 添加角色
     * @param roleDto
     * @return
     */
    @Override
    @Transactional
    public RoleDto addRole(RoleDto roleDto){
        roleDto.setId(UUIDUtil.getUUID());
        roleDto.setCreateTime(new Timestamp(System.currentTimeMillis()));
        int roleFlag = roleMapper.insertSelective(BeanUtil.createBeanByTarget(roleDto,Role.class));
        if(roleFlag > 0){
            if(!CollectionUtils.isEmpty(roleDto.getPermissions())){
                for(PermissionDto permission : roleDto.getPermissions()){
                    roleMapper.insertPermissionRole(permission.getId(),roleDto.getId());
                }
            }
        }
        return roleDto;
    }


    /**
     * 修改角色信息
     * @param roleDto
     * @return
     */
    @Override
    @Transactional
    public void updataRole(RoleDto roleDto){
        int roleFlag =  roleMapper.updateByPrimaryKeySelective(BeanUtil.createBeanByTarget(roleDto,Role.class));
        if(roleFlag > 0){
//            roleMapper.deleteMenuRoleByMenuId(menuDto.getId());
//            List<RoleDto> list = new ArrayList<>();
//            if(!CollectionUtils.isEmpty(menuDto.getRoles())) {
//                for (RoleDto role : menuDto.getRoles()) {
//                    role.setMenuId(menuDto.getId());
//                    list.add(role);
//                }
//                menuMapper.insertRoleMenuList(list);
//            }
        }

    }


}

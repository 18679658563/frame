package com.e8.frame.service.impl;

import com.e8.frame.mapper.PermissionMapper;
import com.e8.frame.mapper.RoleMapper;
import com.e8.frame.model.Permission;
import com.e8.frame.model.Role;
import com.e8.frame.model.dto.PermissionDto;
import com.e8.frame.model.dto.RoleDto;
import com.e8.frame.service.IPermissionService;
import com.e8.frame.tools.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * Description:  权限的业务逻辑处理
 * User: silence
 * Date: 2019-02-21
 * Time: 上午9:45
 */
@Service
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private RoleMapper roleMapper;



    @Override
    public List<Permission> findPermissionByRoleIds(List<String> ids) {
        List<Permission> permissions = permissionMapper.selectByRoleIds(ids);
       return permissions.stream().distinct().collect(Collectors.toList());
    }

    @Override
    public PermissionDto findById(String id) {
       Permission permission =permissionMapper.selectByPrimaryKey(id);
       if (permission == null){
           return null;
       }
        return BeanUtil.createBeanByTarget(permission,PermissionDto.class);
    }

    @Override
    public List<Permission> findPermission() {
        return permissionMapper.findPermission();
    }

    @Override
    public List<PermissionDto> findByDto(PermissionDto dto) {
        Permission permission =BeanUtil.createBeanByTarget(dto,Permission.class);
        List<Permission> permissions =permissionMapper.selectByPermission(permission);
        if(CollectionUtils.isEmpty(permissions)){
            return null;
        }
        List<Role> roleList = null;
        List<PermissionDto> lists = BeanUtil.createBeanListByTarget(permissions,PermissionDto.class);
        for(PermissionDto permissionDto : lists){
            roleList = roleMapper.selectByPermissionId(permissionDto.getId());
            List<PermissionDto> permissionDtoList = null;
            List<RoleDto> roleDtoList = BeanUtil.createBeanListByTarget(roleList,RoleDto.class);
            for(RoleDto role : roleDtoList){
                List<Permission> permissionList = permissionMapper.selectByRoleId(role.getId());
                permissionDtoList = BeanUtil.createBeanListByTarget(permissionList,PermissionDto.class);
                role.setPermissions(permissionDtoList);
            }
            permissionDto.setRoles(roleDtoList);
        }
        return lists;
    }

    @Override
    public Object buildTree(List<PermissionDto> permissionDtos) {
        List<PermissionDto> trees = new ArrayList<PermissionDto>();

        if(!CollectionUtils.isEmpty(permissionDtos)){
            for(PermissionDto permissionDto : permissionDtos){

                if("0".equals((permissionDto.getPid()))){
                    trees.add(permissionDto);
                }

                for(PermissionDto p :permissionDtos){
                    if (p.getPid().equals(permissionDto.getPid())){
                        if(permissionDto.getChildren() == null){
                            permissionDto.setChildren(new ArrayList<PermissionDto>());
                        }
                        permissionDto.getChildren().add(p);
                    }
                }
            }

        }
        Integer totalElements = permissionDtos!=null?permissionDtos.size():0;

        Map map =new HashMap();
        map.put("content",trees.size() == 0?permissionDtos:trees);
        map.put("totalElements",totalElements);
        return map;
    }

    @Override
    public Object getPermissionTree(List<Permission> permissions) {
        List<Map<String,Object>> list = new LinkedList<>();
        permissions.forEach(permission -> {
                    if (permission!=null){
                        List<Permission> permissionList = permissionMapper.findByPid(permission.getId());
                        Map<String,Object> map = new HashMap<>();
                        map.put("id",permission.getId());
                        map.put("label",permission.getAlias());
                        if(permissionList!=null && permissionList.size()!=0){
                            map.put("children",getPermissionTree(permissionList));
                            System.out.println("3333:"+permissionList);
                        }
                        list.add(map);
                    }
                }
        );
        return list;
    }


    /**
     * 通过pid查询menu
     * @param pid
     * @return
     */
    @Override
    public List<Permission> findByPid(String pid) {
        return permissionMapper.findByPid(pid);
    }
    /**
     * 先删除中间表在删除menu表
     * @param id
     */
    @Override
    @Transactional
    public void deletePermission(String id) {
        permissionMapper.deletePermissionRoleById(id);
        permissionMapper.deleteByPrimaryKey(id);
    }
}

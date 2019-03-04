package com.e8.frame.service.impl;

import com.e8.frame.mapper.PermissionMapper;
import com.e8.frame.model.Permission;
import com.e8.frame.model.dto.PermissionDto;
import com.e8.frame.service.IPermissionService;
import com.e8.frame.tools.BeanUtil;
import com.e8.frame.tools.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.sql.Timestamp;
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
@CacheConfig(cacheNames = "permission")
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> findPermissionByRoleIds(List<String> ids) {
        List<Permission> permissions = permissionMapper.selectByRoleIds(ids);
        return permissions.stream().distinct().collect(Collectors.toList());
    }

    @Override
    @Cacheable(key = "#p0")
    public PermissionDto findById(String id) {
        Permission permission = permissionMapper.selectByPrimaryKey(id);
        if (permission == null) {
            return null;
        }
        return BeanUtil.createBeanByTarget(permission, PermissionDto.class);
    }

    @Override
    @Cacheable(key = "queryAll")
    public List<Permission> findPermission() {
        return permissionMapper.findPermission();
    }

    @Override
    @Cacheable(key = "'dto:'+#p0")
    public List<PermissionDto> findByDto(PermissionDto dto) {

        Permission permission = BeanUtil.createBeanByTarget(dto, Permission.class);
        List<PermissionDto> permissions = permissionMapper.selectByPermission(permission);
        if (CollectionUtils.isEmpty(permissions)) {
            return null;
        }
        return permissions;
    }

    @Override
    public Object buildTree(List<PermissionDto> permissionDtos) {
        List<PermissionDto> trees = new ArrayList<PermissionDto>();

        if (!CollectionUtils.isEmpty(permissionDtos)) {
            for (PermissionDto permissionDto : permissionDtos) {

                if ("0".equals((permissionDto.getPid()))) {
                    trees.add(permissionDto);
                }
                for (PermissionDto p : permissionDtos) {
                    if (p.getPid().equals(permissionDto.getId())) {
                        if (permissionDto.getChildren() == null) {
                            permissionDto.setChildren(new ArrayList<PermissionDto>());

                        }
                        permissionDto.getChildren().add(p);
                    }
                }
            }

        }
        Integer totalElements = permissionDtos != null ? permissionDtos.size() : 0;

        Map map = new HashMap();
        map.put("content", trees.size() == 0 ? permissionDtos : trees);
        map.put("totalElements", totalElements);
        return map;
    }

    /**
     * 通过pid查询
     *
     * @param pid
     * @return
     */
    @Override
    @Cacheable(key = "'pid:'+#p0")
    public List<Permission> findByPid(String pid) {
        return permissionMapper.findByPid(pid);
    }

    /**
     * @param permissionDto
     * @return
     */
    @Override
    @CacheEvict(allEntries = true)
    public void addPermission(PermissionDto permissionDto) {
        Permission p = BeanUtil.createBeanByTarget(permissionDto, Permission.class);
        String uuid = UUIDUtil.getUUID();
        p.setId(uuid);
        p.setAlias(permissionDto.getAlias());
        p.setCreateTime(new Timestamp(new Date().getTime()));
        p.setName(permissionDto.getName());
        permissionMapper.insertSelective(p);
    }

    @Override
    @Transactional
    @CacheEvict(allEntries = true)
    public void updatePermission(PermissionDto permissionDto) {
        permissionMapper.updateByPrimaryKeySelective(permissionDto);
    }

    /**
     * 先删除中间表再删除权限表
     *
     * @param id
     */
    @Override
    @Transactional
    @CacheEvict(allEntries = true)
    public void deletePermission(String id) {
        deletePermissionAndPerRoles(id);
        List<Permission> permissions = permissionMapper.selectByPid(id);
        if (!permissions.isEmpty()) {
            deletePermissions(permissions);
        }
    }

    public void deletePermissionAndPerRoles(String PermissionId) {
        permissionMapper.deletePerRoleByPermissionId(PermissionId);
        permissionMapper.deleteByPrimaryKey(PermissionId);
    }

    public void deletePermissions(List<Permission> list) {
        for (Permission permission : list) {
            deletePermission(permission.getId());
        }
    }

    @Override
    public Object getPermissionTree(List<Permission> permissions) {
        List<Map<String, Object>> list = new LinkedList<>();
        permissions.forEach(permission -> {
                    if (permission != null) {
                        List<Permission> permissionList = permissionMapper.selectByPid(permission.getId());
                        Map<String, Object> map = new HashMap<>();
                        map.put("id", permission.getId());
                        map.put("label", permission.getAlias());
                        if (permissionList != null && permissionList.size() != 0) {
                            map.put("children", getPermissionTree(permissionList));
                        }
                        list.add(map);
                    }
                }
        );
        return list;
    }

    @Override
    @Cacheable(key = "#p0")
    public List<PermissionDto> queryAll(String name) {
        return permissionMapper.selectByName(name);
    }

}

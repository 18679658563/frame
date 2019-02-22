package com.e8.frame.service.impl;

import com.e8.frame.mapper.RoleMapper;
import com.e8.frame.model.Role;
import com.e8.frame.model.dto.RoleDto;
import com.e8.frame.service.IRoleService;
import com.e8.frame.tools.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @Auther: sharps
 * @Date: 19-2-21 16:12
 * @Description:
 */
@Service
public class RoleServiceImpl implements IRoleService{


    @Autowired
    private RoleMapper roleMapper;

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
}

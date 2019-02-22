package com.e8.frame.service.impl;

import com.e8.frame.mapper.RoleMapper;
import com.e8.frame.model.Role;
import com.e8.frame.model.dto.RoleDto;
import com.e8.frame.service.IRoleService;
import com.e8.frame.tools.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
}

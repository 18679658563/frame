package com.e8.frame.config.security;

import com.e8.frame.exception.EntityNotFoundException;
import com.e8.frame.mapper.PermissionMapper;
import com.e8.frame.mapper.RoleMapper;
import com.e8.frame.mapper.UserMapper;
import com.e8.frame.model.Permission;
import com.e8.frame.model.Role;
import com.e8.frame.model.User;
import com.e8.frame.model.vo.UserVo;
import com.e8.frame.tools.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Auther: sharps
 * @Date: 19-2-20 18:11
 * @Description:
 */
@Service
public class JwtUserDetailsService implements UserDetailsService {


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username){

        User user  = userMapper.selectByUsername(username);
        UserVo vo = BeanUtil.createBeanByTarget(user,UserVo.class);
        vo.setRoles(roleMapper.selectAllRoleInfoByUserId(user.getId()));
        if (user == null) {
            throw new EntityNotFoundException(UserVo.class, "name", username);
        } else {
            return create(vo);
        }
    }

    public UserDetails create(UserVo user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getAvatar(),
                user.getEmail(),
                mapToGrantedAuthorities(user.getRoles(),permissionMapper),
                user.getEnabled(),
                user.getCreateTime(),
                user.getLastPasswordResetTime()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> roles, PermissionMapper permissionMapper) {
        Set<Permission> permissions = new HashSet<>();
        List<String> ids = new ArrayList<>();
        for (Role role : roles) {
            Set<Role> roleSet = new HashSet<>();
            roleSet.add(role);
            for(Role r : roleSet){
                ids.add(r.getId());
            }
            permissions.addAll(permissionMapper.selectByRoleIds(ids));
        }
        if(CollectionUtils.isEmpty(permissions)){
            Permission per = new Permission();
            per.setId("base");
            per.setName("base");
            per.setAlias("base");
            per.setCreateTime(new Timestamp(new Date().getTime()));
            per.setPid("0");
            permissions.add(per);
        }
        return permissions.stream()
                .map(permission -> new SimpleGrantedAuthority("ROLE_"+permission.getName()))
                .collect(Collectors.toList());
    }


}


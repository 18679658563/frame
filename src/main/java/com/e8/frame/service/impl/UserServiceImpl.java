package com.e8.frame.service.impl;

import com.e8.frame.exception.BadRequestException;
import com.e8.frame.exception.EntityExistException;
import com.e8.frame.mapper.RoleMapper;
import com.e8.frame.mapper.UserMapper;
import com.e8.frame.model.User;
import com.e8.frame.model.dto.RoleDto;
import com.e8.frame.model.dto.UserDto;
import com.e8.frame.service.IUserService;
import com.e8.frame.tools.BeanUtil;
import com.e8.frame.tools.PageUtil;
import com.e8.frame.tools.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;


/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2019-02-20
 * Time: 下午5:27
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;


    @Override
    public UserDto findByUsername(String name) {
        User user = userMapper.selectByUsername(name);
        UserDto dto = null;
        if(user != null){
            dto = BeanUtil.createBeanByTarget(user,UserDto.class);
        }
        return dto;
    }

    @Override
    public List<UserDto> getUsersByPage(UserDto user,PageUtil page) {
        List<UserDto> list = userMapper.getUsersByPage(user,page);
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertSelective(UserDto user) {
        if(userMapper.selectByUsername(user.getUsername())!=null){
            throw new EntityExistException(UserDto.class,"username",user.getUsername());
        }
        if(userMapper.selectByEmail(user.getEmail())!=null){
            throw new EntityExistException(UserDto.class,"email",user.getEmail());
        }
        if(user.getRoles().isEmpty()){
            throw new BadRequestException("角色不能为空");
        }
        User u = BeanUtil.createBeanByTarget(user, User.class);
        String uuid = UUIDUtil.getUUID();
        u.setId(uuid);
        // 默认密码 123456
        u.setPassword("14e1b600b1fd579f47433b88e8d85291");
        u.setAvatar("https://i.loli.net/2018/12/06/5c08894d8de21.jpg");
        u.setCreateTime(new Timestamp(new Date().getTime()));
        userMapper.insertSelective(u);
        List<RoleDto> roles = user.getRoles();
        if(!roles.isEmpty()){
            insertUserRole(roles,uuid);
        }
    }

    @Override
    public void deleteUserAndUserRolesByUserId(String userId) {
        userMapper.deleteUserRolesByUserId(userId);
        userMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public void updateUserAndUserRoles(UserDto userDto) {
        userMapper.updateByPrimaryKeySelective(userDto);
        userMapper.deleteUserRolesByUserId(userDto.getId());
        List<RoleDto> roles = userDto.getRoles();
        if(!roles.isEmpty()){
            insertUserRole(roles,userDto.getId());
        }
    }
    public void insertUserRole(List<RoleDto> roles,String UserId){
        List<Map> userRoleList = new ArrayList<>();
        for(RoleDto role:roles){
            Map map = new HashMap<String, String>();
            map.put("userId", UserId);
            map.put("roleId",role.getId());
            userRoleList.add(map);
        }
        userMapper.insertUserRole(userRoleList);
    }
}

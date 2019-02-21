package com.e8.frame.service.impl;

import com.e8.frame.mapper.UserMapper;
import com.e8.frame.model.User;
import com.e8.frame.model.dto.UserDto;
import com.e8.frame.service.IUserService;
import com.e8.frame.tools.BeanUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    @Override
    public UserDto findByUsername(String name) {
        User user = userMapper.selectByUsername(name);
        UserDto dto = null;
        if(user != null){
            dto = BeanUtil.createBeanByTarget(user,UserDto.class);
        }
        return dto;
    }
}

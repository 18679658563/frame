package com.e8.frame.mapper;

import com.e8.frame.model.User;
import com.e8.frame.model.vo.UserVo;

public interface UserMapper {

    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByUsername(String username);
}
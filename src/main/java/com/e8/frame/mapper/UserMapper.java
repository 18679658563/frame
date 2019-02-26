package com.e8.frame.mapper;

import com.e8.frame.model.Role;
import com.e8.frame.model.User;
import com.e8.frame.model.dto.UserDto;
import com.e8.frame.tools.PageUtil;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserDto record);

    int updateByPrimaryKey(User record);

    User selectByUsername(String username);

    int selectCount();

    List<UserDto> getUsersByPage(UserDto user,PageUtil page);

    User selectByEmail(String Email);

    int insertUserRole(List<Map> list);

    int deleteUserRolesByUserId(String UserId);

    List<String> getUserRoleIdsByUserId(String UserId);

}
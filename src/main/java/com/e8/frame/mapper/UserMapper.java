package com.e8.frame.mapper;

import com.e8.frame.model.User;
import com.e8.frame.model.dto.UserDto;
import com.e8.frame.tools.PageUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

    List<UserDto> getUsersByPage(@Param("user") UserDto user, @Param("page") PageUtil page);

    User selectByEmail(String Email);

    int insertUserRole(List<Map> list);

    int deleteUserRolesByUserId(String UserId);

    //
    int updatePwd(@Param("id")String id, @Param("pass") String pass);

}
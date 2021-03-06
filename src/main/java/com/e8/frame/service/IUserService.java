package com.e8.frame.service;

import com.e8.frame.config.security.JwtUser;
import com.e8.frame.model.dto.UserDto;
import com.e8.frame.tools.PageUtil;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2019-02-20
 * Time: 下午3:05
 */
public interface IUserService {

    UserDto findByUsername(String name);

    List<UserDto> getUsersByPage(UserDto user,PageUtil page);

    void insertSelective(UserDto user);

    void deleteUserAndUserRolesByUserId(String UserId);

    void updateUserAndUserRoles(UserDto userDto);

    /**
     * 修改密码
     * @param userId
     * @param encryptPassword
     */
    void updatePass(String userId, String encryptPassword);
}

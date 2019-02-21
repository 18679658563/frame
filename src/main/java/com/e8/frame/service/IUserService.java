package com.e8.frame.service;

import com.e8.frame.model.dto.UserDto;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2019-02-20
 * Time: 下午3:05
 */
public interface IUserService {

    UserDto findByUsername(String name);
}

package com.e8.frame.mapper;

import com.e8.frame.model.User;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2019-02-20
 * Time: 下午3:27
 */
public interface UserMapper extends BaseMapper<User>{

    User selectByUsername(String username);

}

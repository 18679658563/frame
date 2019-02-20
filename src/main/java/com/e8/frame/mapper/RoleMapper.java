package com.e8.frame.mapper;

import com.e8.frame.model.Role;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2019-02-20
 * Time: 下午3:29
 */
public interface RoleMapper extends BaseMapper<Role> {

    List<Role> selectByUserId(String userId);
}

package com.e8.frame.mapper;

import com.e8.frame.model.Menu;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2019-02-20
 * Time: 下午3:32
 */
public interface MenuMapper extends BaseMapper<Menu>{

    List<Menu> selectByRoleId(String roleId);
}

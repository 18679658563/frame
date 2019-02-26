package com.e8.frame.service;

import com.e8.frame.model.dto.RoleDto;
import com.e8.frame.tools.PageUtil;

import java.util.List;

/**
 * @Auther: sharps
 * @Date: 19-2-21 16:11
 * @Description:
 */
public interface IRoleService {

    List<RoleDto> findByUserId(String userId);

    Object getRoleTree();

    Object findAll(RoleDto role, PageUtil page);

    void deleteRole(String id);
}

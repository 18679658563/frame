package com.e8.frame.service;

import com.e8.frame.model.dto.RoleDto;

import java.util.Set;

/**
 * @Auther: sharps
 * @Date: 19-2-21 16:11
 * @Description:
 */
public interface IRoleService {

    Set<RoleDto> findByUserId(String userId);
}

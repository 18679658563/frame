package com.e8.frame.controller;

import com.e8.frame.model.dto.RoleDto;
import com.e8.frame.service.IRoleService;
import com.e8.frame.tools.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2019-02-22
 * Time: 上午11:14
 */
@Controller
@RequestMapping("/api")
public class RoleController {


    @Autowired
    private IRoleService roleService;

    /**
     * 分页查询所有角色
     * @param resources
     * @param pageable
     * @return
     */
    @GetMapping(value = "/roles")
    @PreAuthorize("hasAnyRole('ADMIN','ROLES_ALL','ROLES_SELECT')")
    public ResponseEntity getRoles(RoleDto resources, Page pageable){//pageNumber&pageSize
        return new ResponseEntity(roleService.findAll(resources,pageable),HttpStatus.OK);
    }


    /**
     * 返回全部的角色，新增用户时下拉选择
     * @return
     */
    @GetMapping(value = "/roles/tree")
    @PreAuthorize("hasAnyRole('ADMIN','MENU_ALL','MENU_SELECT','ROLES_ALL','USER_ALL','USER_SELECT')")
    public ResponseEntity getRoleTree(){
        return new ResponseEntity(roleService.getRoleTree(), HttpStatus.OK);
    }

}

package com.e8.frame.controller;

import com.e8.frame.model.Permission;
import com.e8.frame.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Auther: sharps
 * @Date: 19-2-26 11:12
 * @Description:
 */
@Controller
@RequestMapping("/api")
public class PermissionController {



    @Autowired
    private IPermissionService permissionService;

    /**
     * 返回全部的权限，新增角色时下拉选择
     * @return
     */
    @GetMapping(value = "/permissions/tree")
    @PreAuthorize("hasAnyRole('ADMIN','PERMISSION_ALL','PERMISSION_SELECT','ROLES_SELECT','ROLES_ALL')")
    public ResponseEntity getRoleTree(){
        List<Permission> Permissions = permissionService.findByPid("0");
        return new ResponseEntity(permissionService.getPermissionTree(Permissions), HttpStatus.OK);
    }

}

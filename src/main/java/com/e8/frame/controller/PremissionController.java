package com.e8.frame.controller;

import com.e8.frame.model.dto.PermissionDto;
import com.e8.frame.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PremissionController {

    @Autowired
    private IPermissionService permissionService;



    @GetMapping(value = "/permissions/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','PERMISSION_ALL','PERMISSION_SELECT')")
    public ResponseEntity getPermission(@PathVariable String id){
        return new ResponseEntity(permissionService.findById(id), HttpStatus.OK);
    }

    /**
     * 返回全部的权限
     * @return
     */
    @GetMapping(value = "/permission/tree")
    @PreAuthorize("hasAnyRole('ADMIN','PERMISSION_ALL','PERMISSION_SELECT','ROLES_SELECT','ROLES_ALL')")
    public ResponseEntity getPermissionTree(){
        System.out.println("权限con："+permissionService.getPermissionTree(permissionService.findByPid("0")));
        return new ResponseEntity(permissionService.getPermissionTree(permissionService.findByPid("0")),HttpStatus.OK);
    }

    @GetMapping(value = "/permissions")
    @PreAuthorize("hasAnyRole('ADMIN','PERMISSION_ALL','PERMISSION_SELECT')")
    public ResponseEntity getPermissions(@RequestParam(required = false) String name){
        PermissionDto permissionDto =new PermissionDto();
        permissionDto.setName(name);
        List<PermissionDto> permissionDtoList =permissionService.findByDto(permissionDto);
        Object map = permissionService.buildTree(permissionDtoList);
        return new ResponseEntity(map ,HttpStatus.OK);
        //permissionService.buildTree(permissionDtoList)
    }
}

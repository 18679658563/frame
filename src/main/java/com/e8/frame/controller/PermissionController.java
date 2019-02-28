package com.e8.frame.controller;

import com.e8.frame.exception.BadRequestException;
import com.e8.frame.model.dto.PermissionDto;
import com.e8.frame.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PermissionController {

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
    @GetMapping(value = "/permissions/tree")
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
    }


    @PostMapping(value = "/permissions")
    @PreAuthorize("hasAnyRole('ADMIN','PERMISSION_ALL','PERMISSION_CREATE')")
    public ResponseEntity create(@Validated @RequestBody PermissionDto resources){
       if (resources.getId() != null) {
           throw new BadRequestException("A new menu cannot already have an ID");
        }
        permissionService.addPermission(resources);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping(value = "/permissions")
    @PreAuthorize("hasAnyRole('ADMIN','PERMISSION_ALL','PERMISSION_EDIT')")
    public ResponseEntity update(@Validated @RequestBody PermissionDto resources){
        if (resources.getId() == null) {
            throw new BadRequestException( " permission ID Can not be empty");
        }
        permissionService.updatePermission(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/permissions/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','PERMISSION_ALL','PERMISSION_DELETE')")
    public ResponseEntity delete(@PathVariable String id){
        permissionService.deletePermission(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}

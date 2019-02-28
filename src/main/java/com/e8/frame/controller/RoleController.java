package com.e8.frame.controller;

import com.e8.frame.aop.Log;
import com.e8.frame.exception.BadRequestException;
import com.e8.frame.model.dto.RoleDto;
import com.e8.frame.service.IRoleService;
import com.e8.frame.tools.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity getRoles(RoleDto resources, PageUtil pageable){//pageNumber&pageSize
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


    /**
     * 根据id删除Role信息
     * @param id
     * @return
     */
    @Log(description = "删除角色")
    @DeleteMapping(value = "/roles/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','ROLES_ALL','ROLES_DELETE')")
    public ResponseEntity delete(@PathVariable String id){
        roleService.deleteRole(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * 添加角色信息
     * @param resources
     * @return
     */
    @Log(description = "添加角色")
    @PostMapping(value = "/roles")
    @PreAuthorize("hasAnyRole('ADMIN','ROLES_ALL','ROLES_CREATE')")
    public ResponseEntity create(@Validated @RequestBody RoleDto resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new role cannot already have an ID");
        }
        return new ResponseEntity(roleService.addRole(resources),HttpStatus.CREATED);
    }


    /**
     * 修改菜单信息
     * @param resources
     * @return
     */
    @Log(description = "修改角色")
    @PutMapping(value = "/roles")
    @PreAuthorize("hasAnyRole('ADMIN','MENU_ALL','MENU_EDIT')")
    public ResponseEntity update(@Validated @RequestBody RoleDto resources){
        if (resources.getId() == null) {
            throw new BadRequestException( " role ID Can not be empty");
        }
        roleService.updataRole(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}

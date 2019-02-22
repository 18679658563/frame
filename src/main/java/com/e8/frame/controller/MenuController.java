package com.e8.frame.controller;

import com.e8.frame.exception.BadRequestException;
import com.e8.frame.model.dto.MenuDto;
import com.e8.frame.model.dto.RoleDto;
import com.e8.frame.model.dto.UserDto;
import com.e8.frame.service.IMenuService;
import com.e8.frame.service.IRoleService;
import com.e8.frame.service.IUserService;
import com.e8.frame.tools.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2019-02-21
 * Time: 下午3:11
 */
@Controller
@RequestMapping("/api")
public class MenuController {

    @Autowired
    private IMenuService menuService;

    @Autowired
    private IUserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private IRoleService roleService;

    /**
     * 构建前端路由所需要的菜单
     * @return
     */
    @GetMapping(value = "/menus/build")
    public ResponseEntity buildMenus(HttpServletRequest request){
        UserDto user = userService.findByUsername(jwtTokenUtil.getUserName(request));
        List<RoleDto> roles = roleService.findByUserId(user.getId());
        List<MenuDto> menuDTOList = menuService.findByRoleIds(roles);
        return new ResponseEntity(menuService.buildMenus((List<MenuDto>)menuService.buildTree(menuDTOList).get("content")), HttpStatus.OK);
    }

    @GetMapping(value = "/menus/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','MENU_ALL','MENU_SELECT')")
    public ResponseEntity getMenu(@PathVariable String id){
        return new ResponseEntity(menuService.findById(id), HttpStatus.OK);
    }


    /**
     * 返回全部的菜单
     * @return
     */
    @GetMapping(value = "/menus/tree")
    @PreAuthorize("hasAnyRole('ADMIN','MENU_ALL','MENU_SELECT')")
    public ResponseEntity getMenuTree(){
        return new ResponseEntity(menuService.getMenuTree(menuService.findByPid("0")),HttpStatus.OK);
    }

    @GetMapping(value = "/menus")
    @PreAuthorize("hasAnyRole('ADMIN','MENU_ALL','MENU_SELECT')")
    public ResponseEntity getMenus(@RequestParam(required = false) String name){
        List<MenuDto> menuDTOList = menuService.findAll();
        return new ResponseEntity(menuService.buildTree(menuDTOList),HttpStatus.OK);
    }

    @PostMapping(value = "/menus")
    @PreAuthorize("hasAnyRole('ADMIN','MENU_ALL','MENU_CREATE')")
    public ResponseEntity create(@Validated @RequestBody MenuDto resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new menu cannot already have an ID");
        }
        return new ResponseEntity(menuService.addMenu(resources),HttpStatus.CREATED);
    }

    @PutMapping(value = "/menus")
    @PreAuthorize("hasAnyRole('ADMIN','MENU_ALL','MENU_EDIT')")
    public ResponseEntity update(@Validated @RequestBody MenuDto resources){
        if (resources.getId() == null) {
            throw new BadRequestException( " menu ID Can not be empty");
        }
        menuService.updataMenu(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/menus/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','MENU_ALL','MENU_DELETE')")
    public ResponseEntity delete(@PathVariable String id){
        menuService.deleteMenu(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
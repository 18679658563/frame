package com.e8.frame.controller;

import com.e8.frame.model.User;
import com.e8.frame.model.dto.MenuDto;
import com.e8.frame.model.dto.UserDto;
import com.e8.frame.service.IMenuService;
import com.e8.frame.service.IUserService;
import com.e8.frame.tools.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    /**
     * 构建前端路由所需要的菜单
     * @return
     */
    @GetMapping(value = "/menus/build")
    public ResponseEntity buildMenus(HttpServletRequest request){
        UserDto user = userService.findByUsername(jwtTokenUtil.getUserName(request));
        List<MenuDto> menuDTOList = menuService.findByRoleIds(user.getRoles());
        return new ResponseEntity(menuService.buildMenus((List<MenuDto>)menuService.buildTree(menuDTOList).get("content")), HttpStatus.OK);
    }
}

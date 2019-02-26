package com.e8.frame.controller;

import com.e8.frame.exception.BadRequestException;
import com.e8.frame.mapper.UserMapper;
import com.e8.frame.model.dto.UserDto;
import com.e8.frame.service.IUserService;
import com.e8.frame.tools.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description: UserController
 * @author: luruidi
 * @date: 2019-02-25
 */
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private UserMapper userMapper;

    /**
     * @param: users
     * @return: ResponseEntity
     * @description: 分页查询用户
     */
    @RequestMapping(value = "/users",method = RequestMethod.GET)
    public ResponseEntity getUsers(UserDto userDTO,PageUtil page){
        List<UserDto> list = iUserService.getUsersByPage(userDTO,page);
        PageUtil<UserDto> pageList = new PageUtil<>();
        pageList.setList(list);
        int count = userMapper.selectCount();
        pageList.setCount(count);
        return new ResponseEntity(PageUtil.toResult(pageList), HttpStatus.OK);
    }

    @RequestMapping(value = "/users",method = RequestMethod.POST)
    public ResponseEntity createUser(@RequestBody UserDto user){
        iUserService.insertSelective(user);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/users/{id}",method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable String id){
        iUserService.deleteUserAndUserRolesByUserId(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/users",method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody UserDto user){
        if (user.getId() == null) {
            throw new BadRequestException("UserId Can not be empty!");
        }
        iUserService.updateUserAndUserRoles(user);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}

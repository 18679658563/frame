package com.e8.frame.controller;

import com.e8.frame.mapper.UserMapper;
import com.e8.frame.model.dto.UserDto;
import com.e8.frame.service.IUserService;
import com.e8.frame.tools.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity getUsers(PageUtil page){
        List<UserDto> list = iUserService.getUsersByPage(page);
        PageUtil<UserDto> pageList = new PageUtil<>();
        pageList.setList(list);
        int count = userMapper.selectCount();
        pageList.setCount(count);
        return new ResponseEntity(PageUtil.toResult(pageList), HttpStatus.OK);
    }

    @RequestMapping(value = "/users",method = RequestMethod.POST)
    public ResponseEntity createUser(@RequestBody UserDto user){
        int i = iUserService.insertSelective(user);
        if(i>0){
            return new ResponseEntity("用户插入成功！", HttpStatus.CREATED);
        }
        return new ResponseEntity("用户插入失败！", HttpStatus.SERVICE_UNAVAILABLE);
    }
}

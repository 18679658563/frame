package com.e8.frame.controller;

import com.e8.frame.config.security.JwtUser;
import com.e8.frame.config.security.SecurityContextHolder;
import com.e8.frame.exception.BadRequestException;
import com.e8.frame.mapper.UserMapper;
import com.e8.frame.model.dto.UserDto;
import com.e8.frame.service.IUserService;
import com.e8.frame.tools.EncryptUtils;
import com.e8.frame.tools.PageUtil;
import com.e8.frame.tools.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.StringUtils;
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

    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;

    /**
     * @description: 分页查询用户
     * @param: users
     * @return: ResponseEntity
     */
    @RequestMapping(value = "/users",method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ADMIN','USER_ALL','USER_SELECT')")
    public ResponseEntity getUsers(UserDto userDTO,PageUtil page){
        List<UserDto> list = iUserService.getUsersByPage(userDTO,page);
        PageUtil<UserDto> pageList = new PageUtil<>();
        pageList.setList(list);
        int count = userMapper.selectCount();
        pageList.setCount(count);
        return new ResponseEntity(PageUtil.toResult(pageList), HttpStatus.OK);
    }

    /**
     * @description: 创建用户
     * @param: user
     * @return: ResponseEntity
     */
    @RequestMapping(value = "/users",method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ADMIN','USER_ALL','USER_CREATE')")
    public ResponseEntity createUser(@RequestBody UserDto user){
        iUserService.insertSelective(user);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    /**
     * @description：根据用户id删除用户
     * @param: id
     * @return: ResponseEntity
     */
    @RequestMapping(value = "/users/{id}",method = RequestMethod.DELETE)
    @PreAuthorize("hasAnyRole('ADMIN','USER_ALL','USER_DELETE')")
    public ResponseEntity delete(@PathVariable String id){
        iUserService.deleteUserAndUserRolesByUserId(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * @description: 更新用户
     * @param: user
     * @return: ResponseEntity
     */
    @RequestMapping(value = "/users",method = RequestMethod.PUT)
    @PreAuthorize("hasAnyRole('ADMIN','USER_ALL','USER_EDIT')")
    public ResponseEntity update(@RequestBody UserDto user){
        if (user.getId() == null) {
            throw new BadRequestException("UserId Can not be empty!");
        }
        iUserService.updateUserAndUserRoles(user);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    /**
     * @description: 修改用户密码
     * @param: userid,pass,oldpass
     * @return: ResponseEntity
     */
    @RequestMapping(value = "/updatepwd",method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ADMIN','USER_ALL','USER_EDIT')")
    public ResponseEntity updatePassword(String userid, String pass,  String oldpass){
        UserDetails userDetails = SecurityContextHolder.getUserDetails();
        JwtUser jwtUser = (JwtUser) userDetailsService.loadUserByUsername(userDetails.getUsername());
        if(!StringUtils.isEmpty(pass)) {
            if (!jwtUser.getPassword().equals(EncryptUtils.encryptPassword(EncryptUtils.encryptPassword(oldpass)))) {
               throw new BadRequestException("旧密码错误！");
            }
            iUserService.updatePass(jwtUser.getId(), EncryptUtils.encryptPassword(EncryptUtils.encryptPassword(pass)));
        }else {
            String pwd =UUIDUtil.getUUID(6);
            System.out.println("\n\n\n\n"+pwd);
            iUserService.updatePass(userid, EncryptUtils.encryptPassword(EncryptUtils.encryptPassword(pwd)));
        }

        return new ResponseEntity(HttpStatus.OK);
    }

}

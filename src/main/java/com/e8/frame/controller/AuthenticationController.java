package com.e8.frame.controller;

import com.e8.frame.config.security.AuthenticationToken;
import com.e8.frame.config.security.AuthorizationUser;
import com.e8.frame.config.security.JwtUser;
import com.e8.frame.tools.EncryptUtils;
import com.e8.frame.tools.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2019-02-21
 * Time: 上午10:28
 */
@RestController
@RequestMapping("/auth")
public class AuthenticationController {


    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;

    /**
     * 登录授权
     * @param authorizationUser
     * @return
     */
    @PostMapping(value = "${jwt.auth.path}")
    public ResponseEntity authenticationLogin(@RequestBody AuthorizationUser authorizationUser){

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authorizationUser.getUsername());
        System.out.println(authorizationUser.getPassword());
        System.out.println(userDetails.getPassword());
        System.out.println(EncryptUtils.encryptPassword(authorizationUser.getPassword()));
        System.out.println(EncryptUtils.encryptPassword(EncryptUtils.encryptPassword(authorizationUser.getPassword())));
        if(!userDetails.getPassword().equals(EncryptUtils.encryptPassword(EncryptUtils.encryptPassword(authorizationUser.getPassword())))){

            throw new AccountExpiredException("密码错误");
        }

        if(!userDetails.isEnabled()){
            throw new AccountExpiredException("账号已停用，请联系管理员");
        }

        // 生成令牌
        final String token = jwtTokenUtil.generateToken(userDetails);

        // 返回 token
        return ResponseEntity.ok(new AuthenticationToken(token));
    }

    /**
     * 获取用户信息
     * @param request
     * @return
     */
    @GetMapping(value = "${jwt.auth.account}")
    public ResponseEntity getUserInfo(HttpServletRequest request){
        JwtUser jwtUser = (JwtUser)userDetailsService.loadUserByUsername(jwtTokenUtil.getUserName(request));
        return ResponseEntity.ok(jwtUser);
    }
}

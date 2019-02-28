package com.e8.frame.config.security;

import com.e8.frame.exception.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @Auther: sharps
 * @Date: 19-2-27 15:04
 * @Description: 获取当前登录的用户名
 */
public class SecurityContextHolder {
    public static UserDetails getUserDetails() {
        UserDetails userDetails = null;
        try {
            userDetails = (UserDetails) org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            throw new BadRequestException(HttpStatus.UNAUTHORIZED, "登录状态过期");
        }
        return userDetails;
    }
}

package com.e8.frame.model.vo;

import com.e8.frame.model.Role;
import lombok.Data;

import java.util.Date;
import java.util.Set;

/**
 * @Auther: sharps
 * @Date: 19-2-20 18:39
 * @Description:
 */
@Data
public class UserVo {

    private String  id;

    private String username;

    private String avatar;

    private String email;

    private Boolean enabled;

    private String password;

    private Date createTime;

    private Date lastPasswordResetTime;

    private Set<Role> roles;


}

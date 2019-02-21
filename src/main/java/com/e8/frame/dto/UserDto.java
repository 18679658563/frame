package com.e8.frame.dto;

import com.e8.frame.model.Role;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2019-02-20
 * Time: 下午3:44
 */
@Data
public class UserDto {

    private Long id;

    private String username;

    private String avatar;

    private String email;

    private Boolean enabled;

    private String password;

    private Date createTime;

    private Date lastPasswordResetTime;

    private List<Role> roles;
}

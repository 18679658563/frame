package com.e8.frame.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2019-02-21
 * Time: 下午3:38
 */
@Data
public class UserDto {

    private String id;

    private String username;

    private String avatar;

    private String email;

    private Boolean enabled;

    @JsonIgnore
    private String password;

    private Timestamp createTime;

    private Date lastPasswordResetTime;

    private Set<RoleDto> roles;
}

package com.e8.frame.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2019-02-20
 * Time: 下午3:10
 */
@Data
public class User extends BaseModel implements Serializable {

    //头像
    private String avatar;

    private String email;

    private Integer enabled;

    private String password;

    private String username;

    private Date lastPasswordResetTime;

}

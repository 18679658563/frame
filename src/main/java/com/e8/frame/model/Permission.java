package com.e8.frame.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2019-02-20
 * Time: 下午3:16
 */
@Data
public class Permission extends BaseModel implements Serializable {

    private String alias;

    private String name;

    private String pid;

}

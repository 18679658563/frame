package com.e8.frame.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2019-02-20
 * Time: 下午3:13
 */
@Data
public class Role extends BaseModel implements Serializable {

    private String name;

    private String remark;


}

package com.e8.frame.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2019-02-20
 * Time: 下午3:14
 */
@Data
public class BaseModel implements Serializable {

    private String id;

    private Date createTime;
}

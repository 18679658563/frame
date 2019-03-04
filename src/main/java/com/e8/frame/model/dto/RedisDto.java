package com.e8.frame.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/***
 * Created with IntelliJ IDEA.
 * Description: 
 * User: silence
 * Date: 2019-03-04
 * Time: 上午10:50
 */
@Data
public class RedisDto {

    @NotBlank
    private String key;

    @NotBlank
    private String value;
}

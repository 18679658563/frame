package com.e8.frame.exception;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * @Auther: sharps
 * @Date: 19-2-28 15:27
 * @Description:
 */
public class SelectException extends RuntimeException{

    private Integer status = BAD_REQUEST.value();

    public SelectException(String msg){
        super(msg);
    }

}

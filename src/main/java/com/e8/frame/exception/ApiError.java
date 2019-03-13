package com.e8.frame.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Auther: sharps
 * @Date: 19-2-20 18:21
 * @Description:
 */
@Data
class ApiError {

    private Integer status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;
    private String message;

    private ApiError() {
        timestamp = LocalDateTime.now();
    }

    public ApiError(Integer status,String message) {
        this();
        this.status = status;
        this.message = message;
    }
}



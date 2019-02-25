package com.e8.frame.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2019-02-20
 * Time: 下午3:44
 */
@RestController
@RequestMapping("/api")
public class UserController {
    public ResponseEntity getUsers(){
        return new ResponseEntity();
    }
}

package com.e8.frame.controller;

import com.e8.frame.model.LogModel;
import com.e8.frame.service.ILogService;
import com.e8.frame.tools.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2019-02-27
 * Time: 下午5:05
 */
@RestController
@RequestMapping("/api")
public class LogController {

    @Autowired
    private ILogService logService;

    @GetMapping(value = "logs")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity getLogs(LogModel logModel, PageUtil page){
        return new ResponseEntity(logService.findByPage(page,logModel), HttpStatus.OK);
    }
}

package com.e8.frame.controller;

import com.e8.frame.aop.Log;
import com.e8.frame.model.dto.VisitsDto;
import com.e8.frame.service.IVisitsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: sharps
 * @Date: 19-3-5 16:44
 * @Description:
 */
@RestController
@RequestMapping("/api")
public class VisitsController {


@Autowired
 private IVisitsService visitsService;

    @Log(description = "访问记录更新")
    @PostMapping(value = "/visits")
    public ResponseEntity create(){
        visitsService.count();
        return new ResponseEntity(HttpStatus.CREATED);
    }
    @Log(description = "访问记录")
    @GetMapping(value = "/visits")
    public ResponseEntity get(){
        return new ResponseEntity(visitsService.get(),HttpStatus.OK);
    }
    @Log(description = "访问记录图表")
    @GetMapping(value = "/visits/chartData")
    public ResponseEntity getChartData(){
        return new ResponseEntity(visitsService.getChartData(),HttpStatus.OK);
    }

}

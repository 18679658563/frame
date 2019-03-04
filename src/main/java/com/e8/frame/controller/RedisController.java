package com.e8.frame.controller;

import com.e8.frame.aop.Log;
import com.e8.frame.model.dto.RedisDto;
import com.e8.frame.service.IRedisService;
import com.e8.frame.tools.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/***
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2019-03-04
 * Time: 下午1:52
 */
@RestController
@RequestMapping("/api")
public class RedisController {

    @Autowired
    private IRedisService redisService;

    @Log(description = "查询Redis缓存")
    @GetMapping(value = "/redis")
    @PreAuthorize("hasAnyRole('ADMIN','REDIS_ALL','REDIS_SELECT')")
    public ResponseEntity getRedis(String key, PageUtil page){
        return new ResponseEntity(redisService.findByKey(key,page), HttpStatus.OK);
    }

    @Log(description = "删除Redis缓存")
    @DeleteMapping(value = "/redis")
    @PreAuthorize("hasAnyRole('ADMIN','REDIS_ALL','REDIS_DELETE')")
    public ResponseEntity delete(@RequestBody RedisDto resources){
        redisService.delete(resources.getKey());
        return new ResponseEntity(HttpStatus.OK);
    }

    @Log(description = "清空Redis缓存")
    @DeleteMapping(value = "/redis/all")
    @PreAuthorize("hasAnyRole('ADMIN','REDIS_ALL','REDIS_DELETE')")
    public ResponseEntity deleteAll(){
        redisService.flushAll();
        return new ResponseEntity(HttpStatus.OK);
    }
}




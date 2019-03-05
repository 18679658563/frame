package com.e8.frame.service.impl;

import com.e8.frame.model.dto.RedisDto;
import com.e8.frame.service.IRedisService;
import com.e8.frame.tools.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/***
 * Created with IntelliJ IDEA.
 * Description: 
 * User: silence
 * Date: 2019-03-04
 * Time: 上午10:55
 */
@Service
public class RedisServiceImpl implements IRedisService {

    @Autowired
    private JedisPool pool;

    @Override
    public Object findByKey(String key, PageUtil page) {
        Jedis jedis = null;
        try{
            jedis = pool.getResource();
            List<RedisDto> redisDtos = new ArrayList<>();
            if(!("*").equals(key)){
                key = "*" + key + "*";
            }
            Set<String> set = jedis.keys(key);
            List<String> redis = new ArrayList<>(set);
            int total = 0;
            if(page.getNumber() == 0){
                if(redis.size()>=page.getSize()){
                    total = page.getSize();
                }else{
                    total = redis.size();
                }
            }else {
                if(redis.size() <= page.getNumber()){
                    total = 0;
                } else if(redis.size()/page.getSize()>0){
                    total = page.getSize();
                } else {//redis.size()%page.getNumber()>0
                    total = redis.size()%page.getSize();
                }
            }
            total = page.getNumber()+total>redis.size()?redis.size():page.getNumber()+total;
            for(int x = page.getNumber();x < total ; x++){
                RedisDto redisDto = new RedisDto();
                redisDto.setKey(redis.get(x));
                redisDto.setValue(jedis.get(redis.get(x)));
                redisDtos.add(redisDto);
            }
           // List<RedisDto> redis =  PageUtil.toPage(page.getNumber(),page.getSize(),redisDtos);
            page.setList(redisDtos);
            page.setCount(redis.size());
            return PageUtil.toResult(page);
        } finally {
            if(jedis != null){
                jedis.close();
            }
        }
    }

    @Override
    public void delete(String key) {
        Jedis jedis = null;
        try{
            jedis = pool.getResource();
            jedis.del(key);
        } finally {
            if(jedis != null){
                jedis.close();
            }
        }
    }

    @Override
    public void flushAll() {
        Jedis jedis = null;
        try{
            jedis = pool.getResource();
            jedis.flushDB();
        }finally{
            if(null != jedis){
                jedis.close();
            }
        }
    }
}

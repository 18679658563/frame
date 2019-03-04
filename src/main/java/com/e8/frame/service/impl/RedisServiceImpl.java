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
    public PageUtil findByKey(String key, PageUtil page) {
        Jedis jedis = null;
        try{
            jedis = pool.getResource();
            List<RedisDto> redisDtos = new ArrayList<>();
            if(!key.equals("*")){
                key = "*" + key + "*";
            }
            if(StringUtils.isEmpty(key)){
                key = "*";
            }
            Set<String> set = jedis.keys(key);
            for(String k : set){
                RedisDto redisDto = new RedisDto();
                redisDto.setKey(k);
                redisDto.setValue(jedis.get(k));
                redisDtos.add(redisDto);
            }
            List<RedisDto> redis =  PageUtil.toPage(page.getNumber(),page.getSize(),redisDtos);
            page.setList(redis);
            page.setCount(redisDtos.size());
            return page;
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

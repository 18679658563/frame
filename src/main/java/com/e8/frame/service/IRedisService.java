package com.e8.frame.service;

import com.e8.frame.tools.PageUtil;

/***
 * Created with IntelliJ IDEA.
 * Description: 
 * User: silence
 * Date: 2019-03-04
 * Time: 上午10:44
 */
public interface IRedisService {

    Object findByKey(String key,PageUtil page);

    void delete(String key);

    void flushAll();
}

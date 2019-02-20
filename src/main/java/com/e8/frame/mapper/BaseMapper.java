package com.e8.frame.mapper;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2019-02-20
 * Time: 下午3:20
 */
public interface BaseMapper<T> {

    T selectById(String id);

    int insertBySelective(T t);

    int delete(String id);

    int updateBySelective(T t);

}

package com.e8.frame.mapper;

import com.e8.frame.model.LogModel;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2019-02-27
 * Time: 下午3:21
 */
@Mapper
public interface LogMapper {

    int insertLog(LogModel log);


}

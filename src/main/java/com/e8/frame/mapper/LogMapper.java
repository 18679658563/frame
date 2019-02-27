package com.e8.frame.mapper;

import com.e8.frame.aop.Log;
import com.e8.frame.model.LogModel;
import com.e8.frame.tools.PageUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    List<LogModel> selectByPage(@Param("page") PageUtil page , @Param("log") LogModel log);

    Integer count(LogModel logModel);


}

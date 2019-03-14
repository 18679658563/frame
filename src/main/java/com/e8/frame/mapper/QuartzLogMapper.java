package com.e8.frame.mapper;

import com.e8.frame.model.QuartzLog;
import com.e8.frame.tools.PageUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QuartzLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(QuartzLog record);

    int insertSelective(QuartzLog record);

    QuartzLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(QuartzLog record);

    int updateByPrimaryKeyWithBLOBs(QuartzLog record);

    int updateByPrimaryKey(QuartzLog record);

    List<QuartzLog> queryQuartzLogsByPage(@Param("quartzLog") QuartzLog quartzLog, @Param("page") PageUtil page);

    int countQuartzLogs(QuartzLog quartzLog);
}
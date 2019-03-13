package com.e8.frame.mapper;

import com.e8.frame.model.QuartzJob;
import com.e8.frame.tools.PageUtil;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface QuartzJobMapper {
    int deleteByPrimaryKey(Long id);

    int insert(QuartzJob record);

    int insertSelective(QuartzJob record);

    QuartzJob selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(QuartzJob record);

    int updateByPrimaryKey(QuartzJob record);

    List<QuartzJob> queryQuartzJobsByPage(QuartzJob quartzJob, PageUtil page);

    int countQuartzJobs();

    List<QuartzJob> findByIsPauseIsFalse();
}
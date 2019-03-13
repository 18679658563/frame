package com.e8.frame.service.impl;

import com.e8.frame.exception.BadRequestException;
import com.e8.frame.mapper.QuartzJobMapper;
import com.e8.frame.model.QuartzJob;
import com.e8.frame.quartz.QuartzManage;
import com.e8.frame.service.IQuartzJobService;
import com.e8.frame.tools.PageUtil;
import org.apache.logging.log4j.core.util.CronExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: luruidi
 * @date: 2019-03-12 11:18:18
 **/

@Service(value = "iQuartzJobService")
public class QuartzJobServiceImpl implements IQuartzJobService {

    @Autowired
    private QuartzJobMapper quartzJobMapper;

    @Autowired
    private QuartzManage quartzManage;

    @Override
    public List<QuartzJob> queryQuartzJobsByPage(QuartzJob quartzJob, PageUtil page) {
        return quartzJobMapper.queryQuartzJobsByPage(quartzJob, page);
    }

    public QuartzJob create(QuartzJob quartzJob) {
        if (!CronExpression.isValidExpression(quartzJob.getCronExpression())) {
            throw new BadRequestException("cron表达式格式错误");
        }
        int i = quartzJobMapper.insertSelective(quartzJob);
        if(i>0){
            quartzManage.addJob(quartzJob);
        }
        return quartzJob;
    }

    @Override
    public void updateIsPause(Long id) {
        QuartzJob quartzJob = quartzJobMapper.selectByPrimaryKey(id);
        if (quartzJob == null) {
            throw new BadRequestException("该任务不存在");
        }
        if (quartzJob.getIsPause()) {
            quartzManage.resumeJob(quartzJob);
            quartzJob.setIsPause(false);
        } else {
            quartzManage.pauseJob(quartzJob);
            quartzJob.setIsPause(true);
        }
        quartzJobMapper.updateByPrimaryKeySelective(quartzJob);
    }

    @Override
    public void update(QuartzJob quartzJob) {
        if (!org.quartz.CronExpression.isValidExpression(quartzJob.getCronExpression())) {
            throw new BadRequestException("cron表达式格式错误");
        }
        quartzJob.setUpdateTime(new Timestamp(new Date().getTime()));
        quartzJobMapper.updateByPrimaryKeySelective(quartzJob);
        quartzManage.updateJobCron(quartzJob);
    }

    @Override
    public void delete(Long id) {
        QuartzJob quartzJob = quartzJobMapper.selectByPrimaryKey(id);
        if (quartzJob == null) {
            throw new BadRequestException("该任务不存在");
        }
        quartzJobMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void execution(Long id) {
        QuartzJob quartzJob = quartzJobMapper.selectByPrimaryKey(id);
        if (quartzJob == null) {
            throw new BadRequestException("该任务不存在");
        }
        quartzManage.runAJobNow(quartzJob);
    }
}

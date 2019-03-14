package com.e8.frame.quartz;


import com.e8.frame.mapper.QuartzLogMapper;
import com.e8.frame.model.QuartzJob;
import com.e8.frame.model.QuartzLog;
import com.e8.frame.service.IQuartzJobService;
import com.e8.frame.tools.SpringContextHolder;
import com.e8.frame.tools.ThrowableUtil;
import org.quartz.JobExecutionContext;
import org.quartz.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.quartz.QuartzJobBean;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

 /**
 * 参考人人开源，https://gitee.com/renrenio/renren-security
 * @author
 * @date 2019-01-07
 */


@Async
public class ExecutionJob extends QuartzJobBean {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource(name = "scheduler")
    private Scheduler scheduler;

    @Autowired
    private ExecutorService executorService;

    @Override
    protected void executeInternal(JobExecutionContext context) {
        QuartzJob quartzJob = (QuartzJob) context.getMergedJobDataMap().get(QuartzJob.JOB_KEY);
        // 获取spring bean
        QuartzLogMapper quartzLogMapper = SpringContextHolder.getBean("quartzLogMapper");
        IQuartzJobService quartzJobService = SpringContextHolder.getBean("iQuartzJobService");
        QuartzManage quartzManage = SpringContextHolder.getBean("quartzManage");

        QuartzLog log = new QuartzLog();
        log.setJobName(quartzJob.getJobName());
        log.setBeanName(quartzJob.getBeanName());
        log.setMethodName(quartzJob.getMethodName());
        log.setParams(quartzJob.getParams());
        long startTime = System.currentTimeMillis();
        log.setCronExpression(quartzJob.getCronExpression());
        log.setCreateTime(new Timestamp(new Date().getTime()));
        try {
            // 执行任务
            logger.info("任务准备执行，任务名称：{}", quartzJob.getJobName());
            QuartzRunnable task = new QuartzRunnable(quartzJob.getBeanName(), quartzJob.getMethodName(),
                    quartzJob.getParams());
//            ExecutorService executorService = Executors.newFixedThreadPool(10);
            Future<?> future = executorService.submit(task);
            future.get();
            long times = System.currentTimeMillis() - startTime;
            log.setTime(times);
            // 任务状态
            log.setIsSuccess(true);
            logger.info("任务执行完毕，任务名称：{} 总共耗时：{} 毫秒", quartzJob.getJobName(), times);
        } catch (Exception e) {
            logger.error("任务执行失败，任务名称：{}" + quartzJob.getJobName(), e);
            long times = System.currentTimeMillis() - startTime;
            log.setTime(times);
            // 任务状态 0：成功 1：失败
            log.setIsSuccess(false);
            log.setExceptionDetail(ThrowableUtil.getStackTrace(e));
            //出错就暂停任务
            quartzManage.pauseJob(quartzJob);
            //更新状态
            quartzJobService.updateIsPause(quartzJob.getId());
        } finally {
            quartzLogMapper.insertSelective(log);
        }
    }
}

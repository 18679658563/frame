package com.e8.frame.quartz;

import com.e8.frame.mapper.QuartzJobMapper;
import com.e8.frame.model.QuartzJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author jie
 * @date 2019-01-07
 */
@Component
@Slf4j
public class JobRunner implements ApplicationRunner {

    @Autowired
    private QuartzJobMapper quartzJobMapper;

    @Autowired
    private QuartzManage quartzManage;

    /**
     * 项目启动时重新激活启用的定时任务
     * @param applicationArguments
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments applicationArguments){
        log.info("--------------------注入定时任务---------------------");
        List<QuartzJob> quartzJobs = quartzJobMapper.findByIsPauseIsFalse();
        quartzJobs.forEach(quartzJob -> {
            quartzManage.addJob(quartzJob);
        });
        log.info("--------------------定时任务注入完成---------------------");
    }
}

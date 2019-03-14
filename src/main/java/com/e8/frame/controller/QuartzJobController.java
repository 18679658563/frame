package com.e8.frame.controller;

import com.e8.frame.aop.Log;
import com.e8.frame.exception.BadRequestException;
import com.e8.frame.mapper.QuartzJobMapper;
import com.e8.frame.mapper.QuartzLogMapper;
import com.e8.frame.model.QuartzJob;
import com.e8.frame.model.QuartzLog;
import com.e8.frame.service.IQuartzJobService;
import com.e8.frame.tools.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description:
 * @author: luruidi
 * @date: 2019-03-12 11:18:18
 **/

@Slf4j
@RestController
@RequestMapping("/api")
public class QuartzJobController {

    private static final String ENTITY_NAME = "quartzJob";

    @Autowired
    private IQuartzJobService quartzJobService;

    @Autowired
    private QuartzJobMapper quartzJobMapper;

    @Autowired
    private QuartzLogMapper quartzLogMapper;

    @Log(description = "查询定时任务")
    @GetMapping(value = "/jobs")
    @PreAuthorize("hasAnyRole('ADMIN','JOB_ALL','JOB_SELECT')")
    public ResponseEntity getJobs(QuartzJob quartzJob, PageUtil page){
        List<QuartzJob> quartzJobs = quartzJobService.queryQuartzJobsByPage(quartzJob, page);
        PageUtil<QuartzJob> pageList = new PageUtil<>();
        pageList.setList(quartzJobs);
        int count = quartzJobMapper.countQuartzJobs(quartzJob);
        pageList.setCount(count);
        return new ResponseEntity(PageUtil.toResult(pageList), HttpStatus.OK);
    }

    @Log(description = "新增定时任务")
    @PostMapping(value = "/jobs")
    @PreAuthorize("hasAnyRole('ADMIN','JOB_ALL','JOB_CREATE')")
    public ResponseEntity create(@Validated @RequestBody QuartzJob resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(quartzJobService.create(resources),HttpStatus.CREATED);
    }

    @Log(description = "修改定时任务")
    @PutMapping(value = "/jobs")
    @PreAuthorize("hasAnyRole('ADMIN','JOB_ALL','JOB_EDIT')")
    public ResponseEntity update(@Validated @RequestBody QuartzJob resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        quartzJobService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log(description = "删除定时任务")
    @DeleteMapping(value = "/jobs/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','JOB_ALL','JOB_DELETE')")
    public ResponseEntity delete(@PathVariable Long id){
        quartzJobService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Log(description = "更改定时任务状态")
    @PutMapping(value = "/jobs/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','JOB_ALL','JOB_EDIT')")
    public ResponseEntity updateIsPause(@PathVariable Long id){
        quartzJobService.updateIsPause(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log(description = "执行定时任务")
    @PutMapping(value = "/jobs/exec/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','JOB_ALL','JOB_EDIT')")
    public ResponseEntity execution(@PathVariable Long id){
        quartzJobService.execution(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    /**
     * 查询定时任务日志
     * @param quartzLog
     * @param page
     * @return
     */
    @GetMapping(value = "/jobLogs")
    @PreAuthorize("hasAnyRole('ADMIN','JOB_ALL','JOB_SELECT')")
    public ResponseEntity getJobLogs(QuartzLog quartzLog, PageUtil page){
        List<QuartzLog> quartzLogs = quartzLogMapper.queryQuartzLogsByPage(quartzLog, page);
        PageUtil<QuartzLog> pageList = new PageUtil<>();
        pageList.setList(quartzLogs);
        int count = quartzLogMapper.countQuartzLogs(quartzLog);
        pageList.setCount(count);
        return new ResponseEntity(PageUtil.toResult(pageList), HttpStatus.OK);
    }

}

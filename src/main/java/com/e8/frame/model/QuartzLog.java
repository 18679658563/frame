package com.e8.frame.model;

import lombok.Data;

import java.sql.Timestamp;
@Data
public class QuartzLog {

    private Long id;

    /**
     * 任务名称
     */
    private String jobName;

    /**
     * Bean名称
     */
    private String beanName;

    /**
     * 方法名称
     */
    private String methodName;

    /**
     * 参数
     */
    private String params;

    /**
     * cron表达式
     */
    private String cronExpression;

    /**
     * 状态
     */
    private Boolean isSuccess;

    /**
     * 异常详细
     */
    private String exceptionDetail;

    /**
     * 耗时（毫秒）
     */
    private Long time;

    /**
     * 创建日期
     */
    private Timestamp createTime;
}
package com.e8.frame.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
@Data
public class QuartzJob {

    public static final String JOB_KEY = "JOB_KEY";

    private Long id;

    /**
     * 定时器名称
     */
    private String jobName;

    /**
     * Bean名称
     */
    @NotBlank
    private String beanName;

    /**
     * 方法名称
     */
    @NotBlank
    private String methodName;

    /**
     * 参数
     */
    private String params;

    /**
     * cron表达式
     */
    @NotBlank
    private String cronExpression;

    /**
     * 状态
     */
    private Boolean isPause = false;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建日期
     */
    private Timestamp updateTime;

}
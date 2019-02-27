package com.e8.frame.service;

import com.e8.frame.model.LogModel;
import com.e8.frame.tools.PageUtil;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2019-02-27
 * Time: 下午3:49
 */
public interface  ILogService {

    int saveLog(ProceedingJoinPoint joinPoint , LogModel log);


    Object  findByPage(PageUtil page,LogModel logModel);
}

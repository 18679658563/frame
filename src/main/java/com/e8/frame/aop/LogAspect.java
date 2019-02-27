package com.e8.frame.aop;

import com.e8.frame.exception.BadRequestException;
import com.e8.frame.model.LogModel;
import com.e8.frame.service.ILogService;
import com.e8.frame.tools.ThrowUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * Description: log切面
 * User: silence
 * Date: 2019-02-27
 * Time: 下午2:52
 */
@Component
@Aspect
@Slf4j
public class LogAspect {

    @Autowired
    private ILogService logService;

    private Long currentTime = 0L;

    /**
     * 配置切入点
     */
    @Pointcut("@annotation(com.e8.frame.aop.Log)")
    public void logPointcut(){
    }

    /**
     * 配置环绕通知，使用在方法logpointcat上注册的切入点
     * @param joinPoint join  point for advice
     * @return
     */
    @Around("logPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint){
        Object result = null;
        currentTime = System.currentTimeMillis();
        try{
            result = joinPoint.proceed();
        } catch (Throwable e){
            throw new BadRequestException(e.getMessage());
        }
        LogModel log = new LogModel();
        log.setLogType("INFO");
        log.setTime(System.currentTimeMillis() - currentTime + "");
        logService.saveLog(joinPoint,log);
        return result;
    }

    /**
     * 配置异常通知
     *
     * @param joinPoint join point for advice
     * @param e exception
     */
    @AfterThrowing(pointcut = "logPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        LogModel log = new LogModel();
        log.setLogType("ERROR");
        log.setTime(System.currentTimeMillis() - currentTime + "");
        log.setExceptionDetail(ThrowUtil.getStackTrace(e));
        logService.saveLog((ProceedingJoinPoint)joinPoint, log);
    }
}

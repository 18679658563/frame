package com.e8.frame.service.impl;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.e8.frame.aop.Log;
import com.e8.frame.config.security.AuthorizationUser;
import com.e8.frame.mapper.LogMapper;
import com.e8.frame.model.LogModel;
import com.e8.frame.service.ILogService;
import com.e8.frame.tools.IpUtil;
import com.e8.frame.tools.PageUtil;
import com.e8.frame.tools.UUIDUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2019-02-27
 * Time: 下午4:07
 */
@Service
public class LogServiceImpl implements ILogService {

    @Autowired
    private LogMapper logMapper;

    private final String LOGINPATH = "authenticationLogin";

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveLog(ProceedingJoinPoint joinPoint, LogModel logModel) {
        //获取request
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //获取方法头指定修饰符、返回值类型、方法名、和形式参数。
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        Method method = signature.getMethod();
        Log log = method.getAnnotation(Log.class);
        if(log != null){
            //描述
            logModel.setDescription(log.description());
        }
        //方法路径
        String methodName  = joinPoint.getTarget().getClass().getName() + "." + signature.getName() + "()";
        //参数
        String params = "{";
        //参数值
        Object[] argValues = joinPoint.getArgs();
        //参数名称
        String[] argNames = ((MethodSignature)joinPoint.getSignature()).getParameterNames();
        //用户名
        String username = "";
        if(argValues != null && argValues.length > 0){
            for(int i = 0 ; i < argValues.length ; i++){
                params += " " + argNames[i] + ":" + argValues[i];
            }
        }
        params += "}";
        //获取ip地址
        logModel.setRequestIp(IpUtil.getIp(request));
        //设置username
        if(!LOGINPATH.equals(signature.getName())){
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            username = userDetails.getUsername();
        } else{
            AuthorizationUser user = JSONUtil.toBean(new JSONObject(argValues[0]),AuthorizationUser.class);
            username = user.getUsername();
        }
        logModel.setId(UUIDUtil.getUUID());
        logModel.setMethod(methodName);
        logModel.setParams(params);
        logModel.setUsername(username);
        logModel.setCreateTime(new Date());
        return logMapper.insertLog(logModel);
    }

    @Override
    public Object findByPage(PageUtil page, LogModel logModel) {
        List<LogModel> lists = logMapper.selectByPage(page,logModel);
        Integer count = logMapper.count(logModel);
        page.setList(lists);
        page.setCount(count);
        return PageUtil.toResult(page);
    }
}

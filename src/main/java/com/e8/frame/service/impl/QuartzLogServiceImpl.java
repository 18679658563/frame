package com.e8.frame.service.impl;

import com.e8.frame.mapper.QuartzLogMapper;
import com.e8.frame.model.QuartzLog;
import com.e8.frame.service.IQuartzLogService;
import com.e8.frame.tools.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: luruidi
 * @date: 2019-03-12 15:10:31
 **/
@Service
public class QuartzLogServiceImpl implements IQuartzLogService {

    @Autowired
    private QuartzLogMapper quartzLogMapper;

    @Override
    public List<QuartzLog> queryQuartzLogsByPage(QuartzLog quartzLog, PageUtil page) {
        return quartzLogMapper.queryQuartzLogsByPage(quartzLog, page);
    }

    @Override
    public int queryQuartzLogsByBeforTime() {
        /* 核心代码 */
        Calendar beforeTime = Calendar.getInstance();
        beforeTime.add(Calendar.SECOND, -30);// 5分钟之前的时间
        Date beforeD = beforeTime.getTime();
        return quartzLogMapper.deleteByBeforeTime(beforeD);
    }
}

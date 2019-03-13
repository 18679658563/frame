package com.e8.frame.service;

import com.e8.frame.model.QuartzLog;
import com.e8.frame.tools.PageUtil;

import java.util.List;

/**
 * @description:
 * @author: luruidi
 * @date: 2019-03-12 15:09:21
 **/
public interface IQuartzLogService {
    List<QuartzLog> queryQuartzLogsByPage(QuartzLog quartzLog, PageUtil page);
}

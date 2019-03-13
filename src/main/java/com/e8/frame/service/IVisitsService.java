package com.e8.frame.service;

import org.springframework.scheduling.annotation.Async;


/**
 * @Auther: sharps
 * @Date: 19-3-5 16:52
 * @Description:
 */
public interface IVisitsService {

    /**
     * 获取访问数据
     * @return
     */
//    @Async
    Object get();

//    @Async
    void count();

    /**
     * getChartData
     * @return
     */
    Object getChartData();
}

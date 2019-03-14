package com.e8.frame.task;

import com.e8.frame.service.IVisitsService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Auther: sharps
 * @Date: 19-3-14 16:19
 * @Description:
 */
public class VisitsTask {

    @Autowired
    private IVisitsService visitsService;

    public void run(){
        visitsService.count();
    }
}

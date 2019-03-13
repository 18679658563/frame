package com.e8.frame.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: luruidi
 * @date: 2019-03-12 17:02:55
 **/
@Slf4j
@Component
public class Test {

    public void test(){
        log.info("无参执行成功");
    }

    public void test(String arg){
        log.info("有参执行成功，参数为：" + arg);
    }
}

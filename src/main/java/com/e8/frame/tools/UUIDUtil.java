package com.e8.frame.tools;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2019-02-22
 * Time: 上午9:30
 */
public class UUIDUtil {

    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("\\-","");
    }
}

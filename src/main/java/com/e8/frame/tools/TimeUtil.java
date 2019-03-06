package com.e8.frame.tools;

import java.util.Calendar;
import java.util.Date;

/**
 * @Auther: sharps
 * @Date: 19-3-6 11:26
 * @Description: 日期工具
 */
public class TimeUtil {

    public static String getWeekDay(){
        String[] weekDays = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0){
            w = 0;
        }
        return weekDays[w];
    }
}

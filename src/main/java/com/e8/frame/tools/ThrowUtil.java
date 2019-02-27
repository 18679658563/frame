package com.e8.frame.tools;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created with IntelliJ IDEA.
 * Description: 异常工具
 * User: silence
 * Date: 2019-02-27
 * Time: 下午4:03
 */
public class ThrowUtil {

    /**
     * 获取堆栈信息
     * @param throwable
     * @return
     */
    public static String getStackTrace(Throwable throwable){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        try {
            throwable.printStackTrace(pw);
            return "\n"+sw.toString();
        } finally {
            pw.close();
        }
    }
}

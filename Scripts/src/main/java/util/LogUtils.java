package util;

/**
 * 日志工具类
 */
public class LogUtils {
    private static boolean isDebug =  true;

    public static void LogD(String msg) {
        if (isDebug) {
            System.out.println("------ DEBUG ------ msg=" + msg);
        }
    }

    public static void LogE(String msg) {
        if (isDebug) {
            System.out.println("------ ERROR ------ msg=" + msg);
        }
    }
}

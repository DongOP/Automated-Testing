package util;

import io.appium.java_client.android.AndroidDriver;

/**
 * 授权弹窗相关工具类
 */
public class AuthorizedActionUtils {

    // 点击同意用户协议
    public static void clickAgreeProtocol(AndroidDriver androidDriver){
        // 启动APP后等待 2 秒
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        androidDriver.findElementById("com.jd.pingou:id/bt_agreement_agree").click();
        LogUtils.LogD("点击同意协议");
    }

    // 点击同意位置信息授权
    public static void clickAgreePositionInfo(AndroidDriver androidDriver){
        // 启动APP后等待 2 秒
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        androidDriver.findElementById("com.android.packageinstaller:id/permission_allow_button").click();
        LogUtils.LogD("点击同意位置信息授权");
    }

}

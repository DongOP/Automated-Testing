package util;

import io.appium.java_client.android.AndroidDriver;

/**
 * 页面跳转的工具类
 */
public class PageUtils {

    /**
     * 点击底部导航的分类tab
     * @param androidDriver
     * @throws Exception
     */
    public static void switchCateTab(AndroidDriver androidDriver) throws Exception{
        LogUtils.LogD("等待 2 s，点击底部导航分类tab");
        Thread.sleep(2000);
        // 通过 XPath 找到分类tab
        androidDriver.findElementByXPath("//android.widget.TextView[@resource-id='com.jd.pingou:id/home_navigator_item_text' and @text='分类']").click();
        Thread.sleep(2000);
    }

}

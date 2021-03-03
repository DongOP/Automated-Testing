package util;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class DriverUtils {
    public static AndroidDriver androidDriver;

    // 三星S6 计算器APP
    public static AndroidDriver getDriverForCalc() {
        try {
            // 添加配置，创建DesiredCapabilities对象
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            // 指定测试设备的名称
            desiredCapabilities.setCapability("deviceName", "02160265102d2e03");
            // 添加操作系统配置
            desiredCapabilities.setCapability("platformName", "Android");
            // 添加操作系统版本设置
            desiredCapabilities.setCapability("platformVersion", "7.0");
            // 指定想要测试应用的包名
            desiredCapabilities.setCapability("appPackage", "com.sec.android.app.popupcalculator");
            // 指定想要测试应用的入口activity
            desiredCapabilities.setCapability("appActivity", "com.sec.android.app.popupcalculator.Calculator");
            // 创建驱动...URL是appium的固定地址；指定appium通讯的地址，将相对应的配置传入到驱动里边
            androidDriver = new AndroidDriver(new URL("http://10.252.17.41:4723/wd/hub"), desiredCapabilities);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return androidDriver;
    }

    // 京喜APP
    public static AndroidDriver getDriverForJXApp() {
        try {
            // 添加配置，创建DesiredCapabilities对象
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            // 指定测试设备的名称
            desiredCapabilities.setCapability("deviceName", "02160265102d2e03");
            // 添加操作系统配置
            desiredCapabilities.setCapability("platformName", "Android");
            // 添加操作系统版本设置
            desiredCapabilities.setCapability("platformVersion", "7.0");
            // 指定想要测试应用的包名
            desiredCapabilities.setCapability("appPackage", "com.jd.pingou");
            // 指定想要测试应用的入口activity
            desiredCapabilities.setCapability("appActivity", "com.jd.pingou.MainActivity");
            // 创建驱动...URL是appium的固定地址；指定appium通讯的地址，将相对应的配置传入到驱动里边
            androidDriver = new AndroidDriver(new URL("http://10.252.17.41:4723/wd/hub"), desiredCapabilities);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return androidDriver;
    }
}

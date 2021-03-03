package demo;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import util.AuthorizedActionUtils;
import util.DriverUtils;
import util.PageUtils;

/**
 * 测试：切换到分类页 PageUtils
 */
public class TestGotoCate {
    private static AndroidDriver androidDriver;

    // 只在类中执行一次
    @BeforeClass
    public static void setup() {
        // 获取 Driver
        androidDriver = DriverUtils.getDriverForJXApp();
    }

//    @Feature("Feature：分类页")
//    @Step("Step：点击底部导航分类tab")
//    @Test
    public void testGotoCate() throws Exception {
        // 用户同意APP的协议
        AuthorizedActionUtils.clickAgreeProtocol(androidDriver);
        // 同意位置授权
        AuthorizedActionUtils.clickAgreePositionInfo(androidDriver);
        // 切换到分类页
        PageUtils.switchCateTab(androidDriver);
    }

    @AfterClass
    public static void TearDown() {
        // 退出测试
        if (androidDriver != null) {
            androidDriver.quit();
        }
        // 释放资源
        androidDriver = null;
    }

}

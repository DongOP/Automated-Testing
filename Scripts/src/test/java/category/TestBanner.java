package category;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.openqa.selenium.WebElement;
import util.AuthorizedActionUtils;
import util.DriverUtils;
import util.LogUtils;
import util.PageUtils;

/**
 * banner资源位 测试
 */
public class TestBanner {
    private static AndroidDriver androidDriver;
    private static WebElement webElement;

    // 只在类中执行一次
    @BeforeClass
    public static void setup() {
        // 获取 Driver
        androidDriver = DriverUtils.getDriverForJXApp();
    }

    @Feature("Feature：分类页")
    @Story("banner资源检查")
    @Order(1)
    @Test
    public void testBanner() throws Exception {
        // 进到分类页
        AuthorizedActionUtils.clickAgreeProtocol(androidDriver);
        AuthorizedActionUtils.clickAgreePositionInfo(androidDriver);
        PageUtils.switchCateTab(androidDriver);
        webElement = androidDriver.findElementById("com.jd.pingou.pgcategory.feature:id/iv_ad_banner");
        // banner校验
        Assert.assertNotNull(webElement);
    }

    @Feature("Feature：分类页")
    @Story("banner跳转落地页检查")
    @Order(2)
    @Test
    public void testBannerClick() throws Exception {
        webElement = androidDriver.findElementById("com.jd.pingou.pgcategory.feature:id/iv_ad_banner");
        // 点击 banner 资源位
        webElement.click();
        Thread.sleep(5000);
        LogUtils.LogD("等待5s,点击banner.");
        // banner跳转检查
        try {
            // H5返回按钮
            webElement = androidDriver.findElementById("com.jd.pingou:id/webui_back");
        } catch (Exception e) {
            // 未登录，获取验证码按钮
            webElement = androidDriver.findElementById("com.jd.pingou:id/phoneLogin_getMsg");
            LogUtils.LogD("未登录："  + webElement.getText());
        }
        Assert.assertNotNull(webElement);
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

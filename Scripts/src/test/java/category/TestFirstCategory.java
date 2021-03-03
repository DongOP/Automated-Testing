package category;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.openqa.selenium.WebElement;
import util.AuthorizedActionUtils;
import util.DriverUtils;
import util.PageUtils;

/**
 * 一级类目 测试
 */
public class TestFirstCategory {

    private static AndroidDriver androidDriver;
    private static WebElement webElement;

    // 只在类中执行一次
    @BeforeClass
    public static void setup() {
        // 获取 Driver
        androidDriver = DriverUtils.getDriverForJXApp();
    }

    @Feature("Feature：分类页")
    @Story("一级类目数据检查")
    @Order(1)
    @Test
    public void testFirstCategory() throws Exception {
        // 进到分类页
        AuthorizedActionUtils.clickAgreeProtocol(androidDriver);
        AuthorizedActionUtils.clickAgreePositionInfo(androidDriver);
        PageUtils.switchCateTab(androidDriver);
        // 一级类目 RecyclerView
        webElement = androidDriver.findElementById("com.jd.pingou.pgcategory.feature:id/rv_left");
        // 数据检查
//        Assert.assertNotNull(webElement);
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

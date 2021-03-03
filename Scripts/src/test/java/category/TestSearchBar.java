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
 * 搜索框 测试
 */
public class TestSearchBar {
    private static AndroidDriver androidDriver;

    // 只在类中执行一次
    @BeforeClass
    public static void setup() {
        // 获取 Driver
        androidDriver = DriverUtils.getDriverForJXApp();
    }

    @Feature("Feature：分类页")
    @Story("切换底部导航到分类页")
    @Order(1)
    @Test
    public void testGotoCate() throws Exception {
        // 用户同意APP的协议
        AuthorizedActionUtils.clickAgreeProtocol(androidDriver);
        // 同意位置授权
        AuthorizedActionUtils.clickAgreePositionInfo(androidDriver);
        // 切换到分类页
        PageUtils.switchCateTab(androidDriver);
    }

    @Feature("Feature：分类页")
    @Story("搜索暗文验证")
    @Order(2)
    @Test
    public void testSearchHideWord() throws Exception {
        // 通过XPath路径获取 搜索框暗文
        String hideWordXPath = "//android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.TextView";
        WebElement searchBarElement = androidDriver.findElementByXPath(hideWordXPath);
        String hideWord = searchBarElement.getText();
        LogUtils.LogD("搜索暗文：" + hideWord);
        Assert.assertNotNull(hideWord);
    }

    @Feature("Feature：分类页")
    @Story("搜索框功能测试")
    @Order(3)
    @Test
    public void testSearchResult() throws Exception {
        // 通过XPath路径获取 搜索框
//        String searchXPath = "//android.widget.FrameLayout[@resource-id='com.jd.pingou.pgcategory.feature:id/jx_sv']";
        WebElement searchBarElement;// = androidDriver.findElementByXPath(searchXPath);
        // 点击搜索框 com.jd.pingou.pgcategory.feature:id/jx_sv
        androidDriver.findElementById("com.jd.pingou.pgcategory.feature:id/jx_sv").click();
//        searchBarElement.click();
        Thread.sleep(1000);
        // 输入搜索词
        searchBarElement = androidDriver.findElementById("com.jd.lib.search.feature:id/search_text");
        searchBarElement.sendKeys("手机");
        // 按下搜索
        androidDriver.findElementById("com.jd.pingou:id/rightTv1").click();
        Thread.sleep(3000);
        // com.jd.lib.search.feature:id/tag_btn
        String searchWord = androidDriver.findElementById("com.jd.lib.search.feature:id/tag_btn").getText();
        LogUtils.LogD("搜索关键字：" + searchWord);
        Assert.assertEquals("检查搜索词是否正常", "手机",searchWord);
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

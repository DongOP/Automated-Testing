package demo;

import util.DriverUtils;
import util.LogUtils;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.*;
import org.junit.*;


/**
 * DEMO：使用 Appium、Junit 和 Allure 对 APP 进行 UI 自动化测试
 *
 * 执行顺序（Junit）：@Before -> @Test -> @After
 */
//@Epic("Epic：计算器功能测试---Junit + allure---")
public class TestCalc {
    private static AndroidDriver androidDriver;
    private static String calcResultText;

    // 只在类中执行一次
    @BeforeClass
    public static void setup() {
        // 获取 Driver
        androidDriver = DriverUtils.getDriverForCalc();
    }

    // 在每个测试方法之前都执行一次
    @Before
    public void Clear() {
        androidDriver.findElementByAccessibilityId("清除").click();
        Assert.assertEquals("", _GetCalculatorResultText());
    }

    @Feature("Feature：计算器功能测试---demo")
    @Description("Description描述语：Subtraction")
    @Step("验证减法成功 {8}")
    @Story("Story：9 - 1")
    @Test
    public void Subtraction() {
        // 对应 content-desc
        androidDriver.findElementByAccessibilityId("9").click();
        androidDriver.findElementByAccessibilityId("减号").click();
        androidDriver.findElementByAccessibilityId("1").click();
        androidDriver.findElementByAccessibilityId("等于").click();
        Assert.assertEquals("检查计算结果是否正常", "8", _GetCalculatorResultText());
    }

    @Feature("Feature：计算器功能测试---demo")
    @Description("Description描述语： Subtraction_0")
    @Step("Step：验证减法失败 {0}")
    @Story("Story：9 - 9")
    @Test
    public void Subtraction_0() {
        // 对应 content-desc
        androidDriver.findElementByAccessibilityId("9").click();
        androidDriver.findElementByAccessibilityId("减号").click();
        androidDriver.findElementByAccessibilityId("9").click();
        androidDriver.findElementByAccessibilityId("等于").click();
        Assert.assertEquals("检查计算结果是否正常", "0", _GetCalculatorResultText());
    }

    @AfterClass
    public static void TearDown() {
        // 退出测试
        if (androidDriver != null) {
            androidDriver.quit();
        }
        // 释放资源
        androidDriver = null;
        calcResultText = "";
    }

    // 获取计算的结果
    protected String _GetCalculatorResultText() {
        // 对应 resource-id
        calcResultText = androidDriver.findElementById("com.sec.android.app.popupcalculator:id/txtCalc").getText().trim();
        LogUtils.LogD(calcResultText);
        return calcResultText;
    }

}

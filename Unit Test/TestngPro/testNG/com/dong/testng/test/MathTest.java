package com.dong.testng.test;

import com.dong.testng.math.RecursiveSort;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * 依赖测试
 *
 * Created by Dong on 2018/1/22 0022.
 */
public class MathTest {

    @BeforeTest
    public void setUp() {
        System.out.println("------------开始测试------------");
    }

    @Test
    public static void testFactorial() {
        Assert.assertEquals(5, RecursiveSort.factorial(3));
        if (6 == RecursiveSort.factorial(3)) {
            System.out.println("testFactorial 测试通过");
        } else {
            System.err.println("testFactorial 测试失败");
        }
    }

    @Test(dependsOnMethods = {"testFactorial"})
    public static void testFactorial1() {
        Assert.assertEquals(6, RecursiveSort.factorial(3));
        if (6 == RecursiveSort.factorial(3)) {
            System.out.println("testFactorial1 测试通过");
        } else {
            System.err.println("testFactorial1 测试失败");
        }
    }

    @Test
    public static void testFactorial2() {
        Assert.assertEquals(6, RecursiveSort.factorial(3));
        if (6 == RecursiveSort.factorial(3)) {
            System.out.println("testFactorial2 测试通过");
        } else {
            System.err.println("testFactorial2 测试失败");
        }
    }

    @AfterTest
    public void endTest() {
        System.out.println("------------测试结束------------");
    }
}

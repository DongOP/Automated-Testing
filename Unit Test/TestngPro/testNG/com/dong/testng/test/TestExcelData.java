package com.dong.testng.test;

import com.dong.testng.Main;
import com.dong.testng.test.util.ExcelUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 参数化测试
 *
 * Created by Dong on 2018/1/22 0022.
 */
public class TestExcelData {

    @Test(dataProvider = "excelData")
    public void doExcelTest(Map<Integer, List> map) {
        for (Map.Entry<Integer, List> entry : map.entrySet()) {
//            System.out.println("[Key]=" + entry.getKey() + ", [Value]=" + entry.getValue());
            System.out.println("a=" + entry.getValue().get(0) + ", b=" + entry.getValue().get(1) + ", 期望值=" + entry.getValue().get(2));
        }
    }

    @DataProvider(name = "excelData")
    public Object[][] testData() throws IOException {
        // 硬解码数据
//        return new Object[][] { {10, 20, 30}, {1, 20, 21}, {10, 30, 50}};
        // Excel文件驱动
        Map<Integer, List> map = ExcelUtils.readExcel(Main.FILE_PATH);
        return new Object[][] { { map } };
    }

}

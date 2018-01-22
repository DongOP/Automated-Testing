package com.dong.testng.test.util;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * Created by Dong on 2018/1/22 0022.
 */
public class ExcelUtils {

    /**
     * 获取 Excel 文件信息
     *
     * @param excelPath 文件路径
     * @return {1=[参数A1, 参数B1, 结果1], 2=[参数A2, 参数B2, 结果2], 3=[参数A3, 参数B3, 结果3]}
     */
    public static Map<Integer, List> readExcel(String excelPath) {
        try {
            HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(new File(excelPath)));
            HSSFSheet sheet = null;
            List<String> list = new ArrayList<>();
            HashMap<Integer, List> mMap = new HashMap<>();
            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {//获取每个Sheet表
                sheet = workbook.getSheetAt(i);
                for (int j = 1; j < sheet.getPhysicalNumberOfRows(); j++) {//获取每行
                    HSSFRow row = sheet.getRow(j);
                    for (int k = 0; k < row.getPhysicalNumberOfCells(); k++) {//获取每个单元格
//                        System.out.print(row.getCell(k) + "\t");
                        if (null != row.getCell(k)) {
//                            list.add(Integer.valueOf(row.getCell(k).toString()));
                            list.add(row.getCell(k).toString());
                        }
                    }
//                    System.out.println("");
                    Object[] temps = new Object[]{list.get(0), list.get(1), list.get(2),};
                    mMap.put(j, Arrays.asList(temps));
                    list.clear();
                }
            }
            // {0=[a, b, 期望值], 1=[1.0, 2.0, 3.0], 2=[2.0, 8.0, 10.0], 3=[3.0, 4.0, 7.0]}
            System.out.println("------mMap------获取的数据为=" + mMap);
            return mMap;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}

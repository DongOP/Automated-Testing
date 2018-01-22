package com.dong.testng;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import java.util.Date;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class Main {

    public static final String FILE_PATH = "F:\\IJ program\\TestngPro\\res\\excel\\data_provider.xls";
    
    public static void main(String[] args) {
        // write your code here
        showExcel(FILE_PATH);
//        print(FILE_PATH);
    }

    public static void showExcel(String excelPath) {
        try {
            HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(new File(excelPath)));
            HSSFSheet sheet = null;
            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {//获取每个Sheet表
                sheet = workbook.getSheetAt(i);
                for (int j = 0; j < sheet.getPhysicalNumberOfRows(); j++) {//获取每行
                    HSSFRow row = sheet.getRow(j);
                    for (int k = 0; k < row.getPhysicalNumberOfCells(); k++) {//获取每个单元格
                        System.out.print(row.getCell(k) + "\t");
                    }
                    System.out.println("");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void print(String path) {
        try {
            BufferedInputStream in = new BufferedInputStream(
                    new FileInputStream(new File(path)));
            POIFSFileSystem fs = new POIFSFileSystem(in);
            HSSFWorkbook wb = new HSSFWorkbook(fs);
            HSSFCell cell = null;
            HSSFSheet st = wb.getSheetAt(0);
            for (int rowIndex = 0; rowIndex <= st.getLastRowNum(); rowIndex++) {
                HSSFRow row = st.getRow(rowIndex);
                if (row == null) {
                    continue;
                }
                for (short columnIndex = 0, size = row.getLastCellNum(); columnIndex <= size; columnIndex++) {
                    cell = row.getCell(columnIndex);
                    String value = "";
                    if (cell != null) {
                        // 注意：一定要设成这个，否则可能会出现乱码
    //                    cell.setEncoding(HSSFCell.ENCODING_UTF_16);
                        switch (cell.getCellType()) {
                            case HSSFCell.CELL_TYPE_STRING:
                                value = cell.getStringCellValue();
                                break;
                            case HSSFCell.CELL_TYPE_NUMERIC:
                                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                    Date date = cell.getDateCellValue();
                                    if (date != null) {
                                        value = new SimpleDateFormat("yyyy-MM-dd")
                                                .format(date);
                                    } else {
                                        value = "";
                                    }
                                } else {
                                    value = new DecimalFormat("0").format(cell
                                            .getNumericCellValue());
                                }
                                break;
                            case HSSFCell.CELL_TYPE_FORMULA:
                                // 导入时如果为公式生成的数据则无值
                                if (!cell.getStringCellValue().equals("")) {
                                    value = cell.getStringCellValue();
                                } else {
                                    value = cell.getNumericCellValue() + "";
                                }
                                break;
                            case HSSFCell.CELL_TYPE_BLANK:
                                break;
                            case HSSFCell.CELL_TYPE_ERROR:
                                value = "";
                                break;
                            case HSSFCell.CELL_TYPE_BOOLEAN:
                                value = (cell.getBooleanCellValue() == true ? "Y"
                                        : "N");
                                break;
                            default:
                                value = "";
                        }
//                        System.out.println(rowIndex + "," + columnIndex + ":" + value);
                        System.out.println(value);
                    }
                    if (columnIndex == 0 && value.trim().equals("")) {
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

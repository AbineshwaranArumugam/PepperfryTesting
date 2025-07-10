package com.pepperfry.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

    public static void writeFurnitureCounts(int benches, int settees, int recamiers, int metalBenches) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Furniture Counts");
        XSSFRow header = sheet.createRow(0);
        header.createCell(0).setCellValue("Furniture Type");
        header.createCell(1).setCellValue("Count");
        header.createCell(2).setCellValue("Timestamp");

        Object[][] data = {
            {"Benches", benches},
            {"Settees", settees},
            {"Recamiers", recamiers},
            {"Metal Benches", metalBenches}
        };

        int rowNum = 1;
        for (Object[] rowData : data) {
            XSSFRow row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue((String) rowData[0]);
            row.createCell(1).setCellValue((Integer) rowData[1]);
            row.createCell(2).setCellValue(timestamp());
        }

        try (FileOutputStream fileOut = new FileOutputStream(".//test-output//Excel_Files//furniture_count.xlsx")) {
            workbook.write(fileOut);
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static String timestamp() {
        return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
    }
}

package com.kenat.JFrame.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {
	
public void updateByColumn(String filePath, String column) throws Exception{
		
		FileInputStream inputStream = new FileInputStream(new File(filePath));
		Workbook workbook = WorkbookFactory.create(inputStream);
		Sheet sheet = workbook.getSheetAt(0);
		int lastRow = sheet.getLastRowNum();
		System.out.println(lastRow);
		if (lastRow > 0) {
			for (int i = 1; i < lastRow+1; i++) {//skip 1st row for csv
				Row row = sheet.getRow(i);
				row.getCell(3).setCellValue("Masked by Masking Utiliy");
			}
		}
		inputStream.close();
		FileOutputStream outputStream = new FileOutputStream(filePath);
		workbook.write(outputStream);
		outputStream.close();
	}
	
//	public static void main(String[] args) throws Exception {
//		// TODO Auto-generated method stub
//		ExcelUtil excelUtil = new ExcelUtil();
//		excelUtil.updateByColumn("C:/Users/kenat/Desktop/Test.csv", "");
//	}

}

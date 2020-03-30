/**
 * 
 */
package com.kenat.JFrame.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

/**
 * @author kenat
 *
 */
public class CsvUtil {
	
	private HashMap<String, Integer> headerMap = new HashMap<>();

	public CsvUtil(){
		headerMap.put("CPTY", 23);
	}
	
	public static void read(String filePath, String column) throws Exception {
		CsvReader csvReader = new CsvReader(filePath);

		csvReader.readHeaders();
		while (csvReader.readRecord()){
            System.out.println(csvReader.getRawRecord());
            System.out.println(csvReader.get(column));
        }
	}
	
	public void updateByColumn(String filePath, String column) throws Exception{
		
		CsvReader csvReader = new CsvReader(filePath);
		
		csvReader.readHeaders();
		List<String[]> maskedList = new ArrayList<>();
		String header  = csvReader.getRawRecord();
		maskedList.add(header.split(","));
		
		while (csvReader.readRecord()){
            String line = csvReader.getRawRecord();
            String[] orignal = line.split(",");
            orignal[headerMap.get(column)] = "Masked By Masking Utility";
            
            maskedList.add(orignal);
        }
		csvReader.close();

		CsvWriter cwriter = new CsvWriter(filePath);
		for (int i = 0; i < maskedList.size(); i++) {
			String[] masked = maskedList.get(i);
			cwriter.writeRecord(masked);
		}
		cwriter.close();
	}
	
//	public static void main(String[] args) throws Exception {
//		// TODO Auto-generated method stub
//		CsvUtil csvUtil = new CsvUtil();
//		csvUtil.updateByColumn("C:/Users/kenat/Desktop/Test.csv", "CPTY");
//	}

}

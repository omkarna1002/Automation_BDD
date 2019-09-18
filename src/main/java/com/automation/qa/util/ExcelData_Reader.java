package com.automation.qa.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class ExcelData_Reader {
            
	              public static HashMap<String, String> setMapData(Integer val) throws IOException {
	            	  String datasheet=System.getProperty("user.dir")+"\\src\\main\\java\\com\\automation\\qa\\testdata\\TestData.xlsx";         	  
	                                 FileInputStream fis = new FileInputStream(datasheet); 
	                                 Workbook workbook = new XSSFWorkbook(fis);
	                                 Sheet sheet = workbook.getSheetAt(0);        
	                                 int lastRow = sheet.getLastRowNum();                               
	                                 HashMap<String, String> dataMap = new HashMap<String, String>();
	                                 DataFormatter dataFormatter = new DataFormatter();                        
				                                 for(int i=1; i<=lastRow; i++)
				                                 {                                                              
				                                 Row row = sheet.getRow(i);
				                                 Cell valueCell = row.getCell(val);
				                                 Cell keyCell = row.getCell(0);
				                                 String value = dataFormatter.formatCellValue(valueCell);
				                                 String key = dataFormatter.formatCellValue(keyCell);
				                                 dataMap.put(key, value);                    
				                                 }                             
	                                 return dataMap;                             
	                               }                            
	                  }




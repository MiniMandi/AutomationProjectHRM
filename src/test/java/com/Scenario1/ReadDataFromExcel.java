package com.Scenario1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.*;
import org.testng.annotations.Test;

public class ReadDataFromExcel {
  @Test
  public void testReadFile() throws IOException 
  {
	  //Path of the file 
	  File f1=new File(System.getProperty("user.dir")+"//TestData//LoginCreds.xlsx");
	  
	  //Read in stream 
	  FileInputStream fs= new FileInputStream(f1);
	  
	  //Workbook --> Sheet --> row ---> cell ---> data (Logic)
	  XSSFWorkbook wb = new XSSFWorkbook(fs);
	  
	  //Number of rows
	  int rows = wb.getSheet("UserData").getPhysicalNumberOfRows();
	  System.out.println("Number of rows: "+rows);// have 9 rows
	  
	  //Number of cells 
	  int cells = wb.getSheet("UserData").getRow(0).getPhysicalNumberOfCells();
	  System.out.println("Number of cells: "+cells); 
	  
	  //Create an Array of same size like file 
	  Object data[][]=new Object[rows-1][cells]; //Reading only five 8 rows
	  
	  //Iterate array read data from file and save it in array 
	  
	  for (int i=1; i<rows; i++) 
	  {
		  for (int j=0; j<cells; j++)
		  {
			  //Array always starts with 0 index 
			  data[i-1][j]=wb.getSheet("UserData").getRow(i).getCell(j).getStringCellValue();
			  System.out.print(data[i-1][j] +"  ");
		  }
		  
		  System.out.println();
	  }
	  /**
	  //Reads only single entry
	  String value=wb.getSheet("UserData").getRow(2).getCell(0).getStringCellValue();
	  System.out.println(value);
	  **/
	  
	  
	  /**
	  XSSFSheet sheet1= wb.getSheet("UserData");
	  XSSFRow row=sheet1.getRow(1);
	  XSSFCell cell = row.getCell(0);
	  String value = cell.getStringCellValue();
	  System.out.println(value);
	  **/
	    
  }
}

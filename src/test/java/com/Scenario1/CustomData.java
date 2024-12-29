package com.Scenario1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class CustomData 

{
	public XSSFWorkbook wb;
	
	@DataProvider(name="exceldata")
	public Object[][] testData() 
	{
		 //Path of the file 
		  File f1=new File(System.getProperty("user.dir")+"//TestData//LoginCreds.xlsx");
		  
		  //Read in stream 
		  FileInputStream fs;
		try {
			fs = new FileInputStream(f1);
			 //Workbook --> Sheet --> row ---> cell ---> data (Logic)
			  wb = new XSSFWorkbook(fs);			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		    
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
				 // System.out.print(data[i-1][j] +"  ");
			  }
			  
			  System.out.println();
		  }
	
		 return data;
		  
	}

}

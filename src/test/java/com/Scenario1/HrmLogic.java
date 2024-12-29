package com.Scenario1;

import org.testng.annotations.Test;

public class HrmLogic {
  @Test (dataProvider="exceldata", dataProviderClass = CustomData.class) 
  public void testLogin(String un, String psw)
  {
	   System.out.println("UserName is: "+un);
	   System.out.println("Password is: "+psw);
	  
  }
}

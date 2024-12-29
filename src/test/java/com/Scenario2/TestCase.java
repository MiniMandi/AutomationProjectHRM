package com.Scenario2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.Generic.LoginPage;
import com.Generic.AdminPage;

public class TestCase {
    WebDriver driver; 
    LoginPage loginPage;
    AdminPage adminPage;

    @BeforeClass
    public void setup() {
        // Use WebDriverManager to handle ChromeDriver setup
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        loginPage = new LoginPage(driver);
        adminPage = new AdminPage(driver);
        
    }

    @Test(priority = 1)
    public void testValidLogin() {
        loginPage.setUsername("Admin");
        loginPage.setPassword("admin123");
        loginPage.clickLoginButton();

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/dashboard"), "Login failed");
    }

    @Test(priority = 2, dependsOnMethods = "testValidLogin")
    public void testLeftMenuOptionsCount() {
    	String currentUrl = driver.getCurrentUrl(); 
    	System.out.println("Current URL: " + currentUrl);
    	adminPage = new AdminPage(driver);
        int menuOptionsCount = adminPage.getLeftMenuOptionsCount();
        System.out.println("Number of options in the left menu: " + menuOptionsCount);
        Assert.assertEquals(menuOptionsCount, 12, "Left menu options count mismatch");

        adminPage.clickAdminTab();
        System.out.println("The admin page is open");
        
    }
    
    @Test(priority = 3, dependsOnMethods = { "testLeftMenuOptionsCount"})
    public void searchByUsername() 
    { 
    	//adminPage.clickAdminTab(); 
    	adminPage.searchByUsername("Admin"); 
    	String totalRecords = adminPage.getTotalRecordsFound(); 
    	System.out.println("Total records found: " + totalRecords); 
    	//adminPage.resetSearch(); // Refresh the page }
    }
        
    @Test(priority = 4, dependsOnMethods = {"testValidLogin", "testLeftMenuOptionsCount"})
    public void searchByUserRole() 
    { 
    	adminPage.clickAdminTab(); 
    	adminPage.searchByUserRole("Admin"); 
    	String totalRecords = adminPage.getTotalRecordsFound(); 
    	System.out.println("Total records found by role: " + totalRecords); 
    	adminPage.resetSearch(); // Refresh the page }
    }
    
    
    @Test(priority = 5, dependsOnMethods = {"testValidLogin", "testLeftMenuOptionsCount"})
    public void searchByEnabledStatus() 
    { 
    	adminPage.clickAdminTab(); 
    	adminPage.searchByEnabledStatus(); 
    	String totalRecords = adminPage.getTotalRecordsFound(); 
    	System.out.println("Total records found by enabled status: " + totalRecords); 
    	adminPage.resetSearch(); // Refresh the page 
    	} @Test(priority = 6, dependsOnMethods = {"testValidLogin", "testLeftMenuOptionsCount"})
    	public void searchByDisabledStatus() 
    	{ 
    		adminPage.clickAdminTab(); 
    		adminPage.searchByDisabledStatus(); 
    		String totalRecords = adminPage.getTotalRecordsFound(); 
    		System.out.println("Total records found by disabled status: " + totalRecords); 
    		adminPage.resetSearch(); // Refresh the page
    }
    
    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            // Log failure details if necessary
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            // Log success details if necessary
        }
    }

    @AfterClass
    public void cleanUp() {
        driver.quit();
    }
}

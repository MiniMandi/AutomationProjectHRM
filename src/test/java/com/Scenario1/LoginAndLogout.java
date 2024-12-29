package com.Scenario1;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.Generic.Utility;
import com.Listeners.ExtentITestNGListeners;
import com.Listeners.ExtentManager;


public class LoginAndLogout 
{
	@Listeners(ExtentITestNGListeners.class)
	public class LoginTest {
	    WebDriver driver;
	    ExtentReports extent;
	    ExtentTest test;
	    WebDriverWait wait;
	    Utility utility = new Utility(); 

	    @BeforeClass
	    public void setup() {
	    	driver = new ChromeDriver(); 
	        extent = ExtentManager.getInstance();
	        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	    }




		@Test(dataProvider = "exceldata", dataProviderClass = CustomData.class) 
		public void loginTest(String username, String password) {
		    test = extent.createTest("Login Test with username: " + username);
		
		    // Navigate to the login page for each test iteration
		    driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		
		    WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
		    WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
		    WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class, 'oxd-button--main orangehrm-login-button')]")));
		
		    System.out.println("usernameField: " + usernameField); // Should print for every iteration
		    usernameField.sendKeys(username);
		    passwordField.sendKeys(password);
		    loginButton.click();
		    
		    try {
				Thread.sleep(Duration.ofSeconds(3));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		    utility.getScreenShot(driver, username);
		
		    try {
		        Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"), "Valid login");
		        test.log(Status.PASS, "Login successful for valid credentials");
		    } catch (AssertionError e) {
		        test.log(Status.FAIL, "Login failed for invalid credentials");
		        throw e;
		    }
		}
		
		// Optional: Add logout between iterations for clean state
		@AfterMethod
		public void tearDown(ITestResult result) {
		    if (result.getStatus() == ITestResult.FAILURE) {
		        utility.getScreenShot(driver, result.getName());
		        test.log(Status.FAIL, result.getThrowable());
		    } else if (result.getStatus() == ITestResult.SUCCESS) {
		        test.log(Status.PASS, "Test passed");
		    }
		
		    // Logout to ensure clean state for next iteration
		    try {
		        WebElement userDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("oxd-userdropdown-name")));
		        userDropdown.click();
		        WebElement logoutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Logout']")));
		        logoutButton.click();
		    } catch (Exception e) {
		        System.out.println("Logout failed or not needed.");
		    }
		}
	}
}
		

package com.Generic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AdminPage {
	    WebDriver driver;
	    WebDriverWait wait;
	    
	    // Locators
	    //By leftMenuOptions = By.xpath(".oxd-main-menu-item");
	    //By adminTab = By.xpath("//li[1]//a[1]//span[1]']");
	    
	 // Locators 
	    //By leftMenuOptions = By.cssSelector(".oxd-main-menu"); 
	    By leftMenuOptions = By.xpath("//ul[@class='oxd-main-menu']//li");
	    By adminTab = By.xpath("//span[text()='Admin']");
	    By searchUsernameField = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[1]/div/div[2]/input"); 
	    By searchButton = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[2]/button[2]"); 
	    By totalRecords = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[2]/div/span"); 
	    By resetButton = By.xpath("//button[@type='button' and text()='Reset']");
	    By userRoleDropdownArrow = By.xpath("(//i[contains(@class,'oxd-select-text--arrow')])[1]"); 
	    By adminRoleOption = By.xpath("//div[@class='oxd-select-option']//span[text()='Admin']");
	    By userStatusDropdownArrow = By.xpath("(//i[contains(@class,'oxd-select-text--arrow')])[2]"); 
	    By enabledStatusOption = By.xpath("//div[@class='oxd-select-option']//span[contains(text(), 'Enabled')]"); 
	    By disabledStatusOption = By.xpath("//div[@class='oxd-select-option']//span[contains(text(), 'Disabled')]");
	    
	    // Constructor
	    public AdminPage(WebDriver driver) {
	        this.driver = driver; 
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	    }

	    // Methods
	    public int getLeftMenuOptionsCount() {
	    	String currentUrl = driver.getCurrentUrl(); 
	    	System.out.println("Current URL in admin: " + currentUrl);
	    	System.out.println("leftMenuOptions " + leftMenuOptions);
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(leftMenuOptions));
	        List<WebElement> options = driver.findElements(leftMenuOptions);
	        System.out.println("leftMenuOptions " + options);
	        return options.size();
	    }

	    public void clickAdminTab() {
	        //driver.findElement(adminTab).click();
	        WebElement adminLink = driver.findElement(By.xpath("//span[text()='Admin']/parent::a"));
	        adminLink.click();
	    }

	 // Ensure these methods are public 
	    public void searchByUsername(String username) 
	    { 
	    	System.out.println("Current URL in search: " + driver.getCurrentUrl()+ wait); 
	    	WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(searchUsernameField)); 
	    	System.out.println("Username field found: " + (usernameField != null)); 
	    	usernameField.clear(); //Clear any pre-filled text usernameField.sendKeys(username); 
	    	usernameField.sendKeys(username);
	    	System.out.println("Typed username: " + username); 
	    	WebElement searchBtn = wait.until(ExpectedConditions.elementToBeClickable(searchButton)); 
	    	System.out.println("Search button found: " + (searchBtn != null)); 
	    	searchBtn.click();
	    } 
	   
	    
	    public String getTotalRecordsFound() 
	    { 
	    	String text = wait.until(ExpectedConditions.visibilityOfElementLocated(totalRecords)).getText();
	    	System.out.println("TotalRecords: " + text);
	    	return wait.until(ExpectedConditions.visibilityOfElementLocated(totalRecords)).getText(); 
	    } 
	    
	    public void resetSearch() 
	    { 
	    	List<WebElement> resetButtons = driver.findElements(resetButton); 
	    	System.out.println("Reset buttons found: " + resetButtons.size()); 
	    	for (WebElement button : resetButtons) 
	    	{
	    		System.out.println("Reset button text: " + button.getText());
	    	} 
	    	
	    	try 
	    	{ 
	    		WebElement resetBtn = wait.until(ExpectedConditions.elementToBeClickable(resetButton)); 
	    		resetBtn.click(); 
	    	} 
	    	catch (Exception e) 
	    	{ 
	    		System.out.println("Exception occurred: " + e.getMessage()); 
	    	}
	       	} 

	    public void searchByUserRole(String role) 
	    { 
	    	wait.until(ExpectedConditions.elementToBeClickable(userRoleDropdownArrow)).click();
	    	wait.until(ExpectedConditions.visibilityOfElementLocated(adminRoleOption)).click(); 
	    	WebElement searchBtn = wait.until(ExpectedConditions.elementToBeClickable(searchButton)); 
	    	searchBtn.click(); 
	    }
	    
	    public void searchByEnabledStatus() 
	    { 
	    	wait.until(ExpectedConditions.elementToBeClickable(userStatusDropdownArrow)).click();
	    	wait.until(ExpectedConditions.visibilityOfElementLocated(enabledStatusOption)).click(); 
	    	WebElement searchBtn = wait.until(ExpectedConditions.elementToBeClickable(searchButton)); 
	    	searchBtn.click(); 
	    } 
	    
	    public void searchByDisabledStatus() 
	    { 
	    	wait.until(ExpectedConditions.elementToBeClickable(userStatusDropdownArrow)).click(); 
	    	wait.until(ExpectedConditions.visibilityOfElementLocated(disabledStatusOption)).click(); 
	    	WebElement searchBtn = wait.until(ExpectedConditions.elementToBeClickable(searchButton)); 
	    	searchBtn.click(); 
	    }



}




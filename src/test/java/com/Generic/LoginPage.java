package com.Generic;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    WebDriver driver;
    WebDriverWait wait;

    // Locators
    By usernameField =By.name("username");
    By passwordField =By.name("password");
    By loginButton = By.xpath("//button[contains(@class, 'oxd-button--main orangehrm-login-button')]");
        
    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Methods
    public void setUsername(String username) {
        //driver.findElement(usernameField).sendKeys(username);
    	wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField)).sendKeys(username);
    }

    public void setPassword(String password) {
        //driver.findElement(passwordField).sendKeys(password);
    	wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    	
    }
}

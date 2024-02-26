package com.abb.test.pages;
import com.abb.test.utils.ReusableFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private WebDriver driver;

    // Locators
    private By usernameField = By.id("username");
    private By passwordField = By.id("password");
    private By Login = By.id("Login-button");
    private By successMessage = By.id("successfulLogin");
    private By errorMessage = By.id("error-message"); // Assuming there's an error message element
    public ReusableFunctions reusableFunctions=new ReusableFunctions();
    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    //Actions
    //Enter username
    public void enterUsername(String username) {
        WebElement usernameElement = driver.findElement(usernameField);
        usernameElement.sendKeys(username);
    }

    // Enter password
    public void enterPassword(String password) {
        WebElement passwordElement = driver.findElement(passwordField);
        passwordElement.sendKeys(password);
    }

    // Click on login button
    public void loginbutton() {
    	reusableFunctions.clickButton(Login);
    }
    
    //Success message displayed for successful login
    public boolean isSuccessMessageDisplayed() {
        return driver.findElement(successMessage).isDisplayed();
    }

    //Error message displayed for unsuccessful login
    public boolean isErrorMessageDisplayed() {
        return driver.findElement(errorMessage).isDisplayed();
    }
    
    // Perform login
    public boolean login(String username, String password) {
    	try {
			reusableFunctions.captureScreenshot(driver);
		} catch (Exception e) {
			e.printStackTrace();
		};
        enterUsername(username);
        enterPassword(password);
        loginbutton();
        if(getError().contains("invalid")) {
        	return false;
        }
		return true;
    }

    // Check if error message is displayed
    public String getError() {    
    	 WebDriverWait wait = new WebDriverWait(driver,30); 
     	wait.until(ExpectedConditions.elementToBeClickable(errorMessage)); 
     	return driver.findElement(errorMessage).getText();
     	
     
    }
}

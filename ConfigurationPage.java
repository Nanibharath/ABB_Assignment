package com.abb.test.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.abb.test.utils.ReusableFunctions;

public class ConfigurationPage {
    private WebDriver driver;
    
    //Locators
    private By syncIntervalInput = By.id("sync-interval-input");
    private By saveButton = By.id("save-button");
    private By confirmButton = By.id("confirm-button");
    private By successMessage = By.id("success-message");
    
    //creating a new instance
    ReusableFunctions reuseFunctions=new ReusableFunctions();

    public ConfigurationPage(WebDriver driver) {
        this.driver = driver;
    }

    //Created setSyncInterval(param) method to set SyncInterval for configuration settings
    public void setSyncInterval(String interval) {
        WebElement syncIntervalInputField = driver.findElement(syncIntervalInput);
        syncIntervalInputField.clear(); 
        syncIntervalInputField.sendKeys(interval);
    }

    //Clicking save button
    public void clickSaveButton() {
        driver.findElement(saveButton).click(); 
    }
    
    //Clicking Confirm button to apply changes made in configuration page
    public void clickConfirmButton() {
        driver.findElement(confirmButton).click();
        WebDriverWait wait = new WebDriverWait(driver,30); 
    	wait.until(ExpectedConditions.alertIsPresent()); 
    	Alert alert = driver.switchTo().alert();
    	Assert.assertEquals(driver.switchTo().alert().getText(),"Do you confirm action");
    	alert.accept();
    }

    //Created isSuccessMessageDisplayed() method for success message of configuration page saved details
    public void  isSuccessMessageDisplayed() throws Exception {
       try
       {
    	   driver.findElement(successMessage).isDisplayed();
       }
       catch(Exception e)
       {
    	   e.printStackTrace();
    	   //If details not displayed than capturing screenshot of error details
    	   reuseFunctions.captureScreenshot(driver);
       } 
    }
}


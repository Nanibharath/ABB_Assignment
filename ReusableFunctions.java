package com.abb.test.utils;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class ReusableFunctions {
	
	private WebDriver driver;
	
	//Screenshot code
	public void captureScreenshot(WebDriver webdriver) throws Exception{
		//Convert web driver object to TakeScreenshot
		TakesScreenshot scrShot =((TakesScreenshot)webdriver);
		//Call getScreenshotAs method to create image file
		File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
	    File DestFile=new File("Path to save screenshot");
	    FileUtils.copyFile(SrcFile, DestFile);
	    }
	
	//Reusable clickButton(param) method by passing locator as param
	public void clickButton(By locator)
	{ 
	 	WebDriverWait wait = new WebDriverWait(driver,30); 
    	wait.until(ExpectedConditions.visibilityOfElementLocated(locator));    	
         if(driver.findElement(locator).isEnabled());
         driver.findElement(locator).click();
	}
}

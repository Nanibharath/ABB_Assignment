package com.abb.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LeftNavigationPage {
private WebDriver driver;
//Locators
private By dashboardHeader = By.id("Dashboard");
private By configurationHeader = By.id("configuration");

public LeftNavigationPage(WebDriver driver) {
	 this.driver = driver;
}

public void DashboardPage()
{
	  driver.findElement(dashboardHeader).click();
}
public void ConfigurationPage()
{
	  driver.findElement(configurationHeader).click();
}
}

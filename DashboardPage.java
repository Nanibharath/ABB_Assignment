package com.abb.test.pages;

// importing required packages
import org.openqa.selenium.*;
/*import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;*/
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import com.abb.test.utils.ReusableFunctions;

public class DashboardPage {
    private WebDriver driver;
    //Below are the sample locators for DashboardPage application
    private By factoryInventorySection = By.id("inventory-section");
    private By recentOrdersSection = By.id("orders-section");
    private By recentOrders = By.id("recentOrders");
    private By systemStatusSection = By.id("system-status-section"); //Private variable can only be accessed within the same class
    private By addOrder = By.id("addOrder");
    private By updateOrder = By.id("updateOrder");
    private By retriveOrder = By.id("retriveOrder");   
    private By placeOrder = By.id("placeOrder");  
    private By orderStatusMessage = By.id("orderStatusMessage");
    private By orderUpdatedMessage = By.id("orderUpdatedMessage");
    private By orderRetrievedMessage = By.id("orderRetrievedMessage");
    private By addressupdate = By.id("addressupdate");
    private By apply = By.id("apply");
    
    //creating a new instance of the ReusableFunctions class and assigns it to a variable named reusableFunctions.
    //This allows you to access the properties and methods of the ReusableFunctions class through the reusableFunctions variable
    ReusableFunctions reusableFunctions=new ReusableFunctions();
   
    //initializes an instance WebDriver
    public DashboardPage(WebDriver driver) {
        this.driver = driver;  //Refers to the driver instance variable of the DashboardPage class
    }

    //Covered try and catch functionality
    //Created isInventorySectionDisplayed() method to verify inventory details displayed
    public boolean isInventorySectionDisplayed() {
    	try {
        return driver.findElement(factoryInventorySection).isDisplayed();
    	}
    	catch(StaleElementReferenceException e)
    	{
    		driver.navigate().refresh();
    		return driver.findElement(factoryInventorySection).isDisplayed();
    	}
    }
    //Created isOrdersSectionDisplayed() method to verify order section displayed
    public boolean isOrdersSectionDisplayed() {
    	WebDriverWait wait = new WebDriverWait(driver,30); 
    	wait.until(ExpectedConditions.visibilityOfElementLocated(recentOrdersSection));    	
        return driver.findElement(recentOrdersSection).isDisplayed();
    }
    
    //Created isRecentOrderDisplayed() method to verify recent order displayed
    public boolean isRecentOrderDisplayed(){
    	WebDriverWait wait = new WebDriverWait(driver,30); 
    	wait.until(ExpectedConditions.visibilityOfElementLocated(recentOrders));    	
        return driver.findElement(recentOrders).isDisplayed();
    }

  //Created orderProduct() method to place new order
	public void orderProduct() {		
		reusableFunctions.clickButton(addOrder);// Here created reusable function for clickButton and passing locator as parameter to click button	
		reusableFunctions.clickButton(placeOrder);
	}
	
	//Created updateProduct() method to update address field for existing order
	public void updateProduct() {	
			reusableFunctions.clickButton(updateOrder);	
			WebDriverWait wait = new WebDriverWait(driver,30); 
	    	wait.until(ExpectedConditions.elementToBeClickable(addressupdate));	      
		        	 driver.findElement(addressupdate).clear();
		        	 driver.findElement(addressupdate).sendKeys("newAddress");
		   reusableFunctions.clickButton(apply);	
	}

	//Created RetrieveProduct() method to retrieve product details
	public void RetrieveProduct() {
			reusableFunctions.clickButton(retriveOrder);	
	}
	
	//Created verifyOrderStatusMessage() method to get order status update i.e.., Placed/Not placed
	public String verifyOrderStatusMessage(){
		WebDriverWait wait = new WebDriverWait(driver,30); 
    	wait.until(ExpectedConditions.elementToBeClickable(orderStatusMessage));	      
	    return driver.findElement(orderStatusMessage).getText();
	}
	
	//Created verifyOrderUpdatedMessage() method to get order updated message i.e.., Updated/Not Updated
	public String verifyOrderUpdatedMessage(){
		WebDriverWait wait = new WebDriverWait(driver,30); 
    	wait.until(ExpectedConditions.elementToBeClickable(orderUpdatedMessage));	      
	    return driver.findElement(orderUpdatedMessage).getText();
	}
	
	//Created verifyOrderRetrievedMessage() method to get order retrieved message i.e.., Retrieved/not Retrieved
	public String verifyOrderRetrievedMessage(){
		WebDriverWait wait = new WebDriverWait(driver,30); 
    	wait.until(ExpectedConditions.elementToBeClickable(orderRetrievedMessage));	      
	    return driver.findElement(orderRetrievedMessage).getText();
	}
    
	//Created verifyWebTable() method to verify the correct details present or not in Web table and returns a boolean
	public static boolean verifyWebTable(WebElement table, String orderId, String phoneNumber, String orderStatus) {
        // Find rows of the table
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        // Loop through the rows starting from the second row (assuming first row is header)
        for (int i = 1; i < rows.size(); i++) {
            // Find columns (cells) in each row
           List<WebElement> columns = rows.get(i).findElements(By.tagName("td"));
            // Extract data from columns
            String orderIdCell = columns.get(0).getText();
            String phoneNumberCell = columns.get(1).getText();
            String orderStatusCell = columns.get(2).getText();
            // Verify if the data matches
            if (orderIdCell.equals(orderId) && phoneNumberCell.equals(phoneNumber) && orderStatusCell.equals(orderStatus)) {
                return true;
            }
        }
        return false;
    }
	
	//Created verifyRecentTabledata() method to verify recent table data as assumed in application and returns a boolean
    public boolean verifyRecentTabledata(String orderid, String phonenumber, String orderStatus){ 
    	boolean result=false;    
        WebElement table = driver.findElement(By.id("table_id"));
        boolean found = verifyWebTable(table, orderid, phonenumber, orderStatus);
        if (found) {
            System.out.println("Data found in the web table.");
            result=true;
        } else {
            System.out.println("Data not found in the web table.");
            result=false;
        }
		return result;
    }
    
    //Created isSystemStatusSectionDisplayed() method to display system status
    public String isSystemStatusSectionDisplayed() {
    	String text = null;
        if(driver.findElement(systemStatusSection).isDisplayed())
        {
        	text=driver.findElement(systemStatusSection).getText();
        }
		return text;
    }
}

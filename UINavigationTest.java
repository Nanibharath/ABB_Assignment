package com.abb.test.tests;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.abb.test.pages.ConfigurationPage;
import com.abb.test.pages.DashboardPage;
import com.abb.test.pages.LeftNavigationPage;
import com.abb.test.pages.LoginPage;

public class UINavigationTest {
    private WebDriver driver;
    private DashboardPage dashboardPage;
    private ConfigurationPage configurationPage;
    private LeftNavigationPage leftNavigationPage;
    private LoginPage loginpage;

    @BeforeClass
    public void setUp() {
        // Set up WebDriver
        System.setProperty("webdriver.chrome.driver", "WebDriver path to be provided");
        driver = new ChromeDriver();
        // Navigate to the application URL
        driver.get("http://google.com");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        dashboardPage = new DashboardPage(driver);
        configurationPage = new ConfigurationPage(driver);
        leftNavigationPage = new LeftNavigationPage(driver);
        loginpage = new LoginPage(driver);
        
        
    }
    //@Parameters ({"val1", "val2"})
    @Test
    public void verifyLoginSuccess()
    {
    	Assert.assertTrue(loginpage.login("admin", "admin"));
    	Assert.assertTrue(loginpage.isSuccessMessageDisplayed(), "Login was successful");
    }
    
    @Test
    public void verifyLoginFailure()
    {
    	Assert.assertTrue(loginpage.login("abc123", "xyzzzz"));
    	Assert.assertTrue(loginpage.isErrorMessageDisplayed(), "Error invalid username or password");
    }

    @Test//(priority = 1, dependsOnMethods = {"verifyLoginSuccess"})
    public void verifyValidOrderTableData() {
        // Test navigation to the dashboard page
    	leftNavigationPage.DashboardPage();
        Assert.assertTrue(dashboardPage.isInventorySectionDisplayed());
        Assert.assertTrue(dashboardPage.isOrdersSectionDisplayed());
        Assert.assertTrue(dashboardPage.isRecentOrderDisplayed());
        Assert.assertTrue(dashboardPage.verifyRecentTabledata("101", "123456789", "Delivered"));
        Assert.assertEquals(dashboardPage.isSystemStatusSectionDisplayed(),"System Status ok");
    }
    
    @Test
    public void verifyInvalidOrderTabledata() {
    	leftNavigationPage.DashboardPage();    
        Assert.assertTrue(dashboardPage.isOrdersSectionDisplayed());
        Assert.assertTrue(dashboardPage.isRecentOrderDisplayed());
        Assert.assertTrue(dashboardPage.verifyRecentTabledata("000", "123456789", "Not Delivered"));
        Assert.assertEquals(dashboardPage.isSystemStatusSectionDisplayed(),"System Status not ok");
    }
    
    @Test
    public void validateOrderProdcut() {
    	leftNavigationPage.DashboardPage(); 
    	 Assert.assertTrue(dashboardPage.isInventorySectionDisplayed());
    	 dashboardPage.orderProduct();
    	 Assert.assertEquals(dashboardPage.verifyOrderStatusMessage(), "order placed successfully");
    	 
    }
    
    @Test
    public void validateUpdateProdcut() {
    	leftNavigationPage.DashboardPage(); 
    	 Assert.assertTrue(dashboardPage.isInventorySectionDisplayed());
    	 dashboardPage.updateProduct();
    	 Assert.assertEquals(dashboardPage.verifyOrderUpdatedMessage(), "order updated successfully");
    }
    
    @Test
    public void validateRetriveProdcut() {
    	leftNavigationPage.DashboardPage(); 
    	 Assert.assertTrue(dashboardPage.isInventorySectionDisplayed());
    	 dashboardPage.RetrieveProduct();
    	 Assert.assertEquals(dashboardPage.verifyOrderRetrievedMessage(), "order retrived successfully");
     }
    
    @Test
      public void testConfigurationPageNavigation() throws Exception {
        // Test navigation to the configuration page
    	leftNavigationPage.ConfigurationPage();
        configurationPage.setSyncInterval("300"); // New sync interval value
        configurationPage.clickSaveButton();
        configurationPage.clickConfirmButton();
        configurationPage.isSuccessMessageDisplayed();
    }

    @AfterClass
    public void tearDown() {
        // Close the browser after all tests are executed
        driver.quit();
    }
}

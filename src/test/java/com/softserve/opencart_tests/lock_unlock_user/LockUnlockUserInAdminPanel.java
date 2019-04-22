package com.softserve.opencart_tests.lock_unlock_user;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Verify functionality to lock / unlock user via admin panel.
 * @author Yurii Antokhiv
 * @version 1.0
 */
public class LockUnlockUserInAdminPanel {

    /**
     * Web driver.
     */
    private WebDriver driver;

    /**
     * <code>ArrayList</code> of browser tabs.
     */
    private ArrayList<String> tabs;

    /**
     * url to admin login page.
     */
    private final String URL = "http://192.168.227.130/opencart/upload/admin/";

    /**
     * Message shown if user is locked
     */
    private final String failureMessage = "Warning: No match for E-Mail Address and/or Password.";

    /**
     * Text, to verify if user is logged in.
     */
    private final String successMessage = "My Account";

    /**
     * Set properties and initialize the driver.
     */
    @BeforeClass
    public void setPropertiesAndInitializeDriver() {
        //Set Properties
        System.setProperty("webdriver.chrome.driver",
                this.getClass().getResource("/chromedriver-windows-32bit.exe").getPath());
        //Create new WebDriver object
        driver = new ChromeDriver();
        //Set window --> maximize
        driver.manage().window().maximize();
        //Set timeout 20 sec
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    /**
     * Open url with admin login page.
     */
    @Test
    public void openAdminLoginPage() {
        //Open url --> opencart
        driver.get(URL);
    }

    /**
     * Login into admin account.
     * @param adminLogin admin login
     * @param adminPassword admin password
     */
    @Test
    @Parameters({"adminLogin", "adminPassword"})
    public void loginIntoAdminPanel(String adminLogin, String adminPassword) {
        //Input login
        driver.findElement(By.id("input-username")).click();
        driver.findElement(By.id("input-username")).clear();
        driver.findElement(By.id("input-username")).sendKeys(adminLogin);
        //Input password
        driver.findElement(By.id("input-password")).click();
        driver.findElement(By.id("input-password")).clear();
        driver.findElement(By.id("input-password")).sendKeys(adminPassword);
        //Click 'Login' button
        driver.findElement(By.cssSelector("button[class*='btn']")).click();
    }

    /**
     * Open list of all customers.
     */
    @Test
    public void openCustomersList() {
        //Create action
        Actions builder = new Actions(driver);
        //Open Customer menu
        builder.moveToElement(driver.findElement(By.cssSelector("li[id='menu-customer'] > a"))).perform();
        //Click on 'Customers' button
        builder.moveToElement(driver.findElement(By.xpath("(//a[contains(text(),'Customers')])[2]"))).click().perform();
    }

    /**
     * Get customer by his email.
     * @param userMail user mail
     */
    @Test
    @Parameters({"email"})
    public void findUserByEmail(String userMail){
        //Input user mail into field 'E-Mail'
        driver.findElement(By.id("input-email")).click();
        driver.findElement(By.id("input-email")).clear();
        driver.findElement(By.id("input-email")).sendKeys(userMail);
        //Click Filter button
        driver.findElement(By.id("button-filter")).click();
    }

    /**
     * Change customer status to Disabled.
     */
    @Test
    public void editCustomerStatus() {
        //Click on Edit button
        driver.findElement(By.cssSelector("i[class*='fa-pencil']")).click();
        //Change user status to 'Disabled'
        new Select(driver.findElement(By.id("input-status"))).selectByVisibleText("Disabled");
        //Save changes
        driver.findElement(By.cssSelector("i[class*='fa-save']")).click();
    }

    /**
     * Open new tab with customer login form.
     */
    @Test
    public void openLoginPage() {
        //Open new empty tab using javascript
        ((JavascriptExecutor) driver).executeScript("window.open(\"http://192.168.227.130/opencart/upload/index.php?route=account/login\")");
        //Create array of tabs
        tabs = new ArrayList<>(driver.getWindowHandles());
        //Switch to new tab
        driver.switchTo().window(tabs.get(1));
    }

    /**
     * Attempt to login under a locked user.
     * @param userMail user email address
     * @param userPassword user password
     */
    @Test
    @Parameters({"email","userPassword"})
    public void tryToLoginWithLockedUser(String userMail, String userPassword) {
        //Input correct Login
        driver.findElement(By.id("input-email")).click();
        driver.findElement(By.id("input-email")).clear();
        driver.findElement(By.id("input-email")).sendKeys(userMail);
        //Input correct Password
        driver.findElement(By.id("input-password")).click();
        driver.findElement(By.id("input-password")).clear();
        driver.findElement(By.id("input-password")).sendKeys(userPassword);
        //Click Login button
        driver.findElement(By.cssSelector("input[value*='Login']")).click();
        //Get WebElement object
        WebElement seleniumServerVersion = driver.findElement(By.cssSelector("div[class*='alert']"));
        //Get text from WebElement
        String actual = seleniumServerVersion.getText();
        //Assert message
        Assert.assertEquals(actual, failureMessage);
    }

    /**
     * Switch to previous tab with admin tools.
     */
    @Test
    public void switchToAdminPanel() {
        //Switch to first tab
        driver.switchTo().window(tabs.get(0));
    }

    /**
     * Change customer status to Enabled.
     */
    @Test
    public void rollbackCustomerStatus() {
        //Click on Edit button
        driver.findElement(By.cssSelector("i[class*='fa-pencil']")).click();
        //Change user status to 'Enabled'
        new Select(driver.findElement(By.id("input-status"))).selectByVisibleText("Enabled");
        //Save changes
        driver.findElement(By.cssSelector("i[class*='fa-save']")).click();
    }

    /**
     * Switch back to customer page.
     */
    @Test
    public void switchToLoginPage() {
        //Switches to login page
        driver.switchTo().window(tabs.get(1));
    }

    /**
     * Attempt to login under a unlocked user.
     * @param userMail user email address
     * @param userPassword user password
     */
    @Test
    @Parameters({"email","userPassword"})
    public void tryToLoginWithUnlockedUser(String userMail, String userPassword) {
        //Input correct Login
        driver.findElement(By.id("input-email")).click();
        driver.findElement(By.id("input-email")).clear();
        driver.findElement(By.id("input-email")).sendKeys(userMail);
        //Input correct Password
        driver.findElement(By.id("input-password")).click();
        driver.findElement(By.id("input-password")).clear();
        driver.findElement(By.id("input-password")).sendKeys(userPassword);
        //Click Login button
        driver.findElement(By.cssSelector("input[value*='Login']")).click();
        //Get WebElement object
        WebElement seleniumServerVersion = driver.findElement(By.cssSelector("div[id='content'] > h2:nth-of-type(1)"));
        //Get text from WebElement
        String actual = seleniumServerVersion.getText();
        //Assert message
        Assert.assertEquals(actual, successMessage);
    }

    /**
     * Close driver and browser.
     */
    @AfterClass
    public void exit() {
        //Close driver
        driver.quit();
    }
}

package com.softserve.task1.account.lock_unlock_user;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class LockUnlockUser {
    private WebDriver driver;
    private ArrayList<String> tabs;

    private final String URL = "http://192.168.227.129/opencart/upload/admin/";
    private final String adminLogin = "admin";
    private final String adminPassword = "admin";
    private final String userMail = "john.wick.test@ukr.net";
    private final String userPassword = "qwerty";
    private final String failureMessage = "Warning: No match for E-Mail Address and/or Password.";
    private final String successMessage = "My Account";

    @BeforeClass
    public void openBrowser() {
        //Set Properties
        System.setProperty("webdriver.chrome.driver",
                this.getClass().getResource("/chromedriver-windows-32bit.exe").getPath());
        //Create new WebDriver object
        driver = new ChromeDriver();
        //Set window --> maximize
        driver.manage().window().maximize();
        //Set timeout 20 sec
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Test(priority = 1)
    public void openAdminLoginPage() {
        //Open url --> opencart
        driver.get(URL);
    }

    @Test(priority = 2)
    public void loginIntoAdminPanel() {
        //
        //Input login
        driver.findElement(By.id("input-username")).click();
        driver.findElement(By.id("input-username")).clear();
        driver.findElement(By.id("input-username")).sendKeys(adminLogin);
        //
        //Input password
        driver.findElement(By.id("input-password")).click();
        driver.findElement(By.id("input-password")).clear();
        driver.findElement(By.id("input-password")).sendKeys(adminPassword);
        //
        //Clock 'Login' button
        driver.findElement(By.cssSelector("button[class*='btn']")).click();
    }

    @Test(priority = 3)
    public void openCustomersList() {
        //
        //Create action
        Actions builder = new Actions(driver);
        //
        //Open Customer menu
        builder.moveToElement(driver.findElement(By.cssSelector("li[id='menu-customer'] > a"))).perform();
        //
        //Click on 'Customers' button
        builder.moveToElement(driver.findElement(By.xpath("(//a[contains(text(),'Customers')])[2]"))).click().perform();
    }

    @Test(priority = 4)
    public void editCustomerStatus() {
        //
        //Click on Edit button
        driver.findElement(By.cssSelector("i[class*='fa-pencil']")).click();
        //
        //Click on Status button
        driver.findElement(By.id("input-status")).click();
        //
        //Change status to disabled
        driver.findElement(By.cssSelector("#input-status > option:nth-child(2)")).click();
        //
        //Save
        driver.findElement(By.cssSelector("i[class*='fa-save']")).click();
    }

    @Test(priority = 5)
    public void openNewTab() {
        //
        //Open new empty tab using javascript
        ((JavascriptExecutor) driver).executeScript("window.open(\"http://192.168.227.129/opencart/upload/index.php?route=account/login\")");
        //
        //Create array of tabs
        tabs = new ArrayList<>(driver.getWindowHandles());
        //
        //Switches to new tab
        driver.switchTo().window(tabs.get(1));
    }

    @Test(priority = 6)
    public void tryToLoginWithLockedUser() {
        //
        //Input correct Login
        driver.findElement(By.id("input-email")).click();
        driver.findElement(By.id("input-email")).clear();
        driver.findElement(By.id("input-email")).sendKeys(userMail);
        //
        //Input correct Password
        driver.findElement(By.id("input-password")).click();
        driver.findElement(By.id("input-password")).clear();
        driver.findElement(By.id("input-password")).sendKeys(userPassword);
        //
        //Click Login button
        driver.findElement(By.cssSelector("input[value*='Login']")).click();
        //
        //Get WebElement object
        WebElement seleniumServerVersion = driver.findElement(By.cssSelector("div[class*='alert']"));
        //
        //Get text from WebElement
        String actual = seleniumServerVersion.getText();
        //
        //Assert message
        Assert.assertEquals(actual, failureMessage);
    }

    @Test(priority = 7)
    public void closeTab() {
        //
        //Close current tab using javascript
        ((JavascriptExecutor) driver).executeScript("window.close()");
        //
        //Switch to first tab
        driver.switchTo().window(tabs.get(0));
    }

    @Test(priority = 8)
    public void rollbackCustomerStatus() {
        //
        //Click on Edit button
        driver.findElement(By.cssSelector("i[class*='fa-pencil']")).click();
        //
        //Click on Status button
        driver.findElement(By.id("input-status")).click();
        //
        //Change status to disabled
        driver.findElement(By.cssSelector("#input-status > option:nth-child(1)")).click();
        //
        //Save
        driver.findElement(By.cssSelector("i[class*='fa-save']")).click();

    }

    @Test(priority = 9)
    public void openNewTab2() {
        //
        //Open new empty tab using javascript
        ((JavascriptExecutor) driver).executeScript("window.open(\"http://192.168.227.129/opencart/upload/index.php?route=account/login\")");
        //
        //Create array of tabs
        tabs = new ArrayList<>(driver.getWindowHandles());
        //
        //Switches to new tab
        driver.switchTo().window(tabs.get(1));
    }

    @Test(priority = 10)
    public void tryToLoginWithUnlockedUser() {
        //
        //Input correct Login
        driver.findElement(By.id("input-email")).click();
        driver.findElement(By.id("input-email")).clear();
        driver.findElement(By.id("input-email")).sendKeys(userMail);
        //
        //Input correct Password
        driver.findElement(By.id("input-password")).click();
        driver.findElement(By.id("input-password")).clear();
        driver.findElement(By.id("input-password")).sendKeys(userPassword);
        //
        //Click Login button
        driver.findElement(By.cssSelector("input[value*='Login']")).click();
        //
        //Get WebElement object
        WebElement seleniumServerVersion = driver.findElement(By.cssSelector("div[id='content'] > h2:nth-of-type(1)"));
        //
        //Get text from WebElement
        String actual = seleniumServerVersion.getText();
        //
        //Assert message
        Assert.assertEquals(actual, successMessage);
    }

    @Test(priority = 11)
    public void closeTab2() {
        //
        //Close current tab using javascript
        ((JavascriptExecutor) driver).executeScript("window.close()");
    }

    @AfterClass
    public void exit() {
        driver.quit();
    }

}

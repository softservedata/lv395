package com.softserve.opencart_tests.login.differentCredentials.correct_credentials;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class CheckLoginWithCorrectCredentials {
    /**
     * Web driver.
     */
    private WebDriver driver;

    /**
     * URL to opencart home page.
     */
    private final String URL = "http://192.168.227.130/opencart/upload";
    /**
     * User email address.
     */
    private final String correctEmail = "john.wick.test@ukr.net";
    /**
     * User password.
     */
    private final String correctPassword = "qwerty";

    /**
     * <code>@BeforeClass</code> method to set properties and create driver.
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
        //Open url --> opencart
        driver.get(URL);
        //Open login page
        driver.findElement(By.xpath("//a[contains(text(),'My Account')]")).click();
        driver.findElement(By.linkText("Login")).click();
    }

    /**
     * Attempt to login with correct credentials.
     */
    @Test()
    public void login() {
        // Click on 'My Account' tab
        driver.findElement(By.xpath("//a[contains(text(),'My Account')]")).click();
        //Choose 'Login' button
        driver.findElement(By.linkText("Login")).click();
        //Input email address
        driver.findElement(By.id("input-email")).click();
        driver.findElement(By.id("input-email")).clear();
        driver.findElement(By.id("input-email")).sendKeys(correctEmail);
        //Input password
        driver.findElement(By.id("input-password")).click();
        driver.findElement(By.id("input-email")).clear();
        driver.findElement(By.id("input-password")).sendKeys(correctPassword);
        //Click on 'Login' button
        driver.findElement(By.cssSelector("input[value*='Login']")).click();
        //Get WebElement
        WebElement seleniumServerVersion = driver.findElement(By.cssSelector("div[id='content'] > h2:nth-of-type(1)"));
        //Get text from WebElement
        String actual = seleniumServerVersion.getText();
        // Assert actual with 'My account' text
        Assert.assertEquals(actual,"My Account");
    }

    /**
     * Close driver and browser.
     */
    @AfterClass
    public void closeDriver() {
        //Close driver
        driver.quit();
    }
}

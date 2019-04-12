package com.softserve.task1.account.change_password;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class ChangePassword {
    private WebDriver driver;

    private static final String URL = "http://10.10.0.120/opencart/upload/";
    private static final String message = "Success: Your password has been successfully updated.";
    private static final String correctEmail = "john.wick.test@ukr.net";
    private static final String mainPassword = "qwerty";
    private static final String changedPassword = "qwerty123";

    @DataProvider
    public Object[][] password() {
        return new Object[][]{
                {changedPassword},
                {mainPassword}
        };
    }

    @BeforeClass
    public void atStart(){
        System.setProperty("webdriver.chrome.driver", "./lib/drivers/chromedriver.exe");
        System.getProperty("webdriver.chrome.driver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @BeforeMethod
    public void openBrowser(){
        driver.get(URL);
    }
    @AfterClass
    public void closeDriver(){
        driver.quit();
    }

    @BeforeMethod
    public void login(){
        // Steps
        driver.findElement(By.xpath("//a[contains(text(),'My Account')]")).click();
        //
        driver.findElement(By.linkText("Login")).click();
        //
        driver.findElement(By.id("input-email")).click();
        driver.findElement(By.id("input-email")).sendKeys(correctEmail);
        //
        driver.findElement(By.id("input-password")).click();
        driver.findElement(By.id("input-password")).sendKeys(mainPassword);
        //
        driver.findElement(By.cssSelector("input[value*='Login']")).click();
    }

    @Test(dataProvider = "password")
    public void changePassword(String password) {
        driver.get("http://10.10.0.120/opencart/upload/index.php?route=account/account");
        //
        driver.findElement(By.linkText("Change your password")).click();
        //
        driver.findElement(By.id("input-password")).click();
        driver.findElement(By.id("input-password")).sendKeys(password);
        //
        driver.findElement(By.id("input-confirm")).click();
        driver.findElement(By.id("input-confirm")).sendKeys(password);
        //
        driver.findElement(By.cssSelector("input[class*='btn-primary']")).click();
        //
        WebElement seleniumServerVersion = driver.findElement(By.cssSelector("div[class*='alert']"));
        //
        String actual = seleniumServerVersion.getText();
        // Check
        Assert.assertTrue(actual.contains(message));
    }

    @AfterMethod
    public void logout(){
        //TODO change xpath to actual | incorrect xpath
        driver.findElement(By.xpath("//li[2]/a/span[1]")).click();

        driver.findElement(By.xpath("//li[2]/a/span[1]")).click();

        WebElement elem = driver.findElement(By.cssSelector("div[id='content'] > p:nth-of-type(1)"));

        String actual = "You have been logged off your account. It is now safe to leave the computer.";
        Assert.assertEquals(actual, elem.getText());
    }
}

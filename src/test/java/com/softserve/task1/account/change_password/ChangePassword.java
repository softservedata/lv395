package com.softserve.task1.account.change_password;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ChangePassword {
    private WebDriver driver;

    private final String URL = "http://192.168.227.129/opencart/upload/index.php?route=account/login";
    private final String message = "Success: Your password has been successfully updated.";
    private final String correctEmail = "opencart.testuser@gmail.com";
    private final String mainPassword = "qwerty";
    private final String changedPassword = "qwerty123";

    @BeforeClass
    public void openBrowser() {
        //Set Properties
        System.setProperty("webdriver.chrome.driver", "./lib/drivers/chromedriver.exe");
        System.getProperty("webdriver.chrome.driver");
        //Create new WebDriver object
        driver = new ChromeDriver();
        //Set window --> maximize
        driver.manage().window().maximize();
        //Set timeout 20 sec
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test(priority = 1)
    public void openLoginPage() {
        //Open url --> opencart
        driver.get(URL);
    }

    @Test(priority = 2)
    public void login() {
        //Input correct Login
        driver.findElement(By.id("input-email")).click();
        driver.findElement(By.id("input-email")).clear();
        driver.findElement(By.id("input-email")).sendKeys(correctEmail);
        //Input correct Password
        driver.findElement(By.id("input-password")).click();
        driver.findElement(By.id("input-password")).clear();
        driver.findElement(By.id("input-password")).sendKeys(mainPassword);
        //Click Login button
        driver.findElement(By.cssSelector("input[value*='Login']")).click();
    }

    @Test(priority = 3)
    public void setNewPassword() {
        //Click on 'Change your password' button
        driver.findElement(By.linkText("Change your password")).click();
        //Input password
        driver.findElement(By.id("input-password")).click();
        driver.findElement(By.id("input-password")).sendKeys(changedPassword);
        //Confirm password
        driver.findElement(By.id("input-confirm")).click();
        driver.findElement(By.id("input-confirm")).sendKeys(changedPassword);
        //Click on '' button
        driver.findElement(By.cssSelector("input[class*='btn-primary']")).click();
        //Get web element
        WebElement seleniumServerVersion = driver.findElement(By.cssSelector("div[class*='alert']"));
        //Get text of the element
        String actual = seleniumServerVersion.getText();
        //Assert
        Assert.assertTrue(actual.contains(message));
    }

    @Test(priority = 4)
    public void logout() {
        //Click on My Account tab
        driver.findElement(By.xpath("//li[2]/a/span[1]")).click();
        //Click on Logout button
        driver.findElement(By.xpath("(//a[contains(text(),'Logout')])[1]")).click();
        //Get element
        WebElement elem = driver.findElement(By.cssSelector("div[id='content'] > h1"));
        //Get text from element
        String actual = elem.getText();
        String expected = "Account Logout";
        //Click 'Continue' button
        driver.findElement(By.linkText("Continue")).click();
        //Assert
        Assert.assertEquals(expected, actual);
    }

    @Test(priority = 5)
    public void tryToLoginWithNewPassword() {
        //Click on 'My Account' button
        driver.findElement(By.xpath("//li[2]/a/span[1]")).click();
        //Click on 'Login' button
        driver.findElement(By.linkText("Login")).click();
        //Input correct Login
        driver.findElement(By.id("input-email")).click();
        driver.findElement(By.id("input-email")).clear();
        driver.findElement(By.id("input-email")).sendKeys(correctEmail);
        //Input correct Password
        driver.findElement(By.id("input-password")).click();
        driver.findElement(By.id("input-password")).clear();
        driver.findElement(By.id("input-password")).sendKeys(changedPassword);
        //Click Login button
        driver.findElement(By.cssSelector("input[value*='Login']")).click();
    }

    @Test(priority = 6)
    public void setMainPassword() {
        //Click on 'Change your password' button
        driver.findElement(By.linkText("Change your password")).click();
        //Input password
        driver.findElement(By.id("input-password")).click();
        driver.findElement(By.id("input-password")).sendKeys(mainPassword);
        //Confirm password
        driver.findElement(By.id("input-confirm")).click();
        driver.findElement(By.id("input-confirm")).sendKeys(mainPassword);
        //Click on '' button
        driver.findElement(By.cssSelector("input[class*='btn-primary']")).click();
        //Get web element
        WebElement seleniumServerVersion = driver.findElement(By.cssSelector("div[class*='alert']"));
        //Get text of the element
        String actual = seleniumServerVersion.getText();
        //Assert
        Assert.assertTrue(actual.contains(message));
    }

    @AfterClass
    public void closeDriver() {
        driver.quit();
    }
}

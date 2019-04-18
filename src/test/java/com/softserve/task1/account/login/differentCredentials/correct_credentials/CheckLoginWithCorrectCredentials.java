package com.softserve.task1.account.login.differentCredentials.correct_credentials;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class CheckLoginWithCorrectCredentials {
    private WebDriver driver;

    private final String URL = "http://192.168.227.129/opencart/upload";
    private final String correctEmail = "opencart.testuser@gmail.com";
    private final String correctPassword = "qwerty";

    @DataProvider
    public Object[][] credentials() {
        return new Object[][]{
                {correctEmail, correctPassword} // correct email | correct password --> Logged in
        };
    }

    @BeforeClass
    public void atStart() {
        //Set Properties
        System.setProperty("webdriver.chrome.driver", "./lib/drivers/chromedriver.exe");
        System.getProperty("webdriver.chrome.driver");
        //Create new WebDriver object
        driver = new ChromeDriver();
        //Set window --> maximize
        driver.manage().window().maximize();
        //Set timeout 20 sec
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        //Open url --> opencart
        driver.get(URL);
        //Open login page
        driver.findElement(By.xpath("//a[contains(text(),'My Account')]")).click();
        driver.findElement(By.linkText("Login")).click();
    }

    @AfterClass
    public void closeDriver() {
        driver.quit();
    }

    @Test(dataProvider = "credentials")
    public void login(String email, String password) {
        // Steps
        driver.findElement(By.xpath("//a[contains(text(),'My Account')]")).click();
        //
        driver.findElement(By.linkText("Login")).click();
        //
        driver.findElement(By.id("input-email")).click();
        driver.findElement(By.id("input-email")).sendKeys(email);
        //
        driver.findElement(By.id("input-password")).click();
        driver.findElement(By.id("input-password")).sendKeys(password);
        //
        driver.findElement(By.cssSelector("input[value*='Login']")).click();
        //
        WebElement seleniumServerVersion = driver.findElement(By.cssSelector("div[id='content'] > h2:nth-of-type(1)"));
        //
        String actual = seleniumServerVersion.getText();
        //
        // Check
        Assert.assertTrue(actual.contains("My Account"));
    }
}

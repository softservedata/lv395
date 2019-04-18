package com.softserve.opencart_tests.login.differentCredentials.incorrect_credentials;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class CheckLoginWithIncorrectCredentials {
    private WebDriver driver;

    private final String failureMessage = "Warning: No match for E-Mail Address and/or Password.";
    private final String tooMuchRequestsMessage = "Warning: Your account has exceeded allowed number of login attempts. Please try again in 1 hour.";


    private final String URL = "http://192.168.227.129/opencart/upload";
    private final String correctEmail = "opencart.testuser@gmail.com";
    private final String correctPassword = "qwerty";
    private final String incorrectEmail = "not.opencart.testuser@gmail.com";
    private final String incorrectPassword = "notqwerty";
    private final String emptyEmail = "";
    private final String emptyPassword = "";

    @DataProvider
    public Object[][] credentials() {
        return new Object[][]{
                {correctEmail, incorrectPassword}, // correct email | incorrect password --> Error Message
                {incorrectEmail, correctPassword}, // incorrect email | correct password --> Error Message
                {incorrectEmail, incorrectPassword}, // incorrect email | incorrect password --> Error Message
                {emptyEmail, correctPassword}, // empty email | correct password --> Error Message
                {correctEmail, emptyPassword} // empty email | correct password --> Error Message
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
        //Input to email field
        driver.findElement(By.id("input-email")).click();
        driver.findElement(By.id("input-email")).clear();
        driver.findElement(By.id("input-email")).sendKeys(email);
        //
        //Input to password field
        driver.findElement(By.id("input-password")).click();
        driver.findElement(By.id("input-password")).clear();
        driver.findElement(By.id("input-password")).sendKeys(password);
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
        // Check message
        if (actual.contains("Your account")) {
            Assert.assertTrue(actual.contains(tooMuchRequestsMessage));
        } else {
            Assert.assertTrue(actual.contains(failureMessage));
        }
    }
}

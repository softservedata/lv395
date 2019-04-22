package com.softserve.opencart_tests.login.differentCredentials.incorrect_credentials;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class CheckLoginWithIncorrectCredentials {
    /**
     * Web driver
     */
    private WebDriver driver;
    /**
     * Message, shown when user try to login with incorrect credentials.
     */
    private final String failureMessage = "Warning: No match for E-Mail Address and/or Password.";
    /**
     * Message, shown when user have done more than 5 failure attempts to log in.
     */
    private final String tooMuchRequestsMessage = "Warning: Your account has exceeded allowed number of login attempts. Please try again in 1 hour.";
    /**
     * URL to opencart home page.
     */
    private final String URL = "http://192.168.227.130/opencart/upload";
    /**
     * User email address.
     */
    private final String correctEmail = "john.wick.test@ukt.ner";
    /**
     * User password.
     */
    private final String correctPassword = "qwerty";
    /**
     * User incorrect email address.
     */
    private final String incorrectEmail = "not.john.wick.test@ukt.ner";
    /**
     * User incorrect password.
     */
    private final String incorrectPassword = "notqwerty";
    /**
     * Empty user email address.
     */
    private final String emptyEmail = "";
    /**
     * Empty user password.
     */
    private final String emptyPassword = "";

    /**
     * <code>@DataProvider</code> with incorrect credentials to log in into account.
     * @return user email address and password
     */
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
     * Attempt to login with different credentials.
     * @param email user email address
     * @param password user password
     */
    @Test(dataProvider = "credentials")
    public void login(String email, String password) {
        //Input to email field
        driver.findElement(By.id("input-email")).click();
        driver.findElement(By.id("input-email")).clear();
        driver.findElement(By.id("input-email")).sendKeys(email);
        //Input to password field
        driver.findElement(By.id("input-password")).click();
        driver.findElement(By.id("input-password")).clear();
        driver.findElement(By.id("input-password")).sendKeys(password);
        //Click Login button
        driver.findElement(By.cssSelector("input[value*='Login']")).click();
        //Get WebElement object
        WebElement seleniumServerVersion = driver.findElement(By.cssSelector("div[class*='alert']"));
        //Get text from WebElement
        String actual = seleniumServerVersion.getText();
        // Check message
        if (actual.contains("Your account")) {
            Assert.assertTrue(actual.contains(tooMuchRequestsMessage));
        } else {
            Assert.assertTrue(actual.contains(failureMessage));
        }
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

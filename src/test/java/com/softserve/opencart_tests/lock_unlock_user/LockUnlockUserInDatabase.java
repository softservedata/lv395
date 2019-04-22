package com.softserve.opencart_tests.lock_unlock_user;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

public class LockUnlockUserInDatabase {
    /**
     * Web driver.
     */
    private WebDriver driver;
    /**
     * JDBC connection.
     */
    private Connection connection;

    /**
     * Database url.
     */
    private String db_url = "jdbc:mysql://192.168.227.130:3306/opencart?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true&useSSL=false";

    /**
     * SQL statement to change user status to Disabled.
     */
    private final String setStatusDisabled = "UPDATE opencart.oc_customer SET status = '0' WHERE email like 'john.wick.test@ukr.net'";
    /**
     * SQL statement to change user status to Enabled.
     */
    private final String setStatusEnabled = "UPDATE opencart.oc_customer SET status = '1' WHERE email like 'john.wick.test@ukr.net'";

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
        //Open login page
        driver.get("http://192.168.227.130/opencart/upload/index.php?route=account/login");
    }

    /**
     * Open JDBC connection using <code>DriverManager</code>.
     */
    @Test
    public void getConnection() {
        try {
            //Open jdbc connection
            connection = DriverManager.getConnection(db_url, "lv395", "Lv395_Taqc");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Using <code>PreparedStatement</code> set user status to Disabled.
     */
    @Test
    public void editCustomerStatus() {
        try (PreparedStatement ps = connection.prepareStatement(setStatusDisabled)){
            //Execute prepared statement
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
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
        //Assert
        Assert.assertEquals(actual, failureMessage);

    }

    /**
     * Using <code>PreparedStatement</code> set user status to Enabled.
     */
    @Test
    public void rollbackCustomerStatus() {
        try (PreparedStatement ps = connection.prepareStatement(setStatusEnabled)){
            //Execute prepared statement
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Attempt to login under a unlocked user.
     * @param userEmail user email address
     * @param userPassword user password
     */
    @Test
    @Parameters({"email","userPassword"})
    public void tryToLoginWithUnlockedUser(String userEmail, String userPassword) {
        //Input correct Login
        driver.findElement(By.id("input-email")).click();
        driver.findElement(By.id("input-email")).clear();
        driver.findElement(By.id("input-email")).sendKeys(userEmail);
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

    /**
     * Close driver, browser and connection.
     */
    @AfterClass
    public void exit() {
        //Close driver
        driver.quit();

        try {
            //Close jdbc connection
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

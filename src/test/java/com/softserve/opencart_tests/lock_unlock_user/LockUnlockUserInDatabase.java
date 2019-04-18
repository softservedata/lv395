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
    private WebDriver driver;
    private Connection connection;

    private String db_url = "jdbc:mysql://192.168.227.130:3306/opencart?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    private final String setStatusDisabled = "UPDATE opencart.oc_customer SET status = '0' WHERE email like 'john.wick.test%'";
    private final String setStatusEnabled = "UPDATE opencart.oc_customer SET status = '1' WHERE email like 'john.wick.test%'";

    private final String failureMessage = "Warning: No match for E-Mail Address and/or Password.";
    private final String successMessage = "My Account";

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
        //Open login page
        driver.get("http://192.168.227.130/opencart/upload/index.php?route=account/login");
    }

    @Test
    public void getConnection() {
        try {
            //Open jdbc connection
            connection = DriverManager.getConnection(db_url, "lv395", "Lv395_Taqc");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test(priority = 4)
    public void editCustomerStatus() {
        try(PreparedStatement ps = connection.prepareStatement(setStatusDisabled)) {
            //Execute prepared statement
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test(priority = 6)
    @Parameters({"userMail","userPassword"})
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
        //Assert message
        Assert.assertEquals(actual, failureMessage);
    }

    @Test(priority = 8)
    public void rollbackCustomerStatus() {
        try (PreparedStatement ps = connection.prepareStatement(setStatusEnabled)){
            //Execute prepared statement
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test(priority = 10)
    @Parameters({"userMail","userPassword"})
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

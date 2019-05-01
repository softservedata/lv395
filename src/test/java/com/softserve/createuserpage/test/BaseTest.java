package com.softserve.createuserpage.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

/**
 * A class that holds all the
 * driver lifecycle management code.
 */
public class BaseTest {
    /**
     * Webdriver
     */
    protected WebDriver driver;

    /**
     * URL to register page.
     */
    protected static String URL_HOME = "http://192.168.11.129/opencart/" +
            "upload/index.php?route=account/register";

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver",
                this.getClass().getResource("/chromedriver-windows-32bit.exe").getPath());
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @BeforeMethod
    public void openBrowser() {
        driver.get(URL_HOME);
    }

    @AfterMethod
    public void gotoRegister() {
        driver.get(URL_HOME);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
package com.softserve.edu;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class OrderInfoTest extends AddFunctionality{

    private WebDriver driver;
    private final String URL = "192.168.239.129";

    @BeforeClass
    public void initDriver() {
        System.setProperty("webdriver.chrome.driver",
                "./lib/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @AfterClass
    public void driverQuite() {
        driver.quit();
    }

    @BeforeMethod
    public void webServiceStart() {
        driver.get("http://192.168.239.129/opencart/upload/");
    }

    @AfterMethod
    public void cleanCart() {
        getCartCleaner(driver, URL);
    }

}

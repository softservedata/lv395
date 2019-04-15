package com.softserve.edu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class OtherFunctionalityTest extends AddFunctionality{

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

    @Test
    public void checkPriceTextButton() {
        driver.findElement(By.cssSelector("div[id='content'] > div:nth-of-type(2) > div:nth-of-type(1) > div > div:nth-of-type(3) > button:nth-of-type(1)")).click();
        driver.findElement(By.cssSelector("div[id='content'] > div:nth-of-type(2) > div:nth-of-type(2) > div > div:nth-of-type(3) > button:nth-of-type(1)")).click();
        driver.findElement(By.cssSelector("div[id='content'] > div:nth-of-type(2) > div:nth-of-type(3) > div > div:nth-of-type(3) > button:nth-of-type(1)")).click();
        driver.findElement(By.cssSelector("div[id='content'] > div:nth-of-type(2) > div:nth-of-type(4) > div > div:nth-of-type(3) > button:nth-of-type(1)")).click();
        driver.navigate().refresh();
        WebElement priceTextButton = driver.findElement(By.id("cart-total"));
        String actual = priceTextButton.getText();
        Assert.assertTrue(actual.contains("4 item(s) - $933.20"));
    }

}

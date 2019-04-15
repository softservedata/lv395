package com.softserve.edu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class OrderInfoTest extends AddFunctionality{

    private WebDriver driver;
    private final String URL = "192.168.239.129";

    private static double expectedEcoTax = 4.0;
    private static double expectedVat = (601.00 * 20)/100;
    private static double expectedTotal = 725.20;
    private static double expectedSubTotal = (expectedTotal - expectedVat) - expectedEcoTax;

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

    @DataProvider(name = "CheckCartTableBoxes")
    public static Object[][] cartTableBoxesData() {
        return new Object[][]{
                {"//*[@id=\"cart\"]/ul/li[2]/div/table/tbody/tr[2]/td[2]", expectedEcoTax },
                {"//*[@id=\"cart\"]/ul/li[2]/div/table/tbody/tr[3]/td[2]", expectedVat},
                {"//*[@id=\"cart\"]/ul/li[2]/div/table/tbody/tr[1]/td[2]", expectedSubTotal},
                {"//*[@id=\"cart\"]/ul/li[2]/div/table/tbody/tr[4]/td[2]", expectedTotal}
        };
    }

    @Test(dataProvider = "CheckCartTableBoxes")
    public void checkCartTableBoxes(String xpath, double expectedValue) {
        driver.findElement(By.cssSelector("div[id='content'] > div:nth-of-type(2) > div:nth-of-type(1) > div > div:nth-of-type(3) > button:nth-of-type(1)")).click();
        driver.navigate().refresh();
        driver.findElement(By.cssSelector("div[id='content'] > div:nth-of-type(2) > div:nth-of-type(2) > div > div:nth-of-type(3) > button:nth-of-type(1)")).click();
        driver.navigate().refresh();
        driver.findElement(By.id("cart")).click();
        WebElement dataInBox = driver.findElement(By.xpath(xpath));
        String tmpValue = dataInBox.getText().replaceAll("[^0-9.]+", "");
        double actualValue = Double.parseDouble(tmpValue);
        System.out.println("Test actual result: " + actualValue);
        Assert.assertEquals(actualValue, expectedValue);
        driver.navigate().refresh();
    }

}

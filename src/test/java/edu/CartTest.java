package edu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class CartTest {

    private WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver",
                "./lib/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    @BeforeMethod
    public void beforeMethod() {
        driver.get("http://192.168.239.128/opencart/upload/");
    }

/*    @AfterMethod
    public void afterMethod() {
        driver.findElement(By.cssSelector("button[class*='btn-inverse']")).click();
        driver.findElement(By.cssSelector("button[class*='btn-danger']")).click();
        driver.navigate().refresh();
    }*/

    @DataProvider
    public Object[][] buttonSelectorForSimpleObjectsProvider() {
        String firstSelector = "div[id='content'] > div:nth-of-type(2) > div:nth-of-type(1) > div > div:nth-of-type(3) > button:nth-of-type(1)";
        String secondSelector = "div[id='content'] > div:nth-of-type(2) > div:nth-of-type(2) > div > div:nth-of-type(3) > button:nth-of-type(1)";
        return new Object[][]{
                {firstSelector, "MacBook"},
                {secondSelector, "iPhone"},
        };
    }

    @Test
    public void addOneItemTest() {
        driver.findElement(By.cssSelector("div[id='content'] > div:nth-of-type(2) > div:nth-of-type(1) > div > div:nth-of-type(3) > button:nth-of-type(1)")).click();
        driver.navigate().refresh();
        driver.findElement(By.id("cart")).click();
        WebElement namePlate = driver.findElement(By.cssSelector("td[class*='text-left']"));
        WebElement countPlate = driver.findElement(By.cssSelector("table[class*='table-striped'] > tbody > tr > td:nth-of-type(3)"));
        WebElement pricePlate =driver.findElement(By.cssSelector("table[class*='table-striped'] > tbody > tr > td:nth-of-type(4)"));
        String actual = namePlate.getText();
        Assert.assertTrue(actual.contains("MacBook"));
    }

    @Test
    public void addToCartComplicateObjectButtonTest() {
        driver.findElement(By.cssSelector("div[id='content'] > div:nth-of-type(2) > div:nth-of-type(4) > div > div:nth-of-type(3) > button:nth-of-type(1)")).click();
        driver.findElement(By.cssSelector("#input-option226 > option:nth-child(2)")).click();
        driver.findElement(By.id("button-cart")).click();
        driver.navigate().refresh();
        WebElement priceTextButton = driver.findElement(By.id("cart-total"));
        String actual = priceTextButton.getText();
        System.out.println(actual + " : " + "1 item(s) - $98.00");
        Assert.assertTrue(actual.contains("1 item(s) - $98.00"));
    }


    @Test
    public void checkPriceTextButton() {
        driver.findElement(By.cssSelector("a[href*='http://taqc-opencart.epizy.com/index.php?route=product/product&product_id=43']")).click();
        driver.findElement(By.id("button-cart")).click();
        driver.navigate().refresh();
        WebElement priceTextButton = driver.findElement(By.id("cart-total"));
        String actual = priceTextButton.getText();
        Assert.assertTrue(actual.contains("1 item(s) - $602.00"));
    }

    @Test
    public void checkPriceTextButtonFourElements() {
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
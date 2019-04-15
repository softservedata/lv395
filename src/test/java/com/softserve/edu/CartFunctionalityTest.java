package com.softserve.edu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CartFunctionalityTest extends AddFunctionality {

    private WebDriver driver;
    private final String URL = "192.168.239.129";

    @BeforeClass
    public void initDriver() {
        System.setProperty("webdriver.chrome.driver",
                "./lib/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterClass
    public void driverQuite() {
        driver.quit();
    }

    @BeforeMethod
    public void webServiceStart() {
        driver.get("http://" + URL + "/opencart/upload/");
    }

    @AfterMethod
    public void cleanCart() {
        getCartCleaner(driver, URL);
    }

    @Test(priority = 1)
    public void viewCartButtonTest() {
        driver.findElement(By.cssSelector("div[id='content'] > div:nth-of-type(2) > div:nth-of-type(1) > div > div:nth-of-type(3) > button:nth-of-type(1)")).click();
        driver.navigate().refresh();
        driver.findElement(By.id("cart")).click();
        driver.findElement(By.partialLinkText("View Cart")).click();
        System.out.println("Test 1 actual result: " + driver.getCurrentUrl());
        Assert.assertEquals(driver.getCurrentUrl(), "http://" + URL + "/opencart/upload/index.php?route=checkout/cart");
        driver.navigate().refresh();
    }

    @Test(priority = 2)
    public void checkoutButtonTest() {
        driver.findElement(By.cssSelector("div[id='content'] > div:nth-of-type(2) > div:nth-of-type(1) > div > div:nth-of-type(3) > button:nth-of-type(1)")).click();
        driver.navigate().refresh();
        driver.findElement(By.id("cart")).click();
        driver.findElement(By.partialLinkText("Checkout")).click();
        int checkoutValidElement = driver.findElements(By.id("accordion")).size();
        boolean isElementPresent = checkoutValidElement > 0;
        Assert.assertTrue(isElementPresent);
        driver.navigate().refresh();
    }

    @Test(priority = 3)
    public void removeOneItemTest() {
        driver.findElement(By.cssSelector("a[href='http://" + URL + "/opencart/upload/index.php?route=product/product&product_id=43']")).click();
        WebElement inputField = driver.findElement(By.cssSelector("input[name='quantity']"));
        inputField.clear();
        inputField.sendKeys("5");
        driver.findElement(By.id("button-cart")).click();
        driver.findElement(By.id("logo")).click();
        driver.findElement(By.id("cart")).click();
        driver.findElement(By.cssSelector("[title^='Remove'")).click();
        Assert.assertFalse(driver.findElements(By.xpath("//*[@id=\"cart\"]/ul/li[1]/table/tbody")).size() == 0);
        driver.navigate().refresh();
    }

    @Test(priority = 4)
    public void removeAllItemsTest() {
        driver.findElement(By.cssSelector("div[id='content'] > div:nth-of-type(2) > div:nth-of-type(1) > div > div:nth-of-type(3) > button:nth-of-type(1)")).click();
        driver.findElement(By.cssSelector("div[id='content'] > div:nth-of-type(2) > div:nth-of-type(2) > div > div:nth-of-type(3) > button:nth-of-type(1)")).click();
        driver.navigate().refresh();
        driver.findElement(By.id("cart")).click();
        List<WebElement> removeButtons = driver.findElements(By.cssSelector("[title^='Remove'"));
        for(WebElement i : removeButtons) {
            i.click();
            removeButtons.remove(i);
        }
        Assert.assertFalse(driver.findElements(By.xpath("//*[@id=\"cart\"]/ul/li[1]/table/tbody")).size() == 0);
        driver.navigate().refresh();
    }

    @Test(priority = 5)
    public void clickOnItemTest() {
        driver.findElement(By.cssSelector("div[id='content'] > div:nth-of-type(2) > div:nth-of-type(1) > div > div:nth-of-type(3) > button:nth-of-type(1)")).click();
        driver.navigate().refresh();
        driver.findElement(By.id("cart")).click();
        WebElement namePlate = driver.findElement(By.className("text-left"));
        namePlate.findElement(By.tagName("a")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "http://" + URL + "/opencart/upload/index.php?route=product/product&product_id=43");
        driver.navigate().refresh();
    }

}

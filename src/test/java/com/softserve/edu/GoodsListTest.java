package com.softserve.edu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class GoodsListTest {

    private WebDriver driver;

    @BeforeClass
    public void initializeDriver() {
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
        //WebElements initialization
        List<WebElement> cartElementsTableRows;
        WebElement cartElementsTable = driver.findElement(By.xpath("//*[@id=\"cart\"]/ul/li[1]/table/tbody"));
        cartElementsTableRows = cartElementsTable.findElements(By.tagName("tr"));
        //Cycle which deleting elements from cart
        for(int countOfElements = cartElementsTableRows.size(); countOfElements > 0 ; countOfElements--) {
            driver.findElement(By.id("cart")).click();
            driver.findElement(By.cssSelector("button[class*='btn-danger']")).click();
            cartElementsTableRows.remove(countOfElements-1);
            driver.navigate().refresh();
        }
    }

    @Test(priority = 1)
    public void addOneItemTest() {
        //Adding goods to the cart
        driver.findElement(By.cssSelector("div[id='content'] > div:nth-of-type(2) > div:nth-of-type(1) > div > div:nth-of-type(3) > button:nth-of-type(1)")).click();
        driver.navigate().refresh();
        driver.findElement(By.id("cart")).click();
        //WebElements initialization
        WebElement goodPlate = driver.findElement(By.xpath("//*[@id=\"cart\"]/ul/li[1]/table/tbody/tr"));
        String actualGoodPlate = goodPlate.getText();
        //Printing results
        System.out.println("Test 1 actual result: " + actualGoodPlate);
        //Asserting results
        Assert.assertTrue(actualGoodPlate.contains("MacBook" + " " + "x 1" + " " + "$602.00"));
        driver.navigate().refresh();
    }

    @Test(priority = 2)
    public void increaseQuantityOfItemsTest() {
        List<WebElement> cartElementsTableRows;
        //Adding goods to the cart
        for(int i = 0; i < 3; i++) {
            driver.findElement(By.cssSelector("div[id='content'] > div:nth-of-type(2) > div:nth-of-type(1) > div > div:nth-of-type(3) > button:nth-of-type(1)")).click();
            driver.navigate().refresh();
            driver.findElement(By.cssSelector("div[id='content'] > div:nth-of-type(2) > div:nth-of-type(2) > div > div:nth-of-type(3) > button:nth-of-type(1)")).click();
            driver.navigate().refresh();
        }
        driver.findElement(By.id("cart")).click();
        //WebElements initialization
        WebElement cartElementsTable = driver.findElement(By.xpath("//*[@id=\"cart\"]/ul/li[1]/table/tbody"));
        cartElementsTableRows = cartElementsTable.findElements(By.tagName("tr"));
        //Printing results
        for (WebElement e: cartElementsTableRows) {
            System.out.println("Test 2 actual result: " + e.getText());
        }
        //Asserting results
        Assert.assertTrue(cartElementsTableRows.get(0).getText().contains("iPhone" + " " + "x 3" + " " + "$369.60"));
        Assert.assertTrue(cartElementsTableRows.get(1).getText().contains("MacBook" + " " + "x 3" + " " + "$1,806.00"));
        driver.navigate().refresh();
    }

    @Test(priority = 3)
    public void addSameItemMultipleTimesTest() {
        //Adding goods to the cart
        for (int i = 0; i < 5; i++) {
            driver.findElement(By.cssSelector("div[id='content'] > div:nth-of-type(2) > div:nth-of-type(1) > div > div:nth-of-type(3) > button:nth-of-type(1)")).click();
            driver.navigate().refresh();
        }
        driver.findElement(By.id("cart")).click();
        //WebElements initialization
        WebElement goodPlate = driver.findElement(By.xpath("//*[@id=\"cart\"]/ul/li[1]/table/tbody/tr"));
        String actualGoodPlate = goodPlate.getText();
        //Printing results
        System.out.println("Test 3 actual result: " + actualGoodPlate);
        //Asserting results
        Assert.assertTrue(actualGoodPlate.contains("MacBook" + " " + "x 5" + " " + "$3,010.00"));
        driver.navigate().refresh();
    }

    @Test(priority = 4)
    public void addItemFromProductPageTest() {
        //Adding goods to the cart
        driver.findElement(By.cssSelector("a[href='http://192.168.239.129/opencart/upload/index.php?route=product/product&product_id=43']")).click();
        driver.findElement(By.id("button-cart")).click();
        driver.findElement(By.id("logo")).click();
        driver.findElement(By.id("cart")).click();
        //WebElements initialization
        WebElement goodPlate = driver.findElement(By.xpath("//*[@id=\"cart\"]/ul/li[1]/table/tbody/tr"));
        String actualGoodPlate = goodPlate.getText();
        //Printing results
        System.out.println("Test 4 actual result: " + actualGoodPlate);
        //Asserting results
        Assert.assertTrue(actualGoodPlate.contains("MacBook" + " " + "x 1" + " " + "$602.00"));
        driver.navigate().refresh();
    }

}
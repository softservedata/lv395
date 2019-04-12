package com.softserve.edu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class GoodsListTest {

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
        driver.get("http://192.168.239.129/opencart/upload/");
    }

/*    @AfterMethod
    public void afterMethod() {
        driver.findElement(By.cssSelector("button[class*='btn-inverse']")).click();
        driver.findElement(By.cssSelector("button[class*='btn-danger']")).click();
        driver.navigate().refresh();
    }*/

    @Test
    public void addOneItemTest() {
        //Adding goods to the cart
        driver.findElement(By.cssSelector("div[id='content'] > div:nth-of-type(2) > div:nth-of-type(1) > div > div:nth-of-type(3) > button:nth-of-type(1)")).click();
        driver.navigate().refresh();
        driver.findElement(By.id("cart")).click();
        //WebElements initialization
        WebElement goodPlate = driver.findElement(By.xpath("//*[@id=\"cart\"]/ul/li[1]/table/tbody/tr"));
        String actualGoodPlate = goodPlate.getText();
        //Printing results
        System.out.println(actualGoodPlate);
        //Asserting results
        Assert.assertTrue(actualGoodPlate.contains("MacBook" + " " + "x 1" + " " + "$602.00"));
    }

    @Test
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
            System.out.println(e.getText());
        }
        //Asserting results
        Assert.assertTrue(cartElementsTableRows.get(0).getText().contains("iPhone" + " " + "x 3" + " " + "$369.60"));
        Assert.assertTrue(cartElementsTableRows.get(1).getText().contains("MacBook" + " " + "x 3" + " " + "$1,806.00"));
    }

}
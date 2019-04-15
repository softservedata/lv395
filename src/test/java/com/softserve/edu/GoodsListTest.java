package com.softserve.edu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class GoodsListTest extends AddFunctionality {

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
        driver.findElement(By.cssSelector("a[href='http://" + URL + "/opencart/upload/index.php?route=product/product&product_id=43']")).click();
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

    @Test(priority = 5)
    public void addMoreItemsThenInStockTest() {
        int valueInStack;
        driver.get("http://192.168.239.129/opencart/upload/admin/");
        driver.findElement(By.id("input-username")).sendKeys("admin");
        driver.findElement(By.id("input-password")).sendKeys("admin");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        driver.findElement(By.id("button-menu")).click();
        driver.findElement(By.linkText("Catalog")).click();
        driver.findElement(By.xpath("(//a[contains(text(),'Products')])[1]")).click();
        WebElement itemsInStack = driver.findElement(By.cssSelector("#form-product > div > table > tbody > tr:nth-child(11) > td:nth-child(6) > span"));
        valueInStack = Integer.parseInt(itemsInStack.getText()) + 100;
        driver.get("http://192.168.239.129/opencart/upload/");
        driver.findElement(By.cssSelector("a[href='http://192.168.239.129/opencart/upload/index.php?route=product/product&product_id=43']")).click();
        WebElement inputField = driver.findElement(By.cssSelector("input[name='quantity']"));
        inputField.clear();
        inputField.sendKeys(Integer.toString(valueInStack));
        driver.findElement(By.id("button-cart")).click();
        driver.findElement(By.id("logo")).click();
        driver.findElement(By.id("cart")).click();
        WebElement goodPlate = driver.findElement(By.xpath("//*[@id=\"cart\"]/ul/li[1]/table/tbody/tr"));
        String actualGoodPlate = goodPlate.getText();
        System.out.println("Test 5 actual result: " + actualGoodPlate);
        Assert.assertFalse(actualGoodPlate.contains("MacBook" + " " + "x " + valueInStack + " " + "$619,458.00"));
        driver.navigate().refresh();
    }

    @Test(priority = 6)
    public void addItemsWithAdditionalParametersTest() {
        //Adding goods to the cart
        driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[4]/div/div[3]/button[1]")).click();
        driver.findElement(By.id("input-option226")).click();
        driver.findElement(By.cssSelector("#input-option226 > option:nth-child(2)")).click();
        driver.findElement(By.id("button-cart")).click();
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("logo")));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.id("logo")).click();
        driver.navigate().refresh();
        driver.findElement(By.id("cart")).click();
        //WebElements initialization
        WebElement goodPlate = driver.findElement(By.xpath("//*[@id=\"cart\"]/ul/li[1]/table/tbody/tr"));
        String actualGoodPlate = goodPlate.getText();
        System.out.println("Test 6 actual result: " + actualGoodPlate);
        //Printing results
        //Asserting results
        Assert.assertTrue(actualGoodPlate.contains("Canon EOS 5D\n" +
                "- Select Red x 1 $98.00"));
        driver.navigate().refresh();
    }

}
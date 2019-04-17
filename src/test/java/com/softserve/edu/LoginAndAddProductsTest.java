package com.softserve.edu;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.lang.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class LoginAndAddProductsTest {

    protected ChromeDriver driver;
 /*   private int quanityValue;
    private double total;
    private double unitPrice;*/
    private final String OPEN_CART_URL = "http://192.168.234.129/opencart/upload/";
   /* protected String editField = "#content > form > div > table > tbody > tr:nth-child(1) > td:nth-child(4) > div > input";*/

    public String DELETE = "#content > h2";// "#content > form > div > table > tbody > tr:nth-child(1) > td:nth-child(4) > div > span > button.btn.btn-danger";



    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver",
                "./lib/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get(OPEN_CART_URL);
        driver.manage().window().maximize();
    }

    @AfterClass
    public void afterClass() throws Exception {
        driver.quit();
    }

    @BeforeMethod
    public void beforeMethod() throws Exception {
    }

    @AfterMethod
    public void afterMethod() throws Exception {
    }

    // * * * * * * * * * * * * * * * * * * * * * * * * * * L O G I N * * * * * * * * * * * * * * * * * * * * * * * * * *
    @Test(priority = 1)
    public void logIn() {
        driver.get(OPEN_CART_URL + "index.php?route=account/login");
        driver.findElement(By.id("input-email")).click();
        driver.findElement(By.id("input-email")).sendKeys("yuriykril7773@gmail.com");
        driver.findElement(By.id("input-password")).click();
        driver.findElement(By.id("input-password")).sendKeys("qwerty" + Keys.ENTER);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assert.assertTrue(driver.findElement(By.xpath("(//a[contains(text(),'Logout')])[1]")).isEnabled());
    }

    // * * * * * * * * * * * * * * * * * * * * * * * * * L O G O U T * * * * * * * * * * * * * * * * * * * * * * * * * *
    @Test(priority = 5)
    public void logOut() {
        driver.findElement(By.cssSelector("i[class='fa fa-shopping-cart']")).click();
        while (driver.findElements(By.cssSelector("#content > form")).size() > 0){
            driver.findElement(By.cssSelector("#content > form > div > table > tbody > tr:nth-child(1) > td:nth-child(4) > div > span > button.btn.btn-danger")).click();
            driver.navigate().refresh();
        }
        driver.findElement(By.cssSelector("#top-links > ul > li.dropdown > a")).click();
        driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();
        driver.findElement(By.cssSelector("i[class='fa fa-shopping-cart']")).click();
        Assert.assertTrue(driver.findElements(By.cssSelector("#content > form")).size() == 0);
    }

    // * * * * * * * * * * * * * * * * * * * A D D * T H R E E * P R O D U C T S * * * * * * * * * * * * * * * * * * * *
    @Test (priority = 2)
    public void addProductsTest() {
        driver.get(OPEN_CART_URL);
        driver.findElement(By.cssSelector("#content > div.row > div:nth-child(1) > div > div.button-group > button:nth-child(1)")).click();
        driver.findElement(By.cssSelector("#content > div.row > div:nth-child(4) > div > div.button-group > button:nth-child(1)")).click();
        driver.findElement(By.cssSelector("#input-option226 > option:nth-child(2)")).click();
        driver.findElement(By.cssSelector("#input-quantity")).click();
        driver.findElement(By.cssSelector("#input-quantity")).sendKeys(Keys.BACK_SPACE + "2" + Keys.ENTER);
        driver.findElement(By.id("button-cart")).click();
        driver.findElement(By.cssSelector("a[href$='index.php?route=common/home']")).click();
        driver.findElement(By.xpath("//a[contains(text(),'Phones & PDAs')]")).click();
        driver.findElement(By.linkText("Palm Treo Pro")).click();
        driver.findElement(By.id("input-quantity")).click();
        driver.findElement(By.id("input-quantity")).sendKeys(Keys.BACK_SPACE + "4");
        driver.findElement(By.id("input-quantity")).click();
        driver.findElement(By.id("button-cart")).click();
        driver.findElement(By.cssSelector("i[class='fa fa-shopping-cart']")).click();
        int expectedRowCount = driver.findElements(By.cssSelector("#content > form > div > table > tbody > tr")).size();
        Assert.assertTrue(expectedRowCount > 2);
    }

/*
    // * * * * * * * * * * * * * * * * * * * * * R E F R E S H * B U T T O N * * * * * * * * * * * * * * * * * * * * * *
    @Test (priority = 3)
    public void checkRefreshButtonOnShoppingCartTest() throws Exception {
        driver.findElement(By.cssSelector("i[class='fa fa-shopping-cart']")).click();
        quanityValue = Integer.parseInt(driver.findElement(By.cssSelector(editField)).getAttribute("value"));
        total = getFloatNumber(driver.findElement(By.cssSelector("#content > form > div > table > tbody > tr:nth-of-type(1) > td:nth-of-type(6)")).getText());
        unitPrice = getFloatNumber(driver.findElement(By.cssSelector("#content > form > div > table > tbody > tr:nth-child(1) > td:nth-child(5)")).getText());
        double expectedTotal = (double) Math.round((total + unitPrice + unitPrice) * 100) / 100;
        driver.findElement(By.cssSelector(editField)).clear();
        String newQuanityValue = String.valueOf(quanityValue + 2);
        driver.findElement(By.cssSelector(editField)).sendKeys(Keys.BACK_SPACE + newQuanityValue);
        driver.findElement(By.cssSelector("div[class*='table-responsive'] > table > tbody > tr:nth-of-type(1) > td:nth-of-type(4) > div > span > button:nth-of-type(1)")).click();
        double actualTotal = getFloatNumber(driver.findElement(By.cssSelector("#content > form > div > table > tbody > tr:nth-child(1) > td:nth-child(6)")).getText());
        System.out.println("delete: " + driver.findElement(By.cssSelector("#content > h1")).getText());
        Assert.assertEquals(actualTotal, expectedTotal);
    }

    @Test (priority = 4)
    public void checkRefreshTotalWithCentsTest() throws  Exception {
        driver.findElement(By.cssSelector("i[class='fa fa-shopping-cart']")).click();
        quanityValue = Integer.parseInt(driver.findElement(By.cssSelector("#content > form > div > table > tbody > tr:nth-child(3) > td:nth-child(4) > div > input")).getAttribute("value"));
        total = getFloatNumber(driver.findElement(By.cssSelector("div[class*='table-responsive'] > table > tbody > tr:nth-of-type(3) > td:nth-of-type(6)")).getText());
        System.out.println("total" + total);
        unitPrice = getFloatNumber(driver.findElement(By.cssSelector("div[class*='table-responsive'] > table > tbody > tr:nth-of-type(3) > td:nth-of-type(5)")).getText());
        System.out.println("unitPrice" + unitPrice);
        double expectedTotal = (double) Math.round((total + unitPrice) * 100) / 100;
        System.out.println("expectedTotal" + expectedTotal);
        driver.findElement(By.cssSelector("#content > form > div > table > tbody > tr:nth-child(3) > td:nth-child(4) > div > input")).clear();
        String newQuanityValue = String.valueOf(quanityValue + 1);
        driver.findElement(By.cssSelector("#content > form > div > table > tbody > tr:nth-child(3) > td:nth-child(4) > div > input")).sendKeys(Keys.BACK_SPACE + newQuanityValue);
        driver.findElement(By.cssSelector("div[class*='table-responsive'] > table > tbody > tr:nth-of-type(3) > td:nth-of-type(4) > div > span > button:nth-of-type(1) > i")).click();
        double actualTotal = getFloatNumber(driver.findElement(By.cssSelector("div[class*='table-responsive'] > table > tbody > tr:nth-of-type(3) > td:nth-of-type(6)")).getText());
        System.out.println("delete: " + driver.findElement(By.cssSelector("#content > h1")).getText());
        Assert.assertEquals(actualTotal, expectedTotal);
        Assert.assertTrue(driver.findElement(By.cssSelector("div[id='content'] > p")).isEnabled());
    }*/


}


//driver.findElement(By.cssSelector("a[href*='http://192.168.234.129/opencart/upload/index.php?route=common/home']")).click();


        /*
         //**************************** L O G I N 1 ********************************************************************
        driver.get(OPEN_CART_URL + "index.php?route=account/login");
        driver.findElement(By.id("input-email")).click();
        driver.findElement(By.id("input-email")).sendKeys("yuriykril7773@gmail.com");
        driver.findElement(By.id("input-password")).click();
        driver.findElement(By.id("input-password")).sendKeys("qwerty" + Keys.ENTER);

        //**************************** L O G I N 2 ********************************************************************

        //**************************** A D D **************************************************************************
        driver.get("http://192.168.234.129/opencart/upload/index.php?");
        driver.findElement(By.cssSelector("#content > div.row > div:nth-child(1) > div > div.button-group > button:nth-child(1)")).click();
        driver.findElement(By.cssSelector("#content > div.row > div:nth-child(4) > div > div.button-group > button:nth-child(1)")).click();
        driver.findElement(By.cssSelector("#input-option226 > option:nth-child(2)")).click();
        driver.findElement(By.cssSelector("#input-quantity")).click();
        driver.findElement(By.cssSelector("#input-quantity")).sendKeys(Keys.BACK_SPACE + "2" + Keys.ENTER);
        driver.findElement(By.id("button-cart")).click();
        driver.findElement(By.cssSelector("a[href*='http://192.168.234.129/opencart/upload/index.php?route=common/home']")).click();
        driver.findElement(By.xpath("//a[contains(text(),'Phones & PDAs')]")).click();
        driver.findElement(By.linkText("Palm Treo Pro")).click();
        driver.findElement(By.id("input-quantity")).click();
        driver.findElement(By.id("input-quantity")).sendKeys(Keys.BACK_SPACE + "4");
        driver.findElement(By.id("input-quantity")).click();
        driver.findElement(By.id("button-cart")).click();*/
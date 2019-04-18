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
    protected final String OPEN_CART_URL = "http://192.168.234.130/opencart/upload/";


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
        driver.findElement(By.cssSelector("i[class='fa fa-shopping-cart']")).click();
        while (driver.findElements(By.cssSelector("#content > form")).size() > 0){
            driver.findElement(By.cssSelector("#content > form > div > table > tbody > tr:nth-child(1) > td:nth-child(4) > div > span > button.btn.btn-danger")).click();
            driver.navigate().refresh();
        }
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


}
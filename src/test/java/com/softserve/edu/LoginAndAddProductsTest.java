package com.softserve.edu;
import org.testng.Assert;
import java.lang.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import java.util.concurrent.TimeUnit;

public class LoginAndAddProductsTest extends Helper {

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
        logIn();
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
    public void verifylogIn() {
        logIn();
        Assert.assertTrue(driver.findElement(By.xpath("(//a[contains(text(),'Logout')])[1]")).isEnabled());
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

    // * * * * * * * * * * * * * * * * * * * * * * * * * L O G O U T * * * * * * * * * * * * * * * * * * * * * * * * * *
    @Test(priority = 8)
    public void logOut() {
        driver.findElement(By.cssSelector("#top-links > ul > li.dropdown > a")).click();
        driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();
        driver.findElement(By.cssSelector("i[class='fa fa-shopping-cart']")).click();
        Assert.assertTrue(driver.findElements(By.cssSelector("#content > form")).size() == 0);
    }




}
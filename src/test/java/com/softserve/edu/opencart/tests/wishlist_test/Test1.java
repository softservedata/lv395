package com.softserve.edu.opencart.tests.wishlist_test;

import com.softserve.edu.opencart.tools.LeaveUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.concurrent.TimeUnit;


public class Test1 {

    public ChromeDriver driver;

    @BeforeTest
    /**
     * Initialize WebDriver and
     * Enter to Opencart
     */
    @BeforeClass
    public void beforeClass() {
        //log.info("ShoppingCart test suite start!");
        URL url = this.getClass().getResource("/chromedriver.exe");
        //LeaveUtils.castExceptionByCondition(url == null, DRIVER_ERROR);
        System.setProperty("webdriver.chrome.driver", url.getPath());
        driver = new ChromeDriver();
        //LeaveUtils.castExceptionByCondition(driver == null, DRIVER_ERROR);
        //log.info("ChromeDriver loaded!");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    /**
     * Next method used to login
     * into created user account with
     * correct credentials
     */

    @Test(priority = 1)
    public void login()throws InterruptedException {
        driver.findElement(By.xpath("//a[contains(text(),'My Account')]")).click();
        driver.findElement(By.cssSelector("#input-email")).click();
        driver.findElement(By.cssSelector("#input-email"))
                .sendKeys("maerstek@hotmail.com");
        Thread.sleep(3000);  // only for demonstration
        driver.findElement(By.xpath("//input[@id='input-password']")).click();
        driver.findElement(By.xpath("//input[@id='input-password']"))
                .sendKeys("Lv395_Taqc");
        Thread.sleep(3000);  // only for demonstration
        driver.findElement(By.cssSelector("input[value*='Login']")).click();
        String title = driver.getTitle();
        Assert.assertTrue(title.equals("My Account"));
    }

    /**
     * The next method opening the
     * Wish list page
     */
    @Test(priority = 2)
    public void checkWishList()throws InterruptedException{
        driver.findElement(By.cssSelector("i[class*='fa-heart']")).click();
        String wishTitle = driver.getTitle();
        Assert.assertTrue(wishTitle.equals("My Wish List"));
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='content']/h2"))
                .getText(), "My Wish List");
        Thread.sleep(3000);  // only for demonstration
    }


    /**
     * This method logging out from account
     */
    @Test(priority = 3)
    public void logOut()throws InterruptedException {
        driver.findElement(By.cssSelector("span[class*='caret']")).click();
        Thread.sleep(3000);  // only for demonstration
        driver.findElement(By.xpath("(//a[contains(text(),'Logout')])[1]")).click();
        Thread.sleep(3000);  // only for demonstration
        driver.findElement(By.linkText("Continue")).click();
        Thread.sleep(3000);  // only for demonstration
    }

    /**
     * This method closing WebDriver window
     */
    @AfterTest
    public void close() {
        driver.quit();
    }
}
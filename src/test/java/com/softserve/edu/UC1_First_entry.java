package com.softserve.edu;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class UC1_First_entry {

    public ChromeDriver driver;

    @BeforeTest
    /**
     * Initialize WebDriver and
     * Enter to Opencart
     */
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "./lib/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://192.168.155.134/opencart/upload/");
    }
    /**
     * Next method used to login
     * into created user account with
     * correct credentials
     */

    @Test(priority = 1)
    public void login() {
        driver.findElement(By.xpath("//a[contains(text(),'My Account')]")).click();
        driver.findElement(By.cssSelector("#input-email")).click();
        driver.findElement(By.cssSelector("#input-email"))
                .sendKeys("maerstek@hotmail.com");
        driver.findElement(By.xpath("//input[@id='input-password']")).click();
        driver.findElement(By.xpath("//input[@id='input-password']"))
                .sendKeys("Lv395_Taqc");
        driver.findElement(By.cssSelector("input[value*='Login']")).click();
        String title = driver.getTitle();
        Assert.assertTrue(title.equals("My Account"));
    }

    /**
     * The next method opening the
     * Wish list page
     */
    @Test(priority = 2)
    public void checkWishList(){
        driver.findElement(By.cssSelector("i[class*='fa-heart']")).click();
        String wishTitle = driver.getTitle();
        Assert.assertTrue(wishTitle.equals("My Wish List"));
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='content']/h2"))
                        .getText(), "My Wish List");
    }

    /**
     * This method logging out from account
     */
    @Test(priority = 3)
    public void logOut() {
        driver.findElement(By.cssSelector("span[class*='caret']")).click();
        driver.findElement(By.xpath("(//a[contains(text(),'Logout')])[1]")).click();
        driver.findElement(By.linkText("Continue")).click();
    }

    /**
     * This method closing WebDriver window
     */
    @AfterTest
    public void close() {
        driver.quit();
    }
}
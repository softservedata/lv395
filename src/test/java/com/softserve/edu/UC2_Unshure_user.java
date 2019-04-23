package com.softserve.edu;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class UC2_Unshure_user {

    public String item1;
    public String item2;
    public ChromeDriver driver;

    /**
     * Initialize WebDriver and
     * Enter to Opencart
     */
    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "./lib/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
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
        driver.findElement(By.cssSelector("#input-email")).sendKeys("maerstek@hotmail.com");
        driver.findElement(By.xpath("//input[@id='input-password']")).click();
        driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("Lv395_Taqc");
        driver.findElement(By.cssSelector("input[value*='Login']")).click();
        String title = driver.getTitle();
        Assert.assertTrue(title.equals("My Account"));
    }

    /**
     * This method looking for selected items and add
     * them to Wish list
     */
    @Test(priority = 2)
    public void addItems() throws InterruptedException {
        driver.findElement(By.linkText("Tablets")).click();
        Thread.sleep(1000);
        item1 = driver.findElement(By.cssSelector("p[class*='price']")).getText();
        driver.findElement(By.xpath("//div[contains(@class,'button-group')]/button[2]/i")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//div[contains(@class,'alert')]")).getText(), "Success: You have added Samsung Galaxy Tab 10.1 to your wish list!\n" + "×");
        driver.navigate().refresh();
        driver.findElement(By.linkText("Phones & PDAs")).click();
        item2 = driver.findElement(By.xpath("//div[@id='content']/div[2]/div[2]/div/div[2]/div[1]/p[2]")).getText();
        driver.findElement(By.xpath("//div[@id='content']/div[2]/div[2]/div/div[2]/div[2]/button[2]/i")).click();
        Thread.sleep(500);
        Assert.assertEquals(driver.findElement(By.xpath("//div[contains(@class,'alert')]")).getText(), "Success: You have added iPhone to your wish list!\n" + "×");
    }

    /**
     * This method going to Wish list page
     * and check if selected items present on
     * Wish List page
     */
    @Test(priority = 3)
    public void wishlistCheck() {
        driver.findElement(By.xpath("//a[@id='wishlist-total']/i")).click();
        driver.navigate().refresh();
        Assert.assertEquals(driver.findElement(By.linkText("iPhone")).getText(), "iPhone");
        Assert.assertEquals(driver.findElement(By.linkText("Samsung Galaxy Tab 10.1")).getText(), "Samsung Galaxy Tab 10.1");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='content']/h2")).getText(), "My Wish List");
    }

    /**
     * This method check if selected items have
     * same price on Marketplace and on Wish List
     */
    @Test(priority = 4)
    public void priceCheck() {
        Assert.assertEquals(item1, "$199.99\n" +
                "Ex Tax: $199.99");
        Assert.assertEquals(item2, "$101.00\n" +
                "Ex Tax: $101.00");
    }

    /**
     * This method cleaning our Wish list
     * from selected items
     */
    @Test(priority = 5)
    public void clearList() throws InterruptedException {
        driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/table/tbody/tr[1]/td[6]/a")).click();
        driver.navigate().refresh();
        driver.findElement(By.xpath("(//a[contains(@href,'opencart/upload/index.php')])[62]")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div[id='content'] > p")).getText(), "Your wish list is empty.");
    }

    /**
     * This method logging out from account
     */
    @Test(priority = 5)
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
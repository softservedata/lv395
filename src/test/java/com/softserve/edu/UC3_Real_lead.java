package com.softserve.edu;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class UC3_Real_lead {

    public ChromeDriver driver;
    public String item1;
    public String item2;

    /**
     * Initialize WebDriver and
     * Enter to Opencart
     */
    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "./lib/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
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
    public void addItems()  {
        driver.findElement(By.linkText("Components")).click();
        driver.findElement(By.linkText("Monitors (2)")).click();
        item1 = driver.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[2]/div/div[2]/div[1]/p[2]")).getText();
        driver.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[2]/div/div[2]/div[2]/button[2]")).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        Assert.assertEquals(driver.findElement(By.xpath("//div[contains(@class,'alert')]")).getText(), "Success: You have added Samsung SyncMaster 941BW to your wish list!\n" + "×");
        driver.navigate().refresh();
        driver.findElement(By.linkText("Cameras")).click();
        item2 = driver.findElement(By.xpath("//div[@id='content']/div[2]/div[2]/div/div[2]/div[1]/p[2]")).getText();
        driver.findElement(By.xpath("//div[@id='content']/div[2]/div[2]/div/div[2]/div[2]/button[2]")).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        Assert.assertEquals(driver.findElement(By.xpath("//div[contains(@class,'alert')]")).getText(), "Success: You have added Nikon D300 to your wish list!\n" + "×");
        driver.navigate().refresh();
    }

    /**
     * This method going to Wish list page
     * and check if selected items present on
     * Wish List page
     */
    @Test(priority = 3)
    public void wishlistCheck() {
        driver.findElement(By.xpath("//ul[contains(@class,'list-inline')]/li[3]")).click();
        //driver.navigate().refresh();
        Assert.assertEquals(driver.findElement(By.linkText("Nikon D300")).getText(), "Nikon D300");
        Assert.assertEquals(driver.findElement(By.linkText("Samsung SyncMaster 941BW")).getText(), "Samsung SyncMaster 941BW");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='content']/h2")).getText(), "My Wish List");
    }

    /**
     * This method check if selected items have
     * same price on Marketplace and on Wish List
     */
    @Test(priority = 4)
    public void priceCheck() {
        Assert.assertEquals(item1, "$200.00\n" +
                "Ex Tax: $200.00");
        Assert.assertEquals(item2, "$80.00\n" +
                "Ex Tax: $80.00");
    }

    /**
     * This method adding our items to "Cart"
     * and check confirmation message
     */
    @Test(priority = 5)
    public void addToCart() {
        driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/table[1]/tbody[1]/tr[1]/td[6]/button[1]")).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        Assert.assertEquals(driver.findElement(By.xpath("//div[contains(@class,'alert')]")).getText(), "Success: You have added Nikon D300 to your shopping cart!\n" +
                "×");
        driver.navigate().refresh();
        driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/table[1]/tbody[1]/tr[2]/td[6]/button[1]")).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        Assert.assertEquals(driver.findElement(By.xpath("//div[contains(@class,'alert')]")).getText(), "Success: You have added Samsung SyncMaster 941BW to your shopping cart!\n" +
                "×");
    }

    /**
     * This method going to "Cart" page and check
     * if selected items successfully add to Cart
     * and present on page
     */
    @Test(priority = 6)
    public void checkCart() {
        driver.findElement(By.xpath("//ul[contains(@class,'list-inline')]/li[4]")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//a[contains(text(),'Shopping Cart')]")).getText(), "Shopping Cart");
        Assert.assertEquals(driver.findElement(By.xpath("(//a[contains(text(),'Nikon D300')])[2]")).getText(), "Nikon D300");
        Assert.assertEquals(driver.findElement(By.xpath("(//a[contains(text(),'Samsung SyncMaster 941BW')])[2]")).getText(), "Samsung SyncMaster 941BW");
    }

    /**
     * This method going to "Wish List" and cleaning
     * the list of items and check confirmation message
     */
    @Test(priority = 7)
    public void clearList() {
        driver.findElement(By.xpath("//a[@id='wishlist-total']/i")).click();
        driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/table/tbody/tr[1]/td[6]/a")).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.findElement(By.xpath("(//a[contains(@href,'opencart/upload/index.php')])[62]")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div[id='content'] > p")).getText(), "Your wish list is empty.");
    }

    /**
     * This method logging out from account
     */
    @Test(priority = 8)
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
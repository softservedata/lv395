package com.softserve.edu.OpenCart;
import com.softserve.edu.OpenCart.Helper;
import org.testng.Assert;
import java.lang.*;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import java.util.concurrent.TimeUnit;

public class LoginAndAddProductsTest extends Helper {

    @BeforeClass
    public void beforeClass() {
        /*String webDriverPath =  this.getClass().getResource("/").toString();
        webDriverPath = webDriverPath.substring(webDriverPath.indexOf("/"));
        System.setProperty("webdriver.chrome.driver",
                webDriverPath + "chromedriver-windows-32bit.exe");*/
        System.setProperty("webdriver.chrome.driver", "./lib/chromedriver.exe");
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
            driver.findElement(By.cssSelector("#content > form > div > table > tbody > tr:nth-child(1) >" +
                    " td:nth-child(4) > div > span > button.btn.btn-danger")).click();
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
        addProducts();
        int expectedRowCount = driver.findElements(By.cssSelector("#content > form > div > table > tbody > tr")).size();
        Assert.assertTrue(expectedRowCount > 2);
    }

    // * * * * * * * * * * * * * * * * * * * * * * * * * L O G O U T * * * * * * * * * * * * * * * * * * * * * * * * * *
    @Test(priority = 9)
    public void logOut() {
        driver.findElement(By.cssSelector("#top-links > ul > li.dropdown > a")).click();
        driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();
        driver.findElement(By.cssSelector("i[class='fa fa-shopping-cart']")).click();
        Assert.assertTrue(driver.findElements(By.cssSelector("#content > form")).size() == 0);
    }

}

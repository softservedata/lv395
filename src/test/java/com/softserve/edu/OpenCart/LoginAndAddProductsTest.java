package com.softserve.edu.OpenCart;

import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import java.util.concurrent.TimeUnit;

/**
 * The Test class with three tests:
 * verifyLogIn, addProductsTest, verifyLogOutTest.
 */
public class LoginAndAddProductsTest extends Helper {

    /**
     * BeforeClass: open OpenCart website and set implicitlyWait.
     */
    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "./lib/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get(OPEN_CART_URL);
        driver.manage().window().maximize();
    }

    /**
     * AfterClass: LogIn, delete all products in Shopping Cart,
     * logOut, close browser.
     */
    @AfterClass
    public void afterClass() {
        logIn();
        driver.findElement(By.cssSelector(
                "i[class='fa fa-shopping-cart']")).click();
        while (driver.findElements(By.cssSelector(
                "#content > form")).size() > 0) {
            driver.findElement(By.cssSelector(
                    "#content > form > div > table "
                            + "> tbody > tr:nth-child(1) >"
                            + " td:nth-child(4) > div >"
                            + " span > button.btn.btn-danger")).click();
            driver.navigate().refresh();
        }
        driver.quit();
    }

    /**
     * Verify that Logout is present after logIn function.
     */
    @Test(priority = 1)
    public void verifyLogInTest() {
        logIn();
        Assert.assertTrue(driver.findElement(By.xpath(
                "(//a[contains(text(),'Logout')])[1]")).isEnabled());
    }

    /**
     * Verify on successful adding products,
     * size of table after addProducts.
     */
    @Test (priority = 2)
    public void addProductsTest() {
        addProducts();
        int expectedRowCount = driver.findElements(By.cssSelector(
                "#content > form > div > table > tbody > tr")).size();
        Assert.assertTrue(expectedRowCount > 2);
    }

    /**
     * Verify on successful LogOut.
     * Verify Shopping Cart table is empty
     */
    @Test (priority = 9)
    public void verifyLogOutTest() {
        logOut();
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        Assert.assertTrue(driver.findElements(By.cssSelector(
                "#content > form")).size() == 0);
    }

}

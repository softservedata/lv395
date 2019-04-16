package com.softserve.edu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import javax.persistence.Table;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CartFunctionalityTest extends AddFunctionality {

    @AfterMethod
    public void cleanCart() {
        cartCleaner(driver, getURL());
    }

    @Test
    public void viewCartButtonTest() {
        getAddButtons(0).click();
        driver.navigate().refresh();
        openCart();
        driver.findElement(By.partialLinkText("View Cart")).click();
        System.out.println("Test 1 actual result: " + driver.getCurrentUrl());
        Assert.assertEquals(driver.getCurrentUrl(), "http://" + getURL() + "/opencart/upload/index.php?route=checkout/cart");
        driver.navigate().refresh();
    }

    @Test
    public void checkoutButtonTest() {
        getAddButtons(0).click();
        driver.navigate().refresh();
        openCart();
        driver.findElement(By.partialLinkText("Checkout")).click();
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        new WebDriverWait(driver, 2);
        int checkoutValidElement = driver.findElements(By.id("accordion")).size();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        boolean isElementPresent = checkoutValidElement > 0;
        System.out.println("Test 2 actual result: " + isElementPresent);
        Assert.assertTrue(isElementPresent);
        driver.navigate().refresh();
    }

    @Test
    public void removeOneItemTest() {
        driver.findElement(By.cssSelector("a[href*='product_id=43']")).click();
        WebElement inputField = driver.findElement(By.cssSelector("input[name='quantity']"));
        inputField.clear();
        inputField.sendKeys("5");
        driver.findElement(By.id("button-cart")).click();
        driver.findElement(By.id("logo")).click();
        openCart();
        driver.findElement(By.cssSelector("[title^='Remove'")).click();
        Assert.assertFalse(driver.findElements(By.xpath("//*[@id='cart']/ul/li[1]/table/tbody")).size() == 0);
        driver.navigate().refresh();
    }

    @Test
    public void removeAllItemsTest() {
        getAddButtons(0).click();
        getAddButtons(1).click();
        driver.navigate().refresh();
        openCart();
        List<WebElement> removeButtons = driver.findElements(By.cssSelector("[title^='Remove'"));

        for(WebElement i : removeButtons) {
            i.click();
            removeButtons.remove(i);
        }
        Assert.assertFalse(driver.findElements(By.xpath("//*[@id='cart']/ul/li[1]/table/tbody")).size() == 0);
        driver.navigate().refresh();
    }

    @Test
    public void clickOnItemTest() {
        getAddButtons(0).click();
        driver.navigate().refresh();
        openCart();
        WebElement namePlate = driver.findElement(By.className("text-left"));
        namePlate.findElement(By.tagName("a")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "http://" + getURL() + "/opencart/upload/index.php?route=product/product&product_id=43");
        driver.navigate().refresh();
    }

}

/*
 * OtherFunctionalityTest
 *
 * v. 1.0
 *
 * Copyright (c) 2019 Maksym Burko.
 */
package com.softserve.edu.functionality_test;

import com.softserve.edu.functional.AddFunctionality;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;

/**
 * Test class for different other functions
 * of product cart.
 */
public class OtherFunctionalityTest extends AddFunctionality {

    /**
     * Check or change the inscription on the
     * cart button when adding a product.
     */
    @Test
    public void checkPriceTextButton() {
        addProduct(0).click();
        addProduct(1).click();
        driver.navigate().refresh();
        WebElement priceTextButton = driver.findElement(By.id("cart-total"));
        String actual = priceTextButton.getText();
        Assert.assertTrue(actual.contains("2 item(s) - $725.20"));
    }

    /**
     * Verify that the number of products
     * in the database corresponds to
     * their number in the interface.
     */
    @Test
    public void isItemsInDbTest(){
        int valueInStack;
        driver.get("http://192.168.239.129/opencart/upload/admin/");
        driver.findElement(By.id("input-username")).sendKeys("admin");
        driver.findElement(By.id("input-password")).sendKeys("admin");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        driver.findElement(By.id("button-menu")).click();
        driver.findElement(By.linkText("Catalog")).click();
        driver.findElement(By.xpath("(//a[contains(text(),'Products')])[1]")).click();
        WebElement itemsInStack = driver
                .findElement(By.xpath("//*[@id='form-product']/div/table/tbody/tr[11]/td[6]"));
        valueInStack = Integer.parseInt(itemsInStack.getText());
        driver.get("http://192.168.239.129/opencart/upload/");
        Assert.assertEquals(valueInStack, getProductQuantity(43));
    }

    /**
     * Checking whether the quantity of goods
     * in the cart is changed when logged out.
     */
    @Test
    public void logoutUserCartTest() {
        logIn("qwerty@gmail.com", "qwerty");
        addProduct(0).click();
        driver.navigate().refresh();
        String firstUserCart = driver.findElement(By.id("cart-total")).getText();
        logOut();
        String logoutUserCart = driver.findElement(By.id("cart-total")).getText();
        Assert.assertTrue(logoutUserCart.contains("0 item(s) - $0.00")
                && logoutUserCart != firstUserCart);
    }

    /**
     * Checking whether the amount of goods
     * in the cart changes when a user changes.
     */
    @Test
    public void differentLoggedUsersCartTest() {
        logIn("qwerty@gmail.com", "qwerty");
        addProduct(0).click();
        driver.navigate().refresh();
        String firstUserCart = driver.findElement(By.id("cart-total")).getText();
        System.out.println(firstUserCart);
        logOut();
        logIn("12345@gmail.com", "12345");
        String secondUserCart = driver.findElement(By.id("cart-total")).getText();
        System.out.println(secondUserCart);
        logOut();
        Assert.assertTrue(secondUserCart.contains("0 item(s) - $0.00")
                && secondUserCart != firstUserCart);
    }

    /**
     * Checking whether the quantity of goods
     * in the cart is changed when you relog.
     */
    @Test
    public void relogCartTest() {
        logIn("qwerty@gmail.com", "qwerty");
        addProduct(0).click();
        driver.navigate().refresh();
        String firstLoginCart = driver.findElement(By.id("cart-total")).getText();
        System.out.println(firstLoginCart);
        logOut();
        logIn("qwerty@gmail.com", "qwerty");
        String secondLoginCart = driver.findElement(By.id("cart-total")).getText();
        System.out.println(secondLoginCart);
        logOut();
        Assert.assertEquals(secondLoginCart, firstLoginCart);
    }

    /**
     * Check whether the amount of goods in the
     * cart is saved when you open a new tab of site.
     */
    @Test
    public void openNewTabTest() {
        addProduct(0).click();
        driver.navigate().refresh();
        openCart();
        String cartOnFirstTab = driver.findElement(By.cssSelector("div[id*='cart']"
                + "> ul > li:first-child > table > tbody > tr")).getText();
        String cartOnSecondTab;
        ((JavascriptExecutor)driver).executeScript("window.open()");
        ArrayList<String> allTabs = new ArrayList(driver.getWindowHandles());
        driver.switchTo().window(allTabs.get(1));
        driver.get("http://" + getURL() + "/opencart/upload/");
        openCart();
        cartOnSecondTab = driver.findElement(By.cssSelector("div[id*='cart']"
                + "> ul > li:first-child > table > tbody > tr")).getText();
        Assert.assertEquals(cartOnFirstTab, cartOnSecondTab);
    }

}

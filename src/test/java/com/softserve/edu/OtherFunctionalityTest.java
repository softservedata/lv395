package com.softserve.edu;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;

public class OtherFunctionalityTest extends AddFunctionality{

    @AfterMethod
    public void cleanCart() {
        cartCleaner(driver, getURL());
    }

    @Test
    public void checkPriceTextButton() {
        getAddButtons(0).click();
        getAddButtons(1).click();
        driver.navigate().refresh();
        WebElement priceTextButton = driver.findElement(By.id("cart-total"));
        String actual = priceTextButton.getText();
        Assert.assertTrue(actual.contains("2 item(s) - $725.20"));
        driver.navigate().refresh();
    }

    @Test
    public void isItemsInDbTest(){
        dbConnect();
        int valueInStack;
        driver.get("http://192.168.239.129/opencart/upload/admin/");
        driver.findElement(By.id("input-username")).sendKeys("admin");
        driver.findElement(By.id("input-password")).sendKeys("admin");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        driver.findElement(By.id("button-menu")).click();
        driver.findElement(By.linkText("Catalog")).click();
        driver.findElement(By.xpath("(//a[contains(text(),'Products')])[1]")).click();
        WebElement itemsInStack = driver.findElement(By.xpath("//*[@id='form-product']/div/table/tbody/tr[11]/td[6]"));
        valueInStack = Integer.parseInt(itemsInStack.getText());
        driver.get("http://192.168.239.129/opencart/upload/");
        Assert.assertEquals(valueInStack, getProductQuantity(43));
        driver.navigate().refresh();
    }

    @Test
    public void openNewTabTest() {
        getAddButtons(0).click();
        driver.navigate().refresh();
        openCart();
        String cartOnFirstTab = driver.findElement(By.cssSelector("div[id*='cart'] > ul > li:first-child > table > tbody > tr")).getText();
        String cartOnSecondTab;
        ((JavascriptExecutor)driver).executeScript("window.open()");
        ArrayList<String> allTabs = new ArrayList(driver.getWindowHandles());
        driver.switchTo().window(allTabs.get(1));
        driver.get("http://" + getURL() + "/opencart/upload/");
        openCart();
        cartOnSecondTab = driver.findElement(By.cssSelector("div[id*='cart'] > ul > li:first-child > table > tbody > tr")).getText();
        Assert.assertEquals(cartOnFirstTab, cartOnSecondTab);
        driver.navigate().refresh();
    }

}

package com.softserve.edu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class GoodsListTest extends AddFunctionality {

    @AfterMethod
    public void cleanCart() {
        cartCleaner(driver, getURL());
    }

    @Test
    public void addOneItemTest() {
        //Adding goods to the cart
        getAddButtons(0).click();
        driver.navigate().refresh();
        openCart();
        //WebElements initialization
        String actualGoodPlate = getGoodPlate().getText();
        //Printing results
        System.out.println("Test 1 actual result: " + actualGoodPlate);
        //Asserting results
        Assert.assertTrue(actualGoodPlate.contains("MacBook" + " " + "x 1" + " " + "$602.00"));
        driver.navigate().refresh();
    }

    @Test
    public void increaseQuantityOfItemsTest() {
        List<WebElement> cartElementsTableRows;
        //Adding goods to the cart
        for(int i = 0; i < 3; i++) {
            getAddButtons(0).click();
            driver.navigate().refresh();
            getAddButtons(1).click();
            driver.navigate().refresh();
        }
        openCart();
        //WebElements initialization
        WebElement cartElementsTable = driver.findElement(By.xpath("//*[@id=\"cart\"]/ul/li[1]/table/tbody"));
        cartElementsTableRows = cartElementsTable.findElements(By.tagName("tr"));
        //Printing results
        for (WebElement e: cartElementsTableRows) {
            System.out.println("Test 2 actual result: " + e.getText());
        }
        //Asserting results
        Assert.assertTrue(cartElementsTableRows.get(0).getText().contains("iPhone" + " " + "x 3" + " " + "$369.60"));
        Assert.assertTrue(cartElementsTableRows.get(1).getText().contains("MacBook" + " " + "x 3" + " " + "$1,806.00"));
        driver.navigate().refresh();
    }

    @Test
    public void addSameItemMultipleTimesTest() {
        //Adding goods to the cart
        for (int i = 0; i < 5; i++) {
            getAddButtons(0).click();
            driver.navigate().refresh();
        }
        openCart();
        //WebElements initialization
        String actualGoodPlate = getGoodPlate().getText();
        //Printing results
        System.out.println("Test 3 actual result: " + actualGoodPlate);
        //Asserting results
        Assert.assertTrue(actualGoodPlate.contains("MacBook" + " " + "x 5" + " " + "$3,010.00"));
        driver.navigate().refresh();
    }

    @Test
    public void addItemFromProductPageTest() {
        //Adding goods to the cart
        driver.findElement(By.cssSelector("a[href='http://" + getURL() + "/opencart/upload/index.php?route=product/product&product_id=43']")).click();
        driver.findElement(By.id("button-cart")).click();
        driver.findElement(By.id("logo")).click();
        openCart();
        //WebElements initialization
        String actualGoodPlate = getGoodPlate().getText();
        //Printing results
        System.out.println("Test 4 actual result: " + actualGoodPlate);
        //Asserting results
        Assert.assertTrue(actualGoodPlate.contains("MacBook" + " " + "x 1" + " " + "$602.00"));
        driver.navigate().refresh();
    }

    @Test
    public void addMoreItemsThenInStockTest() {
        int valueInStack;
        driver.get("http://192.168.239.129/opencart/upload/admin/");
        driver.findElement(By.id("input-username")).sendKeys("admin");
        driver.findElement(By.id("input-password")).sendKeys("admin");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        driver.findElement(By.id("button-menu")).click();
        driver.findElement(By.linkText("Catalog")).click();
        driver.findElement(By.xpath("(//a[contains(text(),'Products')])[1]")).click();
        WebElement itemsInStack = driver.findElement(By.xpath("//*[@id='form-product']/div/table/tbody/tr[11]/td[6]"));
        valueInStack = Integer.parseInt(itemsInStack.getText()) + 100;
        driver.get("http://192.168.239.129/opencart/upload/");
        driver.findElement(By.cssSelector("a[href*='product_id=43']")).click();
        WebElement inputField = driver.findElement(By.cssSelector("input[name='quantity']"));
        inputField.clear();
        inputField.sendKeys(Integer.toString(valueInStack));
        driver.findElement(By.id("button-cart")).click();
        driver.findElement(By.id("logo")).click();
        openCart();
        String actualGoodPlate = getGoodPlate().getText();
        System.out.println("Test 5 actual result: " + actualGoodPlate);
        Assert.assertFalse(actualGoodPlate.contains("MacBook" + " " + "x " + valueInStack + " " + "$619,458.00"));
        driver.navigate().refresh();
    }

    @Test
    public void addItemsWithAdditionalParametersTest() {
        //Adding goods to the cart
        getAddButtons(3).click();
        driver.findElement(By.id("input-option226")).click();
        Select cameraColor = new Select(driver.findElement(By.id("input-option226")));
        cameraColor.selectByIndex(1);
        driver.findElement(By.id("button-cart")).click();
        driver.navigate().refresh();
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("logo")));
        driver.findElement(By.id("logo")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        openCart();
        //WebElements initialization
        String actualGoodPlate = getGoodPlate().getText();
        System.out.println("Test 6 actual result: " + actualGoodPlate);
        //Printing results
        //Asserting results
        Assert.assertTrue(actualGoodPlate.contains("Canon EOS 5D\n" +
                "- Select Red x 1 $98.00"));
        driver.navigate().refresh();
    }

}
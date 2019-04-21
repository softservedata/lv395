package com.softserve.edu;

import com.codeborne.selenide.impl.WebElementsCollection;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ShoppingCartPageTest extends LoginAndAddProductsTest {


    /**
     * Some selectors String
     */

    private String editFieldSelector = "#content > form > div > table > tbody > tr:nth-child(1) > td:nth-child(4) > div > input";
    private String unitPriceFieldSelector = "#content > form > div > table > tbody > tr:nth-child(1) > td:nth-child(5)";
    private String totalFieldSelector = "#content > form > div > table > tbody > tr:nth-of-type(1) > td:nth-of-type(6)";
    private WebElement refreshButton;
    private WebElement checkoutButton;
    private String refreshButtonXPath = "//*[@id='content']/form/div/table/tbody/tr[1]/td[4]/div/span/button[1]";
    private String checkoutButtonXPath = "//*[@id='content']/div/div/a[text()='Checkout']";





    @DataProvider
    public Object[][] QuantityTotal() {
        fillQuantityList();
        int currentQuantity = Integer.parseInt(quantityList.get(0));
        //driver.findElement(By.cssSelector("i[class='fa fa-shopping-cart']")).click();
        unitPrice = getFloatNumber(driver.findElement(By.cssSelector(unitPriceFieldSelector)).getText());
        return new Object[][]{
                {currentQuantity, (double) Math.round((currentQuantity * unitPrice)  * 100) / 100 },
                {7, (double) Math.round((7 * unitPrice) * 100) / 100 },
                {2, (double) Math.round((2 * unitPrice) * 100) / 100 },
        };
    }

    @Test(dataProvider = "QuantityTotal", priority = 3)
    public void verifyTotalTest(int newQuantity, double expectedResult) {
        driver.findElement(By.cssSelector("i[class='fa fa-shopping-cart']")).click();
        WebElement quantityField = driver.findElement(By.cssSelector(editFieldSelector));
        quantityField.clear();
        String sNewQuantity = "" + newQuantity;
        quantityField.sendKeys(sNewQuantity);
        webElementInit(refreshButton, refreshButtonXPath).click();
        fillTotalList(driver.findElements(By.xpath(".//form/div/table[@class='table table-bordered']/tbody/tr")).size());
        double expectedSuperTotal = 0.00;
        for (Double e : totalList){
            expectedSuperTotal = (double) Math.round((expectedSuperTotal + e) * 100) / 100;
        }
        totalList.clear();
        double actualSuperTotal = getFloatNumber(driver.findElement(By.xpath("//*[@id='content']/div[2]/div/table/tbody/tr[2]/td[2]")).getText());
        Assert.assertEquals(actualSuperTotal, expectedSuperTotal);
        double actual = getFloatNumber(driver.findElement(By.cssSelector(totalFieldSelector)).getText());
        Assert.assertEquals(actual, expectedResult);
    }



    // Positive Test
    @DataProvider
    public Object[][] accessToCheckout() {
        driver.get(OPEN_CART_URL);
        driver.findElement(By.cssSelector("#content > div.row > div:nth-child(1) > div > div.button-group > button:nth-child(1)")).click();
        fillLists();
        String shoppingCartTitle = driver.getTitle();
        System.out.println("quantityOnStockList.get(0).toString() " + quantityOnStockList.get(0).toString());
        return new Object[][]{
                {quantityList.get(0), shoppingCartTitle },
                {quantityOnStockList.get(0).toString(), shoppingCartTitle },
        };
    }

    @Test (dataProvider = "accessToCheckout", priority = 4)
    public void accessToCheckoutPageTest(String quantityValue, String shoppingCartTitle){
        driver.get("http://192.168.234.131/opencart/upload/index.php?route=checkout/cart");
        WebElement quanityField = driver.findElement(By.cssSelector(editFieldSelector));
        quanityField.clear();
        quanityField.sendKeys(quantityValue);
        webElementInit(refreshButton, refreshButtonXPath).click();
        webElementInit(checkoutButton, checkoutButtonXPath).click();
        String actualTitle = driver.getTitle();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        Assert.assertNotEquals(actualTitle, shoppingCartTitle);
    }

    // Negative Test
    @DataProvider
    public Object[][] notAccessToCheckout() {
        driver.get(OPEN_CART_URL);
        driver.findElement(By.cssSelector("#content > div.row > div:nth-child(1) > div > div.button-group > button:nth-child(1)")).click();
        fillLists();
        String shoppingCartTitle = driver.getTitle();
        System.out.println("quantityOnStockList.get(0).toString() " + quantityOnStockList.get(0).toString());
        return new Object[][]{
                {quantityList.get(0), shoppingCartTitle },
                {quantityOnStockList.get(0).toString(), shoppingCartTitle },
        };
    }

    @Test (dataProvider = "notAccessToCheckout", priority = 4)
    public void notAccessToCheckoutPageTest(String quantityValue, String shoppingCartTitle){
        driver.get("http://192.168.234.131/opencart/upload/index.php?route=checkout/cart");
        WebElement quanityField = driver.findElement(By.cssSelector(editFieldSelector));
        quanityField.clear();
        quanityField.sendKeys(quantityValue);
        webElementInit(refreshButton, refreshButtonXPath).click();
        webElementInit(checkoutButton, checkoutButtonXPath).click();
        String actualTitle = driver.getTitle();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        Assert.assertNotEquals(actualTitle, shoppingCartTitle);
    }


    @DataProvider
    public Object[][] rowDelete() {
        //addProductsTest();
        driver.findElement(By.cssSelector("i[class='fa fa-shopping-cart']")).click();
        int rowInTable = driver.findElements(By.xpath(".//form/div/table[@class='table table-bordered']/tbody/tr")).size();
        String deletePenultimateItemSelector = "//*[@id='content']/form/div/table/tbody/tr["+ (rowInTable-1)+"]/td[4]/div/span/button[2]";
        String productName = driver.findElement(By.xpath("//*[@id='content']/form/div/table/tbody/tr[" + (rowInTable-1) + "]/td[2]/a")).getText();
        return new Object[][]{
                {deletePenultimateItemSelector, rowInTable-1, productName },
        };
    }

   @Test (dataProvider = "rowDelete", priority = 5)
    public void deleteItemTest(String deleteSelector, int expectedRowCount, String expectedNotFindProductName){
        driver.findElement(By.cssSelector("i[class='fa fa-shopping-cart']")).click();
        driver.findElement(By.xpath(deleteSelector)).click();
        driver.navigate().refresh();
        String actual = driver.findElement(By.xpath("//*[@id='content']/form/div/table/tbody/tr[" + (expectedRowCount) + "]/td[2]/a")).getText();
        Assert.assertNotEquals(actual, expectedNotFindProductName);
        int actualRowCount = driver.findElements(By.xpath(".//form/div/table[@class='table table-bordered']/tbody/tr")).size();
        Assert.assertEquals(actualRowCount, expectedRowCount);
    }

    @Test (priority = 6)
    public void buttonContinueShoppingTest(){
        driver.get(OPEN_CART_URL);
        driver.findElement(By.cssSelector("#content > div.row > div:nth-child(1) > div > div.button-group > button:nth-child(1)")).click();
        driver.findElement(By.cssSelector("i[class='fa fa-shopping-cart']")).click();
        driver.findElement(By.linkText("Continue Shopping")).click();
        Assert.assertTrue(driver.findElement(By.id("slideshow0")).isEnabled());
    }

    @Test (priority = 7)
    public void buttonContinueTest(){
        driver.get(OPEN_CART_URL);
        driver.findElement(By.cssSelector("i[class='fa fa-shopping-cart']")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        while (driver.findElements(By.cssSelector("#content > form")).size() > 0){
            driver.findElement(By.cssSelector("#content > form > div > table > tbody > tr:nth-child(1) > td:nth-child(4) > div > span > button.btn.btn-danger")).click();
            driver.navigate().refresh();
        }
        driver.findElement(By.linkText("Continue")).click();
        Assert.assertTrue(driver.findElement(By.id("slideshow0")).isEnabled());
    }







   /* @Test
    public void test() {
        driver.get(OPENCART_ADMIN_URL);
        driver.findElement(By.id("input-username")).sendKeys("admin" + Keys.TAB + "admin" + Keys.ENTER);
        driver.findElement(By.id("button-menu")).click();
        driver.findElement(By.linkText("Catalog")).click();
        driver.findElement(By.xpath("(//a[contains(text(),'Products')])[1]")).click();
    }*/

        //$x(".//tbody/tr/td[2]/a")
}

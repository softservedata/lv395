package com.softserve.edu.OpenCart;

import com.softserve.edu.OpenCart.LoginAndAddProductsTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ShoppingCartPageTest extends LoginAndAddProductsTest {


    /**
     * Some selectors String
     */

    private String editFieldSelector1 = "#content > form > div > table > tbody > tr:nth-child(1) > td:nth-child(4) > div > input";
    private String editFieldSelector2 = "#content > form > div > table > tbody > tr:nth-child(2) > td:nth-child(4) > div > input";
    private String unitPriceFieldSelector = "#content > form > div > table > tbody > tr:nth-child(1) > td:nth-child(5)";
    private String totalFieldSelector = "#content > form > div > table > tbody > tr:nth-of-type(1) > td:nth-of-type(6)";
    private WebElement refreshButton;
    private WebElement checkoutButton;
    private String refreshButtonXPath = "//*[@id='content']/form/div/table/tbody/tr[1]/td[4]/div/span/button[1]";
    private String checkoutButtonXPath = "//*[@id='content']/div/div/a[text()='Checkout']";
    private Boolean isListsFilled = false;





    @DataProvider
    public Object[][] QuantityTotal() {
        fillQuantityList();
        int currentQuantity = Integer.parseInt(quantityList.get(0));
        driver.findElement(By.cssSelector("i[class='fa fa-shopping-cart']")).click();
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
        WebElement quantityField = driver.findElement(By.cssSelector(editFieldSelector1));
        quantityField.clear();
        quantityField.sendKeys(String.valueOf(newQuantity));
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
        isListsFilled = true;
        String h1Text = driver.findElement(By.xpath("//*[@id='content']/h1")).getText().substring(0, 7);
        System.out.println("quantityOnStockList.get(0).toString() " + quantityOnStockList.get(0).toString());
        return new Object[][]{
                {quantityList.get(0), h1Text },
                {quantityOnStockList.get(0).toString(), h1Text },
        };
    }

    @Test (dataProvider = "accessToCheckout", priority = 4)
    public void accessToCheckoutPageTest(String quantityValue, String expected){
        driver.get("http://192.168.234.131/opencart/upload/index.php?route=checkout/cart");
        WebElement quanityField = driver.findElement(By.cssSelector(editFieldSelector1));
        quanityField.clear();
        quanityField.sendKeys(quantityValue);
        webElementInit(refreshButton, refreshButtonXPath).click();
        webElementInit(checkoutButton, checkoutButtonXPath).click();
        String actual = driver.findElement(By.xpath("//*[@id='content']/h1")).getText().substring(0, 7);
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        Assert.assertNotEquals(actual, expected);
    }

    // Negative Test
    @DataProvider
    public Object[][] notAccessToCheckout() {
        driver.get(OPEN_CART_URL);
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        driver.findElement(By.cssSelector("#content > div.row > div:nth-child(1) > div > div.button-group > button:nth-child(1)")).click();
        if (!isListsFilled) {
            fillLists();
        }
        driver.get("http://192.168.234.131/opencart/upload/index.php?route=checkout/cart");
        String h1Text = driver.findElement(By.xpath("//*[@id='content']/h1")).getText().substring(0, 7);
        return new Object[][]{
                //{quantityList.get(0) + 1, shoppingCartTitle },
                { String.valueOf(quantityOnStockList.get(0) +1), editFieldSelector1, h1Text },
                { String.valueOf(quantityOnStockList.get(1) +1), editFieldSelector2, h1Text },
        };
    }

    @Test (dataProvider = "notAccessToCheckout", priority = 5)
    public void notAccessToCheckoutPageTest(String quantityValue, String editFieldSelector, String expected){
        driver.get("http://192.168.234.131/opencart/upload/index.php?route=checkout/cart");
        WebElement webElementEditField = driver.findElement(By.cssSelector(editFieldSelector));
        webElementEditField.clear();
        webElementEditField.sendKeys(quantityValue);
        webElementInit(refreshButton, refreshButtonXPath).click();
        webElementInit(checkoutButton, checkoutButtonXPath).click();
        String actual = driver.findElement(By.xpath("//*[@id='content']/h1")).getText().substring(0, 7);
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        Assert.assertEquals(actual, expected);
    }


    @DataProvider
    public Object[][] rowDelete() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        if (!isListsFilled){
            addProducts();
        }
        driver.findElement(By.cssSelector("i[class='fa fa-shopping-cart']")).click();
        int rowInTable = driver.findElements(By.xpath(".//form/div/table[@class='table table-bordered']/tbody/tr")).size();
        String deletePenultimateItemSelector = "//*[@id='content']/form/div/table/tbody/tr["+ (rowInTable-1)+"]/td[4]/div/span/button[2]";
        String productName = driver.findElement(By.xpath("//*[@id='content']/form/div/table/tbody/tr[" + (rowInTable-1) + "]/td[2]/a")).getText();
        return new Object[][]{
                {deletePenultimateItemSelector, rowInTable-1, productName },
        };
    }

   @Test (dataProvider = "rowDelete", priority = 6)
    public void deleteItemTest(String deleteSelector, int expectedRowCount, String expectedNotFindProductName){
        driver.findElement(By.cssSelector("i[class='fa fa-shopping-cart']")).click();
        driver.findElement(By.xpath(deleteSelector)).click();
        driver.navigate().refresh();
        String actual = driver.findElement(By.xpath("//*[@id='content']/form/div/table/tbody/tr[" + (expectedRowCount) + "]/td[2]/a")).getText();
        Assert.assertNotEquals(actual, expectedNotFindProductName);
        int actualRowCount = driver.findElements(By.xpath(".//form/div/table[@class='table table-bordered']/tbody/tr")).size();
        Assert.assertEquals(actualRowCount, expectedRowCount);
    }

    @Test (priority = 7)
    public void buttonContinueShoppingTest(){
        driver.get(OPEN_CART_URL);
        driver.findElement(By.cssSelector("#content > div.row > div:nth-child(1) > div > div.button-group > button:nth-child(1)")).click();
        driver.findElement(By.cssSelector("i[class='fa fa-shopping-cart']")).click();
        driver.findElement(By.linkText("Continue Shopping")).click();
        Assert.assertTrue(driver.findElement(By.id("slideshow0")).isEnabled());
    }

    @Test (priority = 8)
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

}

package com.softserve.edu;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ShoppingCartPageTest extends LoginAndAddProductsTest {
    private int quanityValue;
    private double total;
    private double unitPrice;
    protected String refreshButton = "div[class*='table-responsive'] > table > tbody > tr:nth-of-type(1) > td:nth-of-type(4) > div > span > button:nth-of-type(1)";
    protected String editField = "#content > form > div > table > tbody > tr:nth-child(1) > td:nth-child(4) > div > input";
    protected String unitPriceField = "#content > form > div > table > tbody > tr:nth-child(1) > td:nth-child(5)";
    protected String totalField = "#content > form > div > table > tbody > tr:nth-of-type(1) > td:nth-of-type(6)";

    public double getFloatNumber(String s) {
        s = s.replaceAll("[^0-9?!\\.]+", "");
        double d = Double.parseDouble(s);
        return (double) Math.round(d * 100) / 100;
    }

   /* @BeforeMethod (dependsOnMethods = "positiveQuanityDataAndRefresh")
    public void beforeRefresh(){
        driver.findElement(By.cssSelector("i[class='fa fa-shopping-cart']")).click();
        WebElement productQuanity = driver.findElement(By.cssSelector(editField));
        int productCount = Integer.parseInt(productQuanity.getText());
        WebElement productTotal = driver.findElement(By.cssSelector(totalField));
        total = getFloatNumber(productTotal.getText());
        unitPrice = getFloatNumber(driver.findElement(By.cssSelector(unitPriceField)).getText());
    }*/

    @DataProvider
    public Object[][] positiveQuanityDataAndRefresh() {
        driver.findElement(By.cssSelector("i[class='fa fa-shopping-cart']")).click();
        unitPrice = getFloatNumber(driver.findElement(By.cssSelector(unitPriceField)).getText());
        return new Object[][]{
                {1, (double) Math.round((getFloatNumber(driver.findElement(By.cssSelector(totalField)).getText()) + unitPrice) * 100) / 100 },
                {7, (double) Math.round((getFloatNumber(driver.findElement(By.cssSelector(totalField)).getText()) + (8 * unitPrice)) * 100) / 100 },
                {-8, (double) Math.round(getFloatNumber(driver.findElement(By.cssSelector(totalField)).getText()) * 100) / 100 },
        };
    }

    //negative testing
    @Test(dataProvider = "positiveQuanityDataAndRefresh", priority = 3)
    public void refreshTest(int plusQuanity, double expectedResult) {
        driver.findElement(By.cssSelector("i[class='fa fa-shopping-cart']")).click();
        WebElement quanityField = driver.findElement(By.cssSelector(editField));
        String newQuanity = String.valueOf(Integer.parseInt(quanityField.getAttribute("value")) + plusQuanity);
        quanityField.clear();
        quanityField.sendKeys(newQuanity);
        driver.findElement(By.cssSelector(refreshButton)).click();
        double actual = getFloatNumber(driver.findElement(By.cssSelector(totalField)).getText());
        Assert.assertEquals(actual, expectedResult);
    }

    @Test
    public void deleteItemTest(int countOfRows, int expectedResult){
        int actual = driver.findElements(By.cssSelector("")).size();
        Assert.assertEquals(actual, expectedResult);
    }

       /* driver.findElement(By.cssSelector("i[class='fa fa-shopping-cart']")).click();
        quanityValue = Integer.parseInt(driver.findElement(By.cssSelector(editField)).getAttribute("value"));
        total = getFloatNumber(driver.findElement(By.cssSelector(totalField)).getText());
        unitPrice = getFloatNumber(driver.findElement(By.cssSelector(unitPriceField)).getText());

        double expectedTotal = (double) Math.round((total + unitPrice + unitPrice) * 100) / 100;
        driver.findElement(By.cssSelector(editField)).clear();
        String newQuanityValue = String.valueOf(quanityValue + inputValue);
        driver.findElement(By.cssSelector(editField)).sendKeys(Keys.BACK_SPACE + newQuanityValue);
        driver.findElement(By.cssSelector("div[class*='table-responsive'] > table > tbody > tr:nth-of-type(1) > td:nth-of-type(4) > div > span > button:nth-of-type(1)")).click();
        double actualTotal = getFloatNumber(driver.findElement(By.cssSelector(totalField)).getText());
        Assert.assertEquals(actualTotal, expectedTotal);*/
        /*
        driver.findElement(By.cssSelector(selector)).click();
        driver.findElement(By.cssSelector(selector)).sendKeys(inputValue);
        driver.findElement(By.id("description")).click();
        driver.findElement(By.id("input-search")).sendKeys(Keys.ENTER);
        WebElement webElement = driver.findElement(By.xpath(selector));
        String actual = webElement.getText();
        Assert.assertEquals(actual, expectedResult);*/
}

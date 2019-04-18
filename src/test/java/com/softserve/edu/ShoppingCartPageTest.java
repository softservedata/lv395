package com.softserve.edu;

import com.codeborne.selenide.impl.WebElementsCollection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartPageTest extends LoginAndAddProductsTest {
    private int quantityValue;
    private double total;
    private double unitPrice;
    protected String refreshButton = "div[class*='table-responsive'] > table > tbody > tr:nth-of-type(1) > td:nth-of-type(4) > div > span > button:nth-of-type(1)";
    protected String editField = "#content > form > div > table > tbody > tr:nth-child(1) > td:nth-child(4) > div > input";
    protected String unitPriceField = "#content > form > div > table > tbody > tr:nth-child(1) > td:nth-child(5)";
    protected String totalField = "#content > form > div > table > tbody > tr:nth-of-type(1) > td:nth-of-type(6)";
    protected List<Double> totalList = new ArrayList<>();
    public List<Double> fillTotalList(int size){
        for (int i=0; i < size; i++) {
            totalList.add(getFloatNumber(driver.findElement(By.xpath("//*[@id='content']/form/div/table/tbody/tr["+ (i+1) +"]/td[6]")).getText()));
        }
        return totalList;
    }


    public double getFloatNumber(String s) {
        s = s.replaceAll("[^0-9?!\\.]+", "");
        double d = Double.parseDouble(s);
        return (double) Math.round(d * 100) / 100;
    }

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
        String newQuantity = String.valueOf(Integer.parseInt(quanityField.getAttribute("value")) + plusQuanity);
        quanityField.clear();
        quanityField.sendKeys(newQuantity);
        driver.findElement(By.cssSelector(refreshButton)).click();
        fillTotalList(driver.findElements(By.xpath(".//form/div/table[@class='table table-bordered']/tbody/tr")).size());
        double expectedSuperTotal = 0.00;
        for (Double e : totalList){
            expectedSuperTotal = expectedSuperTotal + e;
        }
        totalList.removeAll(totalList);
        double actualSuperTotal = getFloatNumber(driver.findElement(By.xpath("//*[@id='content']/div[2]/div/table/tbody/tr[2]/td[2]")).getText());
        Assert.assertEquals(actualSuperTotal, expectedSuperTotal);
        double actual = getFloatNumber(driver.findElement(By.cssSelector(totalField)).getText());
        Assert.assertEquals(actual, expectedResult);
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

   @Test (dataProvider = "rowDelete", priority = 4)
    public void deleteItemTest(String deleteSelector, int expectedRowCount, String expectedNotFindProductName){
        driver.findElement(By.cssSelector("i[class='fa fa-shopping-cart']")).click();
        driver.findElement(By.xpath(deleteSelector)).click();
        driver.navigate().refresh();
        String actual = driver.findElement(By.xpath("//*[@id='content']/form/div/table/tbody/tr[" + (expectedRowCount) + "]/td[2]/a")).getText();
        Assert.assertNotEquals(actual, expectedNotFindProductName);
        int actualRowCount = driver.findElements(By.xpath(".//form/div/table[@class='table table-bordered']/tbody/tr")).size();
        Assert.assertEquals(actualRowCount, expectedRowCount);
    }


        //$x(".//tbody/tr/td[2]/a")
}

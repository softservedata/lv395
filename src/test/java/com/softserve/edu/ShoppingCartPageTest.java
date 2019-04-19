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
    private String tableCountRowXPath = ".//form/div/table[@class='table table-bordered']/tbody/tr";
    private int tableCountRow = 0;
    private int quantityValue;
    private double total;
    private double unitPrice;
    private final String OPENCART_ADMIN_URL = "http://192.168.234.130/opencart/upload/admin/index.php?";
    private String refreshButton = "div[class*='table-responsive'] > table > tbody > tr:nth-of-type(1) > td:nth-of-type(4) > div > span > button:nth-of-type(1)";
    private String editField = "#content > form > div > table > tbody > tr:nth-child(1) > td:nth-child(4) > div > input";
    protected String unitPriceField = "#content > form > div > table > tbody > tr:nth-child(1) > td:nth-child(5)";
    private String totalField = "#content > form > div > table > tbody > tr:nth-of-type(1) > td:nth-of-type(6)";
    private List<Double> totalList = new ArrayList<>();
    private List<String> productNameList = new ArrayList<>();
    private List<String> quantityList = new ArrayList<>();
    private List<Integer> quantityOnStockList = new ArrayList<>();

    public void fillQuantityOnStockList(){
        quantityOnStockList.removeAll(quantityOnStockList);
        driverAdmin = new ChromeDriver();
        driverAdmin.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driverAdmin.get(OPENCART_ADMIN_URL);
        driverAdmin.findElement(By.id("input-username")).sendKeys("admin" + Keys.TAB + "admin" + Keys.ENTER);
        driverAdmin.findElement(By.id("button-menu")).click();
        driverAdmin.findElement(By.linkText("Catalog")).click();
        driverAdmin.findElement(By.xpath("(//a[contains(text(),'Products')])[1]")).click();
        for (int i=0; i<tableCountRow; i++){
            quantityOnStockList.add(Integer.parseInt(driverAdmin.findElement(By.xpath
                    ("//tbody/tr/td[text()='" + productNameList.get(i)+"']/following-sibling::td[*]/span[@class='label label-success']")).getText()));
            System.out.println("QuantityOnStockList: " + quantityOnStockList.get(i));
        }
        driverAdmin.quit();
        //return quantityOnStockList;
    }

    public List<Double> fillTotalList(int size){
        for (int i=0; i < size; i++) {
            totalList.add(getFloatNumber(driver.findElement(By.xpath("//*[@id='content']/form/div/table/tbody/tr["+ (i+1) +"]/td[6]")).getText()));
        }
        return totalList;
    }

    public void fillProductNameList(){
        productNameList.removeAll(productNameList);
        driver.findElement(By.cssSelector("i[class='fa fa-shopping-cart']")).click();
        tableCountRow = driver.findElements(By.xpath(tableCountRowXPath)).size();
        for (int i=0; i<tableCountRow; i++) {
            productNameList.add(driver.findElement(By.xpath("//*[@id='content']/form/div/table/tbody/tr[" + (i+1) +"]/td[2]/a")).getText());
            System.out.println("productNameList: " + productNameList.get(i));
        }
    }

    public void fillQuantityList(){
        quantityList.removeAll(quantityList);
        driver.findElement(By.cssSelector("i[class='fa fa-shopping-cart']")).click();
        tableCountRow = driver.findElements(By.xpath(tableCountRowXPath)).size();
        for (int i=0; i<tableCountRow; i++) {
            quantityList.add(driver.findElement(By.xpath("//*[@id='content']/form/div/table/tbody/tr[" + (i+1) +"]/td[4]/div/input")).getAttribute("value"));
            System.out.println("quantityList: " + quantityList.get(i));
        }
    }

    public double getFloatNumber(String s) {
        s = s.replaceAll("[^0-9?!\\.]+", "");
        double d = Double.parseDouble(s);
        return (double) Math.round(d * 100) / 100;
    }

    public int getQuantityOnStock(int i){
        return quantityOnStockList.get(i);
    }

   /* @Test
    public void test() {
        driver.get(OPENCART_ADMIN_URL);
        driver.findElement(By.id("input-username")).sendKeys("admin" + Keys.TAB + "admin" + Keys.ENTER);
        driver.findElement(By.id("button-menu")).click();
        driver.findElement(By.linkText("Catalog")).click();
        driver.findElement(By.xpath("(//a[contains(text(),'Products')])[1]")).click();
    }*/


    @DataProvider
    public Object[][] positiveQuantityDataAndRefresh() {
        driver.findElement(By.cssSelector("i[class='fa fa-shopping-cart']")).click();
        unitPrice = getFloatNumber(driver.findElement(By.cssSelector(unitPriceField)).getText());
        return new Object[][]{
                {1, (double) Math.round(unitPrice * 100) / 100 },
                {7, (double) Math.round((7 * unitPrice) * 100) / 100 },
                {2, (double) Math.round((2 * unitPrice) * 100) / 100 },
        };
    }

    //negative testing
    @Test(dataProvider = "positiveQuantityDataAndRefresh", priority = 3)
    public void refreshTest(int newQuantity, double expectedResult) {
        driver.findElement(By.cssSelector("i[class='fa fa-shopping-cart']")).click();
        WebElement quantityField = driver.findElement(By.cssSelector(editField));
        quantityField.clear();
        String sNewQuantity = "" + newQuantity;
        quantityField.sendKeys(sNewQuantity);
        driver.findElement(By.cssSelector(refreshButton)).click();
        fillTotalList(driver.findElements(By.xpath(".//form/div/table[@class='table table-bordered']/tbody/tr")).size());
        double expectedSuperTotal = 0.00;
        for (Double e : totalList){
            expectedSuperTotal = (double) Math.round((expectedSuperTotal + e) * 100) / 100;
        }
        totalList.removeAll(totalList);
        fillQuantityList();
        fillProductNameList();
        fillQuantityOnStockList();
        double actualSuperTotal = getFloatNumber(driver.findElement(By.xpath("//*[@id='content']/div[2]/div/table/tbody/tr[2]/td[2]")).getText());
        Assert.assertEquals(actualSuperTotal, expectedSuperTotal);
        double actual = getFloatNumber(driver.findElement(By.cssSelector(totalField)).getText());
        Assert.assertEquals(actual, expectedResult);
    }
    /*
    @Test
    public void accessToCheckoutPage(){

    }*/

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

  /*  @Test
    public void buttonContinueShoppingTest(){
        driver.get(OPEN_CART_URL);
        driver.findElement(By.cssSelector("#content > div.row > div:nth-child(1) > div > div.button-group > button:nth-child(1)")).click();
        driver.findElement(By.cssSelector("i[class='fa fa-shopping-cart']")).click();
        driver.findElement(By.linkText("Continue Shopping")).click();
        Assert.assertTrue(driver.findElement(By.id("slideshow0")).isEnabled());
    }

    @Test
    public void buttonContinueTest(){
        driver.get(OPEN_CART_URL);
        driver.findElement(By.cssSelector("i[class='fa fa-shopping-cart']")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        while (driver.findElements(By.cssSelector("#content > form")).size() > 0){
            driver.findElement(By.cssSelector("#content > form > div > table > tbody > tr:nth-child(1) > td:nth-child(4) > div > span > button.btn.btn-danger")).click();
            driver.navigate().refresh();
        }
        driver.findElement(By.linkText("Continue")).click();
        Assert.assertTrue(driver.findElement(By.id("slideshow0")).isEnabled());(
    }*/




        //$x(".//tbody/tr/td[2]/a")
}

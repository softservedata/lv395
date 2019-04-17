package com.softserve.edu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.Assert;
import org.testng.annotations.*;

public class OrderInfoTest extends AddFunctionality{

    private static double expectedEcoTax = 4.0;
    private static double expectedVat = (601.00 * 20)/100;
    private static double expectedTotal = 725.20;
    private static double expectedSubTotal = (expectedTotal - expectedVat) - expectedEcoTax;

    @AfterMethod
    public void cleanCart() {
        cartCleaner(driver, getURL());
    }

    @DataProvider(name = "CheckCartTableBoxes")
    public static Object[][] cartTableBoxesData() {
        return new Object[][]{
                {"//*[@id=\"cart\"]/ul/li[2]/div/table/tbody/tr[2]/td[2]", expectedEcoTax },
                {"//*[@id=\"cart\"]/ul/li[2]/div/table/tbody/tr[3]/td[2]", expectedVat},
                {"//*[@id=\"cart\"]/ul/li[2]/div/table/tbody/tr[1]/td[2]", expectedSubTotal},
                {"//*[@id=\"cart\"]/ul/li[2]/div/table/tbody/tr[4]/td[2]", expectedTotal}
        };
    }

    @Test(dataProvider = "CheckCartTableBoxes")
    public void checkCartTableBoxes(String xpath, double expectedValue) {
        getAddButtons(0).click();
        driver.navigate().refresh();
        getAddButtons(1).click();
        driver.navigate().refresh();
        openCart();
        WebElement dataInBox = driver.findElement(By.xpath(xpath));
        String tmpValue = dataInBox.getText().replaceAll("[^0-9.]+", "");
        double actualValue = Double.parseDouble(tmpValue);
        System.out.println("Test actual result: " + actualValue);
        Assert.assertEquals(actualValue, expectedValue);
        driver.navigate().refresh();
    }

}

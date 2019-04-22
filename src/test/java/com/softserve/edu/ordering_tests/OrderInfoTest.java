/*
 * OrderInfoTest
 *
 * v. 1.0
 *
 * Copyright (c) 2019 Maksym Burko.
 */
package com.softserve.edu.ordering_tests;

import com.softserve.edu.functional.AddFunctionality;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

/**
 * Class for check values in order info
 * table in product cart.
 */
public class OrderInfoTest extends AddFunctionality {

    private static double expectedEcoTax = 4.0;
    private static double expectedVat = (601.00 * 20)/100;
    private static double expectedTotal = 725.20;
    private static double expectedSubTotal =
            (expectedTotal - expectedVat) - expectedEcoTax;


    /**
     * Data provider for order info table tests.
     *
     * @return locator to actual value and expected value.
     */
    @DataProvider(name = "CheckCartTableBoxes")
    public static Object[][] cartTableBoxesData() {
        return new Object[][]{
                {"//*[@id=\"cart\"]/ul/li[2]/div/table/tbody/tr[2]/td[2]", expectedEcoTax },
                {"//*[@id=\"cart\"]/ul/li[2]/div/table/tbody/tr[3]/td[2]", expectedVat},
                {"//*[@id=\"cart\"]/ul/li[2]/div/table/tbody/tr[1]/td[2]", expectedSubTotal},
                {"//*[@id=\"cart\"]/ul/li[2]/div/table/tbody/tr[4]/td[2]", expectedTotal}
        };
    }

    /**
     * Method for check values in order info
     * table in product cart.
     *
     * @param xpath - locator to actual value.
     * @param expectedValue - expected value.
     */
    @Test(dataProvider = "CheckCartTableBoxes")
    public void checkCartTableBoxes(String xpath, double expectedValue) {
        addProduct(0).click();
        driver.navigate().refresh();
        addProduct(1).click();
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

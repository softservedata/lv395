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

    /**
     * Eco Tax should equal 2$ per item.
     */
    private static double expectedEcoTax = 4.0;

    /**
     * Eco Tax should equal (20% from Sub Total) – Eco Tax.
     */
    private static double expectedVat = ((601.00 * 20)/100);

    /**
     * Sub-Total should equal Total – (Eco Tax + VAT)
     */
    private static double expectedTotal = 725.20;

    /**
     * Total should be equal to the sum
     * of the value of all goods in the cart
     */
    private static double expectedSubTotal =
            ((expectedTotal - expectedVat) - expectedEcoTax);


    /**
     * Data provider for order info table tests.
     *
     * @return locator to actual value and expected value.
     */
    @DataProvider(name = "CheckCartTableBoxes")
    public static Object[][] cartTableBoxesData() {
        String actualEcoTax = "//*[@id='cart']/ul/li[2]/div/table/tbody/tr[2]/td[2]";
        String actualVat = "//*[@id='cart']/ul/li[2]/div/table/tbody/tr[3]/td[2]";
        String actualSubTotal = "//*[@id='cart']/ul/li[2]/div/table/tbody/tr[1]/td[2]";
        String actualTotal = "//*[@id='cart']/ul/li[2]/div/table/tbody/tr[4]/td[2]";
        return new Object[][]{
                {actualEcoTax, expectedEcoTax },
                {actualVat, expectedVat},
                {actualSubTotal, expectedSubTotal},
                {actualTotal, expectedTotal}
        };
    }

    /**
     * Method for check values in order info
     * table in product cart.
     *
     * @param xpath locator to actual value.
     * @param expectedValue expected value.
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
    }

}

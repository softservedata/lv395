package com.softserve.edu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

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

}

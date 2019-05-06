package com.softserve.edu.opencart.pages.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutPage extends AStatusPart {

    public static final String CHECKOUT_LABEL_TEXT = "Checkout";
    //
    WebElement checkoutLabel;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        initElements();
    }

    public void initElements() {
        checkoutLabel = driver.findElement(By.cssSelector(".col-sm-12 > h1"));
    }

    // Page Object

    public WebElement getCheckoutLabel(){
        return checkoutLabel;
    }

    public String getCheckoutLabelText(){
        return getCheckoutLabel().getText();
    }

    // Functional

    // Business Logic

}

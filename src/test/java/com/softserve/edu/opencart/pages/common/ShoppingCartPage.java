package com.softserve.edu.opencart.pages.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ShoppingCartPage extends AStatusPart {

    public static final String SHOPPING_CART_LABEL_TEXT = "Shopping Cart";
    //
    WebElement shoppingCartLabel;

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
        initElements();
    }

    public void initElements() {
        shoppingCartLabel = driver.findElement(By.cssSelector(".col-sm-12 > h1"));
    }

    // Page Object

    public WebElement getShoppingCartLabel(){
        return shoppingCartLabel;
    }

    public String getShoppingCartLabelText(){
        return getShoppingCartLabel().getText();
    }

    // Functional

    // Business Logic

}

package com.softserve.edu.opencart.pages.shop;

import com.softserve.edu.opencart.pages.common.AHeaderPart;
import com.softserve.edu.opencart.pages.common.AStatusPart;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AShoppingCartPage extends AStatusPart {

    public final String EMPTY_CART_MESSAGE = "Your shopping cart is empty!";
    //
    private WebElement shoppingCartContainer;

    protected AShoppingCartPage(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements() {
        shoppingCartContainer = driver.findElement(By.id("content"));
    }

    // Page Object

    public WebElement getShoppingCartContainer() {
        return shoppingCartContainer;
    }

    public String getCartContainerText() {
        return getShoppingCartContainer().getText();
    }

    // Functional

    // Business Logic

    public boolean isShoppingCartEmpty() {
        return getCartContainerText().equals(EMPTY_CART_MESSAGE);
    }

}

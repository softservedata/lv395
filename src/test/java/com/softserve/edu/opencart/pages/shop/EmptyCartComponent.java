package com.softserve.edu.opencart.pages.shop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EmptyCartComponent extends ACartComponent {

    public final String EMPTY_CART_MESSAGE = "Your shopping cart is empty!";

    private WebElement emptyCartMessage;

    public EmptyCartComponent(WebDriver driver) {
        super(driver);
        initElements();
    }

    public void initElements() {
        emptyCartMessage = driver.findElement(By.xpath("//div[@id='cart']//p"));
    }

    // Page Object

    public WebElement getEmptyCartMessage() {
        return emptyCartMessage;
    }

    public String getEmptyCartMessageText() {
        return getEmptyCartMessage().getText();
    }

    // Functional

    public void closeCart() {
        clickCartButton();
    }

    // Business Logic

    public boolean isCartEmpty() {
        return getCartContainerText().equals(EMPTY_CART_MESSAGE);
    }

}

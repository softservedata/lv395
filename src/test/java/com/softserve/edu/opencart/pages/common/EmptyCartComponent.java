package com.softserve.edu.opencart.pages.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EmptyCartComponent extends AHeaderPart {

    public static final String EMPTY_CART_MESSAGE = "Your shopping cart is empty!";
    //
    private WebElement emptyCart;

    protected EmptyCartComponent(WebDriver driver) {
        super(driver);
        initElements();
    }

    public void initElements() {
        emptyCart = driver.findElement(By.xpath("//div[@id='cart']//p"));
    }

    public WebElement getEmptyCart() {
        return emptyCart;
    }

    public String getEmptyCartText() {
        return getEmptyCart().getText();
    }

}

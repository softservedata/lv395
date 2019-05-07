package com.softserve.edu.opencart.pages.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EmptyCartComponent extends ACartComponent {

    private WebElement emptyCart;

    protected EmptyCartComponent(WebDriver driver) {
        super(driver);
        initElements();
    }

    public void initElements() {
        emptyCart = driver.findElement(By.xpath("//div[@id='cart']//p"));
    }

    // Page Object

    public WebElement getEmptyCartMessage() {
        return emptyCart;
    }

    public String getEmptyCartMessageText() {
        return getEmptyCartMessage().getText();
    }

    // Functional

    public void closeCart() {
        clickCartButton();
    }

}

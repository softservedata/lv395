package com.softserve.edu.opencart.pages.shop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EmptyCartComponent extends ACartComponent {

    private WebElement emptyCartMessage;

    public EmptyCartComponent(WebDriver driver) {
        super(driver);
        initElements();
    }

    public void initElements() {
        emptyCartMessage = driver.findElement(By.cssSelector("#cart li > p"));
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

}

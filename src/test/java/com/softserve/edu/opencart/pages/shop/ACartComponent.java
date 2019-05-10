package com.softserve.edu.opencart.pages.shop;

import com.softserve.edu.opencart.pages.common.AHeaderPart;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class ACartComponent extends AHeaderPart {

    //
    private WebElement cartContainer;

    protected ACartComponent(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements() {
        cartContainer = driver.findElement(By.cssSelector(".dropdown-menu.pull-right"));
    }

    // Page Object

    public WebElement getCartContainer() {
        return cartContainer;
    }

    public String getCartContainerText() {
        return getCartContainer().getText();
    }

    // Functional

    // Business Logic

}

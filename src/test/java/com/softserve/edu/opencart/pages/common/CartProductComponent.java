package com.softserve.edu.opencart.pages.common;

import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;

public class CartProductComponent {
    WebElement cartProductLayout;

    protected CartProductComponent(WebElement cartProductLayout) {
        this.cartProductLayout = cartProductLayout;
    }

    // cartProductImg
    public WebElement getCartProductImg() {
        return cartProductLayout.findElement(By.cssSelector(".dropdown-menu.pull-right"));
    }

    // cartProductName
    public WebElement getCartProductName() {
        return cartProductLayout.findElement(By.cssSelector("td:nth-child(2)>a"));
    }

    public String getCartProductNameText() {
        return getCartProductName().getText();
    }

    public void clickCartProductName() {
        getCartProductName().click();
    }

    // cartProductQuantity
    public WebElement getCartProductQuantity() {
        return cartProductLayout.findElement(By.cssSelector("td:nth-child(3)"));
    }

    public String getCartProductQuantityText() {
        return getCartProductQuantity().getText();
    }

    // cartProductPrice
    public WebElement getCartProductPrice() {
        return cartProductLayout.findElement(By.cssSelector("td:nth-child(4)"));
    }

    public String getCartProductPriceText() {
        return getCartProductPrice().getText();
    }

    // removeButton
    public WebElement getRemoveButton() {
        return cartProductLayout.findElement(By.xpath(".//td[@class='text-center']/button"));
    }

    public void clickRemoveButton() {
        getRemoveButton().click();
    }

    // viewCartButton
    public WebElement getViewCartButton() {
        return cartProductLayout.findElement(By.xpath("//a[1]/strong"));
    }

    public void clickViewCartButton() {
        getViewCartButton().click();
    }

    // checkoutButton
    public WebElement getCheckoutButton() {
        return cartProductLayout.findElement(By.xpath(".//p[@class='text-right']/a[2]"));
    }

    public void clickCheckoutButton() {
        getCheckoutButton().click();
    }

    // Functional

    // Business Logic

}

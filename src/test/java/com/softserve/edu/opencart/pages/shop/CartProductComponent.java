package com.softserve.edu.opencart.pages.shop;

import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;

public class CartProductComponent {

    WebElement cartProductLayout;
    WebElement cartProductImg;
    WebElement cartProductName;
    WebElement cartProductQuantity;
    WebElement cartProductPrice;
    WebElement removeButton;
    WebElement checkoutButton;

    protected CartProductComponent(WebElement cartProductLayout) {
        this.cartProductLayout = cartProductLayout;
        initElements();
    }

    private void initElements() {
        cartProductImg = cartProductLayout.findElement(By.cssSelector(".img-thumbnail"));
        cartProductName = cartProductLayout.findElement(By.cssSelector("td:nth-child(2)>a"));
        cartProductQuantity = cartProductLayout.findElement(By.cssSelector("td:nth-child(3)"));
        cartProductPrice = cartProductLayout.findElement(By.cssSelector("td:nth-child(4)"));
        removeButton = cartProductLayout.findElement(By.xpath(".//td[@class='text-center']/button"));
        checkoutButton = cartProductLayout.findElement(By.xpath("//a[2]/strong"));
    }

    // cartProductImg

    public WebElement getCartProductImg() {
        return cartProductImg;
    }

    public void clickCartProductImg() {
        getCartProductImg().click();
    }

    // cartProductName
    public WebElement getCartProductName() {
        return cartProductName;
    }

    public String getCartProductNameText() {
        return getCartProductName().getText();
    }

    public void clickCartProductName() {
        getCartProductName().click();
    }

    // cartProductQuantity
    public WebElement getCartProductQuantity() {
        return cartProductQuantity;
    }

    public String getCartProductQuantityText() {
        return getCartProductQuantity().getText();
    }

    // cartProductPrice
    public WebElement getCartProductPrice() {
        return cartProductPrice;
    }

    public String getCartProductPriceText() {
        return getCartProductPrice().getText();
    }

    // removeButton
    public WebElement getRemoveButton() {
        return removeButton;
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
        return checkoutButton;
    }

    public void clickCheckoutButton() {
        getCheckoutButton().click();
    }

    // Functional

    // Business Logic

}

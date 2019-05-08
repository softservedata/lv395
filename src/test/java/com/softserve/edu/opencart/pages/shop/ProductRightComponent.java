package com.softserve.edu.opencart.pages.shop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductRightComponent {

    WebElement productRightLayout;
    //
    WebElement wishListButton;
    WebElement compareButton;
    WebElement nameLabel;
    WebElement priceLabel;
    WebElement quantityField;
    WebElement addToCartButton;

    WebDriver driver;

    public ProductRightComponent(WebDriver driver) {
        this.driver = driver;
        initElements();
    }

    public void initElements() {
        wishListButton = driver.findElement(By.cssSelector("button[onclick*='wishlist']"));
        compareButton = driver.findElement(By.cssSelector("button[onclick*='compare']"));
        nameLabel = driver.findElement(By.cssSelector("h1"));
        priceLabel = driver.findElement(By.cssSelector(".list-unstyled h2"));
        quantityField = driver.findElement(By.id("input-quantity"));
        addToCartButton = driver.findElement(By.id("button-cart"));
    }

    // Page Object

    // wishListButton

    public WebElement getWishListButton() {
        return wishListButton;
    }

    public void clickWishListButton() {
        getWishListButton().click();
    }

    // compareButton

    public WebElement getCompareButton() {
        return compareButton;
    }

    public void clickCompareButton() {
        getCompareButton().click();
    }

    // nameLabel

    public WebElement getNameLabel() {
        return nameLabel;
    }

    public String getNameLabelText() {
        return getNameLabel().getText();
    }

    // priceLabel

    public WebElement getPriceLabel() {
        return priceLabel;
    }

    public String getPriceLabelText(){
        return getPriceLabel().getText();
    }

    // quantityField

    public WebElement getQuantityField() {
        return quantityField;
    }

    public String getQuantityFieldText() {
        return getQuantityField().getText();
    }

    public void clearQuantityField() {
        getQuantityField().clear();
    }

    public void clickQuantityField() {
        getQuantityField().click();
    }

    public void setQuantityField(int quantity) {
        getQuantityField().sendKeys(Integer.toString(quantity));
    }

    // addToCartButton

    public WebElement getAddToCartButton() {
        return addToCartButton;
    }

    public String getAddToCartButtonText() {
       return getAddToCartButton().getText();
    }

    public void clickAddToCartButton() {
        getAddToCartButton().click();;
    }

    // Functional

    // Business Logic

}

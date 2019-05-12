package com.softserve.edu.opencart.pages.account;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.softserve.edu.opencart.tools.StringHandler;

public class WishListProductComponent {

    private WebElement tr;
    private WebElement productNameWishList;
    private WebElement unitPriceWishList;
    private WebElement addToCartFromWishList;
    private WebElement removeFromWishListButton;

    public WishListProductComponent(WebElement tr) {
        this.tr = tr;
        initWishListProductComponents();
    }

    private void initWishListProductComponents() {
        productNameWishList = tr.findElement(By.cssSelector(".text-left a"));
        unitPriceWishList = tr.findElement(By.cssSelector(".price"));
        addToCartFromWishList = tr.findElement(By.cssSelector(".text-right .btn.btn-primary"));
        removeFromWishListButton = tr.findElement(By.cssSelector(".text-right a.btn.btn-danger"));
    }

    public WebElement gettr() {
        return tr;
    }

    public WebElement getproductNameWishList() {
        return productNameWishList;
    }

    public String getproductNameWishListText() {
        return getproductNameWishList().getText();
    }

    public void clickproductNameWishList() {
        getproductNameWishList().click();
    }

    public WebElement getunitPriceWishList() {
        return unitPriceWishList;
    }

    public String getunitPriceWishListText() {
        return getunitPriceWishList().getText();
    }

    public double getunitPriceWishListAmount() {
        return StringHandler.extractFirstDouble(getunitPriceWishListText());
    }

    public WebElement getaddToCartFromWishList() {
        return addToCartFromWishList;
    }

    public void clickaddToCartFromWishList() {
        getaddToCartFromWishList().click();
    }

    public WebElement getremoveFromWishListButton() {
        return removeFromWishListButton;
    }

    public void clickremoveFromWishListButton() {
        System.out.println("removeProductFromWishListByPartialName+++++++++++++++wishListMessagePage.getAlertMessageText()");
        getremoveFromWishListButton().click();
    }
}


package com.softserve.edu.opencart.pages.account;

import com.softserve.edu.opencart.pages.account.ARightLoginPart;
import com.softserve.edu.opencart.pages.common.WishListComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class WishListPage extends ARightLoginPart {
//define elements
    private WishListComponent wishListComponent;
    public final String EXPECTED_MESSAGE_WISH_REMOVE = "Success: You have modified your wish list!";

    private WishListProductsListComponent wishListProductListComponent;
    private WebElement wishListButtonContinue;

    public WishListPage(WebDriver driver) {
        super(driver);
        initWishListPage();
    }

    private void initWishListPage() {
        wishListButtonContinue = driver.findElement(By.cssSelector(".pull-right > a"));
        wishListProductListComponent = new  WishListProductsListComponent(driver);
    }

    public WebElement getWishListButtonContinue() {
        return wishListButtonContinue;
    }
}

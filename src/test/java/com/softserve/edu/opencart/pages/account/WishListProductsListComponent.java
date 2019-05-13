package com.softserve.edu.opencart.pages.account;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WishListProductsListComponent {

    private WebDriver driver;
    private List<WishListProductComponent> wishListProductComponents;

    public WishListProductsListComponent(WebDriver driver) {

        this.driver = driver;
        initWishListProductsListComponents();
    }

    private void initWishListProductsListComponents() {
        wishListProductComponents = new ArrayList<>();
        for (WebElement current : driver.findElements(By.cssSelector(".table-responsive tbody tr"))) {
            wishListProductComponents.add(new WishListProductComponent(current));
        }
    }

    public List<WishListProductComponent> getWishListProductComponents() {
        return wishListProductComponents;
    }

    public WishListProductComponent getWishListProductComponentByPartialName(String partialProductName) {
        WishListProductComponent result = null;
        for (WishListProductComponent current : getWishListProductComponents()) {
            if (current.getproductNameWishListText().toLowerCase()
                    .contains(partialProductName.toLowerCase())) {
                result = current;
                break;
            }
        }
        return result;
    }

    public List<String> getWishListProductsNameList() {
        List<String> result = new ArrayList<>();
        for (WishListProductComponent current : getWishListProductComponents()) {
            result.add(current.getproductNameWishListText());
        }
        return result;
    }

    public void addToCartProductFromWishListByPartialName(String partialProductName) {
        getWishListProductComponentByPartialName(partialProductName)
                .clickaddToCartFromWishList();
    }

    public void removeProductFromWishListByPartialName(String partialProductName) {
        System.out.println("removeProductFromWishListByPartialName+++++++++++++++wishListMessagePage.getAlertMessageText()");
        getWishListProductComponentByPartialName(partialProductName)
                .clickremoveFromWishListButton();
    }
}
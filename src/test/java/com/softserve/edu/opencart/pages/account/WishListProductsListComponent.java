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
    }

}
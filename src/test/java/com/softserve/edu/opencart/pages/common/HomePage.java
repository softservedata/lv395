package com.softserve.edu.opencart.pages.common;

import com.softserve.edu.opencart.data.IProduct;
import com.softserve.edu.opencart.pages.shop.ProductPage;
import com.softserve.edu.opencart.pages.shop.ProductsContainerComponent;
import com.softserve.edu.opencart.pages.shop.ProductsListComponent;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.softserve.edu.opencart.data.Currencies;

public class HomePage extends AHeaderPart {

    public static final String IPHONE_IMAGE = "iPhone6-1140x380.jpg";
    public static final String EXPECTED_MESSAGE_CART = "Success: You have added %s to your shopping cart!";
    public final String EXPECTED_MESSAGE_WISH_UNLOGGED = "You must login or create an account to save %s to your wish list!";
    public final String EXPECTED_MESSAGE_WISH = "Success: You have added %s to your wish list!";

    private WebElement alertMessage;
    //
    private WebElement slideshow0;
    //
    private ProductsContainerComponent productsContainerComponent;
    private ProductsListComponent productsListComponent;

    public HomePage(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements() {
        slideshow0 = driver.findElement(By.id("slideshow0"));
        productsContainerComponent = new ProductsContainerComponent(driver);
    }
    private void initAlertMessage() {
        alertMessage = driver.findElement(By.cssSelector(".alert.alert-success"));
    }
    // Page Object

    // slideshow0
    public WebElement getSlideshow0FirstImage() {
        // return Slideshow0.findElement(By.cssSelector("a > img"));
        return slideshow0.findElement(By.xpath(".//a/img"));
        // return Slideshow0.findElement(By.xpath("//a/img")); // ERROR
        // return driver.findElement(By.xpath("//div[@id='slideshow0']//a/img"));
    }
    public ProductsListComponent getProductsListComponent() {
        return productsListComponent;
    }

    public String getSlideshow0FirstImageAttributeText(String attribute)
    {
        return getSlideshow0FirstImage().getAttribute(attribute).trim();
    }

    public String getSlideshow0FirstImageAttributeSrcText()
    {
        return getSlideshow0FirstImageAttributeText(TAG_ATTRIBUTE_SRC);
    }

    // productsContainerComponent
    public ProductsContainerComponent getProductsContainerComponent() {
        return productsContainerComponent;
    }

    // alertMessage
    public WebElement getAlertMessage() {
        return alertMessage;
    }

    public WebElement getAlertMessageCloseButton() {
        return getAlertMessage().findElement(By.cssSelector(".close"));
    }

    public String getAlertMessageText() {
        String textMessage = getAlertMessage().getText();
        // Remove x Symbol from Message
        return textMessage.substring(0, textMessage.length() - 2);
    }

    // Functional

    public HomePage putToWishProductByPartialName(String partialProductName) {
        getProductsListComponent()
                .addToWishProductByPartialName(partialProductName);
        return new HomePage(driver);
    }
    @Step("Add product to the cart")
    public HomePage addProductToCart(IProduct product) {
        getProductsContainerComponent()
                .clickProductComponentAddToCartButtonByName(product.getName());
        return new HomePage(driver);
    }

    @Step("Click on product name")
    public ProductPage clickProductName(IProduct product) {
        getProductsContainerComponent()
                .clickProductComponentName(product.getName());
        return new ProductPage(driver);
    }

    // Business Logic

    public HomePage chooseCurrency(Currencies currency) {
        clickCurrencyByPartialName(currency);
        return new HomePage(driver);
    }
    public HomePage closeAlertMessage() {
        getAlertMessageCloseButton().click();
        return new HomePage(driver);
    }
}
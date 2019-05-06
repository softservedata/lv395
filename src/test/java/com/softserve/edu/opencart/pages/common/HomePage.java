package com.softserve.edu.opencart.pages.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.softserve.edu.opencart.data.Currencies;

public class HomePage extends AHeaderPart {

    public static final String IPHONE_IMAGE = "iPhone6-1140x380.jpg";
    //
    private WebElement slideshow0;
    //
    private ProductsContainerComponent productsContainerComponent;

    public HomePage(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements() {
        slideshow0 = driver.findElement(By.id("slideshow0"));
        productsContainerComponent = new ProductsContainerComponent(driver);
    }

    // Page Object

    // slideshow0
    public WebElement getSlideshow0FirstImage() {
        // return Slideshow0.findElement(By.cssSelector("a > img"));
        return slideshow0.findElement(By.xpath(".//a/img"));
        // return Slideshow0.findElement(By.xpath("//a/img")); // ERROR
        // return driver.findElement(By.xpath("//div[@id='slideshow0']//a/img"));
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

    // Functional

    public CartProductContainer openCartProductContainer() {
        clickCartButton();
        return new CartProductContainer(driver);
    }

    // Business Logic

    public HomePage chooseCurrency(Currencies currency) {
        clickCurrencyByPartialName(currency);
        return new HomePage(driver); 
    }

    public HomePage addProductToCart(Product product) {
        getProductsContainerComponent()
                .clickProductComponentAddToCartButtonByName(product.getName());
        return new HomePage(driver);
    }

}

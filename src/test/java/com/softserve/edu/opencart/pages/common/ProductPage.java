package com.softserve.edu.opencart.pages.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage extends AStatusPart {

    //
    private ProductComponent product;

    public ProductPage(WebDriver driver, ProductComponent productComponent) {
        super(driver);
        initElement(productComponent);
    }

    private void initElement(ProductComponent productComponent) {
        product = productComponent.setPartialDescriptionToFull(driver.findElement(By.id("tab-description")));

    }

    public ProductComponent getProduct() {
        return product;
    }

    public SuccessfulSearchPage gotoSearchPage() {
        driver.findElement(By.xpath(".//ul[@class='breadcrumb']/li[2]")).click();
        return new SuccessfulSearchPage(driver);

    }

}

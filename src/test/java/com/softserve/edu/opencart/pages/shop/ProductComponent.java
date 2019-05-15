package com.softserve.edu.opencart.pages.shop;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductComponent {

    private WebElement productLayout;
    private List<ProductComponent> productComponents;
    //
    private WebElement name;
    private WebElement partialDescription;
    private WebElement price;
    private WebElement quantityField;
    private WebElement addToCartButton;
    private WebElement addToWishButton;
    private WebElement addToCompareButton;
    private WebElement updateButton;
    private WebElement removeButton;

    public ProductComponent(WebElement productLayout) {
        this.productLayout = productLayout;
        initElements();
    }

    private void initElements() {
        name = productLayout.findElement(By.cssSelector("h4 a"));
        partialDescription = productLayout.findElement(By.cssSelector("h4 + p"));
        price = productLayout.findElement(By.cssSelector(".price"));
        updateButton = productLayout.findElement(By.xpath("//a[contains(text(),'My Account')]"));
        removeButton = productLayout.findElement(By.cssSelector(".fa.fa-times-circle"));
        quantityField = productLayout.findElement(By.cssSelector(".input-group.btn-block input"));
        addToCartButton = productLayout.findElement(By.cssSelector("button[onclick*='cart.add']"));
        addToWishButton = productLayout.findElement(By.cssSelector(".fa.fa-heart"));
        addToCompareButton = productLayout.findElement(By.cssSelector(".fa.fa-exchange"));
    }


    //ProductCartComponent
    public List<ProductComponent> getProductCartComponents() {
        return productComponents;
    }
    public WebElement getProductLayout() {
        return productLayout;
    }

    public ProductComponent getProductCartComponentByPartialName(String partialProductCartName) {
        ProductComponent result = null;
        for (ProductComponent current : getProductCartComponents()) {
            if (current.getNameText().toLowerCase()
                    .contains(partialProductCartName.toLowerCase())) {
                result = current;
                break;
            }
        }
        return result;
    }
    // name
    public WebElement getName() {
        return name;
    }

    public String getNameText() {
        return getName().getText();
    }

    public void clickName() {
        getName().click();
    }

    // partialDescription
    public WebElement getPartialDescription() {
        return partialDescription;
    }

    public String getPartialDescriptionText() {
        return getPartialDescription().getText();
    }

    // price
    public WebElement getPrice() {
        return price;
    }

    public String getPriceText() {
        return getPrice().getText();
    }

    // QuantityField
    public WebElement getQuantityField() {return quantityField;}
    public void setQuantityField(String text) {getQuantityField().sendKeys(text);}
    public void clickQuantityField() {getQuantityField().click();}
    public void clearQuantityField() {getQuantityField().clear();}

    // addToCartButton
    public WebElement getAddToCartButton() {
        return addToCartButton;
    }

    public String getAddToCartButtonText() {
        return getAddToCartButton().getText();
    }

    public void clickAddToCartButton() {
        getAddToCartButton().click();
    }

    //UpdateButton
    public WebElement getUpdateButton() {return updateButton;}
    public void clickUpdateButton() {getUpdateButton().click();}

    //RemoveButton
    public WebElement getRemoveButton() {return removeButton;}
    public void clickRemoveButton() {getRemoveButton().click();}
    // addToWishButton
    public WebElement getAddToWishButton() {
        return addToWishButton;
    }

    public String getAddToWishButtonText() {
        return getAddToWishButton().getText();
    }

    public void clickAddToWishButton() {
        getAddToWishButton().click();
    }

    // addToCompareButton
    public WebElement getAddToCompareButton() {
        return addToCompareButton;
    }

    public String getAddToCompareButtonText() {
        return getAddToCompareButton().getText();
    }

    public void clickAddToCompareButton() {
        getAddToCompareButton().click();
    }

    // Functional

    // Business Logic
//-------------------changes-----------------
    public ProductComponent setPartialDescriptionToFull(WebElement newDescription) {
        this.partialDescription = newDescription;
        return this;
    }
}

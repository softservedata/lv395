package com.softserve.edu.opencart.pages.shop;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ShoppingCartProductComponent {

    // some constant selectors

    private final String COLUMN_NAME_CSSSELECTOR = "td:nth-child(2)>a";
    private final String COLUMN_MODEL_CSSSELECTOR = "td:nth-child(3)";
    private final String COLUMN_QUANTITY_FIELD_CSSSELECTOR = "input";
    private final String COLUMN_QUANTITY_UPDATE_CSSSELECTOR = "button[data-original-title='Update']";
    private final String COLUMN_QUANTITY_REMOVE_CSSSELECTOR = "button[data-original-title='Remove']";
    private final String COLUMN_UNITPRICE_CSSSELECTOR = "td:nth-child(5)";
    private final String COLUMN_TOTAL_CSSSELECTOR = "td:nth-child(6)";


    WebElement product;

    protected ShoppingCartProductComponent(WebElement product){
        this.product = product;
    }

    //product
    public WebElement getProduct(){return product;}

    //productName
    public WebElement getProductName(){
        return product.findElement(By.cssSelector(COLUMN_NAME_CSSSELECTOR));
    }

    public String getProductNameText(){
        return getProductName().getText();
    }

    public void clickProductName(){
        getProductName().click();
    }

    //productModel
    public WebElement getProductModel(){
        return product.findElement(By.cssSelector(COLUMN_MODEL_CSSSELECTOR));
    }

    public String getProductModelText(){
        return getProductModel().getText();
    }

    //quantity
    public WebElement getQuantityField(){
        return product.findElement(By.cssSelector(COLUMN_QUANTITY_FIELD_CSSSELECTOR));
    }

    public void clearQuantityField(){
        getQuantityField().clear();
    }

    public void clickQuantityField(){
        getQuantityField().click();
    }

    public WebElement getUpdateQuantityButton(){
        return product.findElement(By.cssSelector(COLUMN_QUANTITY_UPDATE_CSSSELECTOR));
    }

    public void clickUpdateQuantityButton(){
        getUpdateQuantityButton().click();
    }

    public WebElement getRemoveFromShoppingCartButton(){
        return product.findElement(By.cssSelector(COLUMN_QUANTITY_REMOVE_CSSSELECTOR));
    }

    public void clickRemoveFromShoppingCartButton(){
        getRemoveFromShoppingCartButton().click();
    }

    //unitPrice
    public WebElement getUnitPrice(){
        return product.findElement(By.cssSelector(COLUMN_UNITPRICE_CSSSELECTOR));
    }

    public String getUnitPriceText(){
        return getUnitPrice().getText();
    }

    //totalPrice
    public WebElement getTotalPrice(){
        return product.findElement(By.cssSelector(COLUMN_TOTAL_CSSSELECTOR));
    }

    public String getTotalPriceText(){
        return getTotalPrice().getText();
    }

    // Functional

    public void setQuantity(String quantity){
        clickQuantityField();
        clearQuantityField();
        getQuantityField().sendKeys(quantity);
        clickUpdateQuantityButton();
    }

    // Business Logic

}

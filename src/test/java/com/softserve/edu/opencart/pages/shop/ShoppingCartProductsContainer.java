package com.softserve.edu.opencart.pages.shop;

import com.softserve.edu.opencart.data.IProduct;
import com.softserve.edu.opencart.tools.PriceUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartProductsContainer {
    private static final String PRODUCT_COMPONENT_CSSSELECTOR = ".table-responsive>.table.table-bordered>tbody>tr";

    protected WebDriver driver;
    //
    private List<ShoppingCartProductComponent> productComponents;

    public ShoppingCartProductsContainer(WebDriver driver) {
        this.driver = driver;
        initElements();
    }

    private void initElements() {
        productComponents = new ArrayList<>();
        for (WebElement current : driver.findElements(By.cssSelector(PRODUCT_COMPONENT_CSSSELECTOR))) {
            productComponents.add(new ShoppingCartProductComponent(current));
        }
    }

    // Page Object

    // productComponents
    public List<ShoppingCartProductComponent> getShoppingCartComponents() {
        initElements();
        return productComponents;
    }

    // Functional

    // productComponents
    public ShoppingCartProductComponent getShoppingCartComponentByName(String productName){
        ShoppingCartProductComponent result = null;
        for (ShoppingCartProductComponent current : getShoppingCartComponents()) {
            if (current.getProductNameText().toLowerCase()
                    .equals(productName.toLowerCase())) {
                result = current;
            }
        }
        if (result == null) {
            // TODO Develop Custom Exception
            throw new RuntimeException("ProductName: " + productName + " not Found.");
        }
        return result;
    }

    public String getNameByProduct(IProduct product) {
    	return getShoppingCartComponentByName(product.getName()).getProductNameText();
    }
    
    public BigDecimal getUnitPriceByName(IProduct product){
        return PriceUtils.getPrice(getShoppingCartComponentByName(product.getName()).getUnitPriceText());
    }

    public BigDecimal getTotalPriceByName(IProduct product){
        return PriceUtils.getPrice(getShoppingCartComponentByName(product.getName()).getTotalPriceText());
    }

    public String getCurrencyByProduct(IProduct product){
        return PriceUtils.getCurrencySymbol(getShoppingCartComponentByName(product.getName()).getTotalPriceText());
    }

    public String getModelByName(IProduct product){
        return getShoppingCartComponentByName(product.getName()).getProductModelText();
    }

    public void quantityProductsByName(IProduct product, String quantity){
        getShoppingCartComponentByName(product.getName()).setQuantity(quantity);
    }

    public ShoppingCartPage removeProductFromShoppingCartByName(IProduct product){
        getShoppingCartComponentByName(product.getName()).clickRemoveFromShoppingCartButton();
        return new ShoppingCartPage(driver);
    }

    public void removeProductByName(IProduct product){
        getShoppingCartComponentByName(product.getName()).clickRemoveFromShoppingCartButton();
    }


}

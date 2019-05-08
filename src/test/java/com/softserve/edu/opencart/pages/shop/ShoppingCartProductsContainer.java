package com.softserve.edu.opencart.pages.shop;

import com.softserve.edu.opencart.data.Product;
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

    public String getNameByProduct(Product product) {
    	return getShoppingCartComponentByName(product.getName()).getProductNameText();
    }
    
    public BigDecimal getUnitPriceByName(Product product){
        return PriceUtils.getPrice(getShoppingCartComponentByName(product.getName()).getUnitPriceText());
    }

    public BigDecimal getTotalPriceByName(Product product){
        return PriceUtils.getPrice(getShoppingCartComponentByName(product.getName()).getTotalPriceText());
    }

    public String getCurrencyByProduct(Product product){
        return PriceUtils.getCurrencySymbol(getShoppingCartComponentByName(product.getName()).getTotalPriceText());
    }

    public String getModelByName(Product product){
        return getShoppingCartComponentByName(product.getName()).getProductModelText();
    }

    public void quantityProductsByName(Product product, String quantity){
        getShoppingCartComponentByName(product.getName()).setQuantity(quantity);
    }

    public void removeProductFromShoppingCartByName(Product product){
        getShoppingCartComponentByName(product.getName()).clickRemoveFromShoppingCartButton();
    }


}

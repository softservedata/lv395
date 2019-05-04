package com.softserve.edu.opencart.pages.common;

import com.softserve.edu.opencart.data.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CartProductContainer {

    private static final String PRODUCT_COMPONENT_CSSSELECTOR = (".table.table-striped>tbody>tr");
    private static final String PRICE_TABLE_CSSSELECTOR = (".dropdown-menu.pull-right .table.table-bordered");
    //
    WebDriver driver;
    //
    private List<CartProductComponent> productComponents;

    public CartProductContainer(WebDriver driver) {
        this.driver = driver;
        initElements();
    }

    private void initElements() {
        productComponents = new ArrayList<>();
        for (WebElement current : driver.findElements(By.cssSelector(PRODUCT_COMPONENT_CSSSELECTOR))) {
            productComponents.add(new CartProductComponent(current));
        }
    }

    // Page Object

    // productComponents
    public List<CartProductComponent> getCartProductComponents() {
        return productComponents;
    }

    // Functional

    // cartProductComponents
    public CartProductComponent getCartProductComponentByName(String productName) {
        CartProductComponent result = null;
        for (CartProductComponent current : getCartProductComponents()) {
            if (current.getCartProductNameText().toLowerCase().equals(productName.toLowerCase())) {
                result = current;
            }
        }
        if (result == null) {
            throw new RuntimeException("ProductName: " + productName + " not Found.");
        }
        return result;
    }

    // Business Logic

}

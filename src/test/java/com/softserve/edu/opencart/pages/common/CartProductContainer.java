package com.softserve.edu.opencart.pages.common;

import com.softserve.edu.opencart.data.Product;
import com.softserve.edu.opencart.tools.LeaveUtils;
import com.softserve.edu.opencart.tools.PriceUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CartProductContainer {

    private String PRODUCT_NOT_FOUND_ERROR_MSG = "Product not Found.";
    //
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
        LeaveUtils.castExceptionByCondition(result == null, PRODUCT_NOT_FOUND_ERROR_MSG);
        return result;
    }

    public TotalPriceTableComponent getTotalPriceTableComponent(){
        return new TotalPriceTableComponent(driver.findElement(By.cssSelector(PRICE_TABLE_CSSSELECTOR)));
    }

    public BigDecimal getCartProductPriceByName(String productName) {
        return PriceUtils.getPrice(getCartProductComponentByName(productName).getCartProductPriceText());
    }

    private void removeProductFromCartByName(String productName) {
        getCartProductComponentByName(productName).clickRemoveButton();
    }

    public HomePage removeProductByName(Product product) {
        removeProductFromCartByName(product.getName());
        return new HomePage(driver);
    }

    // Business Logic

    /*public HomePage removeAllProductsFromCart() {
        List<CartProductComponent> productsInCart = getCartProductComponents();
        HomePage homePage = new HomePage(driver);
        if(productsInCart.size() > 0) {
            for (CartProductComponent product : productsInCart) {
                removeProductFromCartByName(product.getCartProductNameText());
                homePage
                        .gotoHomePage()
                        .openCartProductContainer();
            }
        }
        return homePage;
    }*/

}

package com.softserve.edu.opencart.pages.shop;

import com.softserve.edu.opencart.data.IProduct;
import com.softserve.edu.opencart.pages.common.CheckoutPage;
import com.softserve.edu.opencart.pages.common.HomePage;
import com.softserve.edu.opencart.tools.LeaveUtils;
import com.softserve.edu.opencart.tools.PriceUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CartProductContainer extends ACartComponent {

    private final String PRODUCT_NOT_FOUND_ERROR_MSG = "Product name: %s not Found.";
    //
    private static final String PRODUCT_COMPONENT_CSSSELECTOR = (".table.table-striped>tbody>tr");
    private static final String PRICE_TABLE_CSSSELECTOR = (".dropdown-menu.pull-right .table.table-bordered");
    //
    private List<CartProductComponent> productComponents;

    public CartProductContainer(WebDriver driver) {
        super(driver);
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
    @Step("Get all products from cart")
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
        LeaveUtils.castExceptionByCondition(result == null,
                String.format(PRODUCT_NOT_FOUND_ERROR_MSG, productName));
        return result;
    }

    @Step("Get data from product cart table")
    public TotalPriceTableComponent getTotalPriceTableComponent(){
        return new TotalPriceTableComponent(driver.findElement(By.cssSelector(PRICE_TABLE_CSSSELECTOR)));
    }

    public BigDecimal getCartProductPriceByName(String productName) {
        return PriceUtils.getPrice(getCartProductComponentByName(productName).getCartProductPriceText());
    }

    private void removeProductFromCartByName(String productName) {
        getCartProductComponentByName(productName).clickRemoveButton();
    }

    @Step("Remove product from cart")
    public HomePage removeProductByName(IProduct product) {
        removeProductFromCartByName(product.getName());
        return new HomePage(driver);
    }

    // Business Logic

    @Step("Click 'View Cart' button")
    public ShoppingCartPage gotoShoppingCartPage() {
        productComponents.get(0).clickViewCartButton();
        return new ShoppingCartPage(driver);
    }

    @Step("Click 'Checkout' button")
    public CheckoutPage gotoCheckoutPage() {
        productComponents.get(0).clickCheckoutButton();
        return new CheckoutPage(driver);
    }

    @Step("Click on product name in cart")
    public ProductPage gotoProductPage(IProduct product) {
        productComponents.get(0).clickCartProductName();
        return new ProductPage(driver);
    }

}

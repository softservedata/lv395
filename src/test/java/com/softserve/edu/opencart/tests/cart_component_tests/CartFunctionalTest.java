package com.softserve.edu.opencart.tests.cart_component_tests;

import com.softserve.edu.opencart.data.ProductRepository;
import com.softserve.edu.opencart.pages.common.CheckoutPage;
import com.softserve.edu.opencart.pages.common.ShoppingCartPage;
import com.softserve.edu.opencart.tests.ATestRunner;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartFunctionalTest extends ATestRunner {

    @Test
    public void checkViewCartButton() {
        // Steps
        ShoppingCartPage shoppingCartPage = loadApplication()
                .addProductToCart(ProductRepository.getMacBook())
                .gotoHomePage()
                .openCartProductContainer()
                .gotoShoppingCartPage();
        // Check
        Assert.assertTrue(shoppingCartPage.getShoppingCartLabelText()
        .contains(shoppingCartPage.SHOPPING_CART_LABEL_TEXT));
        // Steps
        shoppingCartPage
                .gotoHomePage()
                .openCartProductContainer()
                .removeProductByName(ProductRepository.getMacBook());
    }

    @Test
    public void checkCheckoutButton() {
        // Steps
        CheckoutPage checkoutPage = loadApplication()
                .addProductToCart(ProductRepository.getMacBook())
                .gotoHomePage()
                .openCartProductContainer()
                .gotoCheckoutPage();
        // Check
        Assert.assertTrue(checkoutPage.getCheckoutLabelText()
                .contains(checkoutPage.CHECKOUT_LABEL_TEXT));
        // Steps
        checkoutPage
                .gotoHomePage()
                .openCartProductContainer()
                .removeProductByName(ProductRepository.getMacBook());
    }

}

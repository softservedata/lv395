package com.softserve.edu.opencart.tests;

import com.softserve.edu.opencart.data.ProductRepository;
import com.softserve.edu.opencart.pages.common.ShoppingCartPage;
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

}

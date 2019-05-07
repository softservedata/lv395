package com.softserve.edu.opencart.tests.shopping_cart_tests;

import com.softserve.edu.opencart.tests.ATestRunner;
import org.testng.annotations.Test;

@Test
public class ShoppingCartRemoveItemTest extends ATestRunner {
    public void checkRemoveButton() throws InterruptedException {
        // Steps
        /*ShoppingCartProductsContainer shoppingCartProductsContainer = loadApplication()
                .addProductToCart(ProductRepository.getMacBook())
                .addProductToCart(ProductRepository.getIPhone3())
                .addProductToCart(ProductRepository.getIPhone3())
                .gotoShoppingCartPage()
                .getShoppingCartProductsContainer();
        Thread.sleep(2000);
        shoppingCartProductsContainer.removeProductFromShoppingCartByName(ProductRepository.getMacBook());

        // Check
        Assert.assertFalse(shoppingCartProductsContainer.getShoppingCartComponents()
                .contains(ProductRepository.getMacBook()));
        Thread.sleep(5000);
        // Steps
        */
    }
}

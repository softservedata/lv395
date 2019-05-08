package com.softserve.edu.opencart.tests.cart_component_tests;

import com.softserve.edu.opencart.data.IProduct;
import com.softserve.edu.opencart.data.ProductRepository;
import com.softserve.edu.opencart.pages.common.*;
import com.softserve.edu.opencart.tests.ATestRunner;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class CartFunctionalTest extends ATestRunner {

    @DataProvider
    public Object[][] productData() {
        return new Object[][] {
                {ProductRepository.getMacBook()}
        };
    }

    @Test(dataProvider = "productData")
    public void checkViewCartButton(IProduct product) {
        // Steps
        ShoppingCartPage shoppingCartPage = loadApplication()
                .addProductToCart(product)
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
                .removeProductByName(product);
    }

    @Test(dataProvider = "productData")
    public void checkCheckoutButton(IProduct product) {
        // Steps
        CheckoutPage checkoutPage = loadApplication()
                .addProductToCart(product)
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
                .removeProductByName(product);
    }

    @Test(dataProvider = "productData")
    public void checkRemoveButton(IProduct product) {
        // Steps
        CartProductContainer cartProductContainer = loadApplication()
                .addProductToCart(product)
                .gotoHomePage()
                .openCartProductContainer();
        List<CartProductComponent> cartProductComponents = cartProductContainer
                .getCartProductComponents();
        // Check
        Assert.assertEquals(cartProductComponents.get(0).getCartProductNameText(),
                product.getName());
        // Steps
        cartProductContainer
                .removeProductByName(product)
                .gotoHomePage()
                .openCartProductContainer();
        // Check
       Assert.assertTrue(cartProductContainer.isCartEmpty());
    }

/*    @Test(dataProvider = "productData")
    public void checkClickOnItem(IProduct product) {
        // Steps
        CartProductContainer cartProductContainer = loadApplication()
                .addProductToCart(product)
                .gotoHomePage()
                .openCartProductContainer();
        List<CartProductComponent> cartProductComponents = cartProductContainer
                .getCartProductComponents();
        // Check
        Assert.assertEquals(cartProductComponents.get(0).getCartProductNameText(),
                product.getName());
        // Steps
        // TODO
    }*/

}

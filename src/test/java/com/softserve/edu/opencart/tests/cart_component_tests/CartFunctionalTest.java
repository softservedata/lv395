package com.softserve.edu.opencart.tests.cart_component_tests;

import com.softserve.edu.opencart.data.IProduct;
import com.softserve.edu.opencart.data.Product;
import com.softserve.edu.opencart.data.ProductRepository;
import com.softserve.edu.opencart.pages.common.CartProductComponent;
import com.softserve.edu.opencart.pages.common.CartProductContainer;
import com.softserve.edu.opencart.pages.common.CheckoutPage;
import com.softserve.edu.opencart.pages.common.ShoppingCartPage;
import com.softserve.edu.opencart.tests.ATestRunner;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

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

    @DataProvider
    public Object[][] productData() {
        return new Object[][] {
                {ProductRepository.getMacBook()}
        };
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

}

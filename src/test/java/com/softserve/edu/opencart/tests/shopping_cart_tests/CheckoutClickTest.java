package com.softserve.edu.opencart.tests.shopping_cart_tests;

import com.softserve.edu.opencart.data.IProduct;
import com.softserve.edu.opencart.data.ProductRepository;
import com.softserve.edu.opencart.pages.common.CheckoutPage;
import com.softserve.edu.opencart.pages.common.HomePage;
import com.softserve.edu.opencart.tests.shopping_cart_tests.ATestRunner;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CheckoutClickTest extends ATestRunner {

    @DataProvider
    public Object[][] productData() {
        return new Object[][] {
                {ProductRepository.getMacBook()}
        };
    }

    @Test(dataProvider = "productData")
    public void positiveCheckGotoCheckoutPage(IProduct product) {
        // Steps
        CheckoutPage checkoutPage = loadApplication()
                .addProductToCart(product)
                .gotoHomePage()
                .gotoShoppingCartPage()
                .gotoCheckoutPageByCheckoutButton();
        Assert.assertTrue(checkoutPage.getCheckoutLabelText()
                .contains(checkoutPage.CHECKOUT_LABEL_TEXT));
    }
    // TODO negative test
    @Test(dataProvider = "productData")
    public void negativeCheckGotoCheckoutPage(IProduct product) {
        // Steps
        CheckoutPage checkoutPage = loadApplication()
                .addProductToCart(product)
                .gotoHomePage()
                .gotoShoppingCartPage()
                .gotoCheckoutPageByCheckoutButton();
        Assert.assertFalse(checkoutPage.getCheckoutLabelText()
                .contains(checkoutPage.CHECKOUT_LABEL_TEXT));
    }


}

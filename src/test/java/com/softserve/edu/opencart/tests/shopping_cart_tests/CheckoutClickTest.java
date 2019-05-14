package com.softserve.edu.opencart.tests.shopping_cart_tests;

import com.softserve.edu.opencart.data.IProduct;
import com.softserve.edu.opencart.data.ProductRepository;
import com.softserve.edu.opencart.pages.common.*;
import com.softserve.edu.opencart.pages.shop.*;
import com.softserve.edu.opencart.tools.DataBaseUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.softserve.edu.opencart.pages.common.CheckoutPage.CHECKOUT_LABEL_TEXT;


public class CheckoutClickTest extends ATestRunner {

    @DataProvider
    public Object[][] productData() {
        return new Object[][] {
                {ProductRepository.getMacBook()}
        };
    }

    @Test(dataProvider = "productData", priority = 1)
    public void positiveCheckGotoCheckoutPage(IProduct product) {
        // Steps
        CheckoutPage checkoutPage = loadApplication()
                .addProductToCart(product)
                .gotoHomePage()
                .gotoShoppingCartPage()
                .gotoCheckoutPageByCheckoutButton();
        Assert.assertTrue(checkoutPage.getCheckoutLabelText()
                .contains(CHECKOUT_LABEL_TEXT));
    }


    @Test(dataProvider = "productData", priority = 2)
    public void negativeCheckGotoCheckoutPage(IProduct product) {
        // Steps
        DataBaseUtils dbUtils = new DataBaseUtils();
        int maxQuantity = dbUtils.getProductQuantityFromDb(product);
        ShoppingCartPage shoppingCartPage = loadApplication()
                .clickProductName(product)
                .setQuantity(maxQuantity)
                .addProductToCart()
                .gotoHomePage()
                .gotoShoppingCartPage();
        Assert.assertTrue(shoppingCartPage
                .getErrorQuantityLabel()
                .contains(shoppingCartPage.SHOPPING_CART_ERROR_QUANTITY_TEXT));
    }

    @Test(dataProvider = "productData", priority = 3)
    public void checkGotoCheckoutPage(IProduct product) {
        DataBaseUtils dbUtils = new DataBaseUtils();
        int maxQuantity = dbUtils.getProductQuantityFromDb(product);
        CheckoutPage checkoutPage = loadApplication()
                .clickProductName(product)
                .setQuantity(maxQuantity)
                .addProductToCart()
                .gotoShoppingCartPage()
                .setQuantityProductsByName(product, String.valueOf(maxQuantity-5))
                .gotoCheckoutPageByCheckoutButton();
        Assert.assertTrue(checkoutPage.getCheckoutLabelText()
                .contains(CHECKOUT_LABEL_TEXT));
    }

}
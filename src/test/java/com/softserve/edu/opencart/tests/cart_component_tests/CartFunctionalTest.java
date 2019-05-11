package com.softserve.edu.opencart.tests.cart_component_tests;

import com.softserve.edu.opencart.data.IProduct;
import com.softserve.edu.opencart.data.ProductRepository;
import com.softserve.edu.opencart.pages.common.*;
import com.softserve.edu.opencart.pages.shop.*;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

@Epic("Functional Testing")
@Feature("CartFunctionalTest")
public class CartFunctionalTest extends ATestRunner {

    @DataProvider
    public Object[][] productData() {
        return new Object[][] {
                {ProductRepository.getMacBook()}
        };
    }

    @Description("Test Description: This test check does the 'View Cart' button works")
    @Severity(SeverityLevel.NORMAL)
    @Story("Click 'View Cart' and check did we go to the cart page")
    @Test(dataProvider = "productData", description = "Check 'View cart' button test")
    public void checkViewCartButton(IProduct product) {
        log.debug("checkViewCartButton test started");
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
        log.debug("checkViewCartButton test finished");
    }

    @Description("Test Description: This test check does the 'Checkout' button works")
    @Severity(SeverityLevel.NORMAL)
    @Story("Click 'Checkout' and check did we go to the checkout page")
    @Test(dataProvider = "productData", description = "Check 'Checkout' button test")
    public void checkCheckoutButton(IProduct product) {
        log.debug("checkCheckoutButton test started");
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
        log.debug("checkCheckoutButton test finished");
    }


    @Description("Test Description: This test check does the 'Remove' button works")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Click 'Checkout' and check is the product removed from the cart")
    @Test(dataProvider = "productData", description = "Check 'Remove' button test")
    public void checkRemoveButton(IProduct product) {
        log.debug("checkRemoveButton test started");
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
        EmptyCartComponent emptyCartComponent = cartProductContainer
                .removeProductByName(product)
                .gotoHomePage()
                .openEmptyCart();
        // Check
       Assert.assertTrue(emptyCartComponent.isCartEmpty());
        log.debug("checkRemoveButton test finished");
    }

    @Description("Test Description: This test check clicking on item name in cart")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Click on product name and check did we go to the product page")
    @Test(dataProvider = "productData", description = "Check clicking on item name test")
    public void checkClickOnItem(IProduct product) {
        log.debug("checkClickOnItem test started");
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
        ProductPage productPage = cartProductContainer
                .gotoProductPage(product);
        // Check
        Assert.assertTrue(productPage.getDescriptionText()
                .contains(product.getDescription()));
        log.debug("checkClickOnItem test finished");
    }

}

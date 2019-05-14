package com.softserve.edu.opencart.tests.cart_component_tests;

import com.softserve.edu.opencart.data.IProduct;
import com.softserve.edu.opencart.data.IUser;
import com.softserve.edu.opencart.data.ProductRepository;
import com.softserve.edu.opencart.data.UserRepository;
import com.softserve.edu.opencart.pages.account.MyAccountPage;
import com.softserve.edu.opencart.pages.shop.CartProductComponent;
import com.softserve.edu.opencart.pages.shop.CartProductContainer;
import com.softserve.edu.opencart.pages.common.HomePage;
import com.softserve.edu.opencart.pages.shop.EmptyCartComponent;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

/**
 * This class includes test cases for some
 * other functionality of product cart.
 */
@Epic("Functional Testing")
@Feature("OtherFunctionalTest")
public class OtherFunctionalTest extends ATestRunner {

    /**
     * Data provider for checkPriceTextButton test.
     *
     * @return product from product repository
     * and expected test result.
     */
    @DataProvider // (parallel = true)
    public Object[][] validData() {
        return new Object[][]{
                {ProductRepository.getMacBook() ,"1 item(s) - $602.00"},
        };
    }

    /**
     * This test check does the info about products displayed on Cart Button.
     *
     * Add product to cart and check that the info about products displayed on Cart Button.
     *
     * @param product product from product repository.
     * @param text expected test result.
     */
    @Description("Test Description: This test check does the info about products displayed on Cart Button")
    @Severity(SeverityLevel.NORMAL)
    @Story("Add product to cart and check that the info about products displayed on Cart Button")
    @Test(dataProvider = "validData", description = "Check info about products on Cart Button")
    public void checkPriceTextButton(IProduct product, String text) {
        log.debug("checkPriceTextButton test started");
        // Steps
        HomePage homePage = loadApplication()
                .addProductToCart(product)
                .gotoHomePage();
        // Check
        Assert.assertTrue(homePage.getCartButtonText().contains(text));
        // Steps
        homePage
                .openCartProductContainer()
                .removeProductByName(product);
        log.debug("checkPriceTextButton test finished");
    }

    /**
     * Data provider for logoutUserCartTest test.
     *
     * @return User and product from repositories.
     */
    @DataProvider // (parallel = true)
    public Object[][] validUsers() {
        return new Object[][]{
                {UserRepository.get().johnWick(), ProductRepository.getMacBook()},
        };
    }

    /**
     * This test checks whether goods in the cart
     * are stored when the user leaves the profile.
     *
     * Add the item to the cart under the logged-in
     * user and exit the profile.
     *
     * @param user product from product repository
     * @param product product from repository.
     */
    @Description("Test Description: This test checks whether goods in the cart are stored " +
            "when the user leaves the profile")
    @Severity(SeverityLevel.NORMAL)
    @Story("Add the item to the cart under the logged-in user and exit the profile")
    @Test(dataProvider = "validUsers", description = "Checking the user's personal cart")
    public void logoutUserCartTest(IUser user, IProduct product) {
        log.debug("logoutUserCartTest test started");
        // Steps
        MyAccountPage myAccountPage = loadApplication()
                .gotoLoginPage()
                .successLogin(user);
        CartProductContainer cartProductContainer = myAccountPage
                .gotoHomePage()
                .addProductToCart(product)
                .openCartProductContainer();
        List<CartProductComponent> cartProductComponents = cartProductContainer
                .getCartProductComponents();
        // Check
        Assert.assertEquals(cartProductComponents.get(0).getCartProductNameText(),
                product.getName());
        // Steps
        EmptyCartComponent emptyCartComponent = cartProductContainer
                .logout()
                .continueHomePage()
                .openEmptyCart();
        // Check
        Assert.assertTrue(emptyCartComponent.isCartEmpty());
        log.debug("logoutUserCartTest test finished");
    }

    /**
     * Data provider for differentLoggedUsersCartTest test.
     *
     * @return User and product from repositories.
     */
    @DataProvider // (parallel = true)
    public Object[][] validDifferentUsers() {
        return new Object[][]{
                {UserRepository.get().johnWick(), ProductRepository.getMacBook()},
                {UserRepository.get().yaroslav(), ProductRepository.getIPhone()}
        };
    }

    /**
     *This test checks whether goods in the cart
     * are not saved when user changes.
     *
     * Add the item to the cart under the
     * logged-in user and change profile.
     *
     * @param user product from product repository
     * @param product product from repository.
     */
    @Description("Test Description: This test checks whether goods in the cart are not saved when user changes")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Add the item to the cart under the logged-in user and change profile")
    @Test(dataProvider = "validDifferentUsers", description = "Checking the user's personal cart")
    public void differentLoggedUsersCartTest(IUser user, IProduct product) {
        log.debug("differentLoggedUsersCartTest test started");
        // Steps
        MyAccountPage myAccountPage = loadApplication()
                .gotoLoginPage()
                .successLogin(user);
        CartProductContainer cartProductContainer = myAccountPage
                .gotoHomePage()
                .addProductToCart(product)
                .openCartProductContainer();
        List<CartProductComponent> cartProductComponents = cartProductContainer
                .getCartProductComponents();
        // Check
        Assert.assertEquals(cartProductComponents.get(0).getCartProductNameText(),
                product.getName());
        // Steps
        cartProductComponents.clear();
        cartProductContainer
                .gotoHomePage()
                .openCartProductContainer()
                .removeProductByName(product)
                .gotoHomePage()
                .logout()
                .continueHomePage();
        log.debug("differentLoggedUsersCartTest test finished");
    }
}

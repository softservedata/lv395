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
import com.softserve.edu.opencart.tests.ATestRunner;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class OtherFunctionalTest extends ATestRunner {

    @DataProvider // (parallel = true)
    public Object[][] validData() {
        return new Object[][]{
                {ProductRepository.getMacBook() ,"1 item(s) - $602.00"},
        };
    }

    @Test(dataProvider = "validData")
    public void checkPriceTextButton(IProduct product, String text) {
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
    }

    @DataProvider // (parallel = true)
    public Object[][] validUsers() {
        return new Object[][]{
                {UserRepository.get().johnWick(), ProductRepository.getMacBook()},
        };
    }

    @Test(dataProvider = "validUsers")
    public void logoutUserCartTest(IUser user, IProduct product) {
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
        EmptyCartComponent emptyCartComponent = myAccountPage
                .refresh()
                .logout()
                .continueHomePage()
                .openEmptyCart();
        // Check
        Assert.assertTrue(emptyCartComponent.isCartEmpty());
    }

    @DataProvider // (parallel = true)
    public Object[][] validDifferentUsers() {
        return new Object[][]{
                {UserRepository.get().johnWick(), ProductRepository.getMacBook()},
                {UserRepository.get().yaroslav(), ProductRepository.getIPhone()}
        };
    }

    @Test(dataProvider = "validDifferentUsers")
    public void differentLoggedUsersCartTest(IUser user, IProduct product) {
        // Steps
        MyAccountPage myAccountPage = loadApplication()
                .gotoLoginPage()
                .successLogin(user);
        CartProductContainer cartProductContainer = myAccountPage
                .gotoHomePage()
                .refresh()
                .addProductToCart(product)
                .refresh()
                .openCartProductContainer();
        List<CartProductComponent> cartProductComponents = cartProductContainer
                .getCartProductComponents();
        // Check
        Assert.assertEquals(cartProductComponents.get(0).getCartProductNameText(),
                product.getName());
        // Steps
        cartProductComponents.clear();
        myAccountPage
                .refresh()
                .gotoHomePage()
                .openCartProductContainer()
                .removeProductByName(product)
                .gotoHomePage()
                .logout()
                .continueHomePage();
    }
}

package com.softserve.edu.opencart.tests.cart_component_tests;

import com.softserve.edu.opencart.data.IProduct;
import com.softserve.edu.opencart.data.IUser;
import com.softserve.edu.opencart.data.ProductRepository;
import com.softserve.edu.opencart.data.UserRepository;
import com.softserve.edu.opencart.pages.account.MyAccountPage;
import com.softserve.edu.opencart.pages.common.CartProductComponent;
import com.softserve.edu.opencart.pages.common.CartProductContainer;
import com.softserve.edu.opencart.pages.common.HomePage;
import com.softserve.edu.opencart.tests.ATestRunner;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class OtherFunctionalTest extends ATestRunner {

    @Test
    public void checkPriceTextButton() {
        // Steps
        HomePage homePage = loadApplication()
                .addProductToCart(ProductRepository.getMacBook())
                .gotoHomePage();
        // Check
        Assert.assertTrue(homePage.getCartButtonText().contains(" 1 item(s) - $602.00"));
        // Steps
        homePage
                .openCartProductContainer()
                .removeProductByName(ProductRepository.getMacBook());
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
        myAccountPage
                .refresh()
                .logout()
                .continueHomePage()
                .openCartProductContainer();
        // Check
        Assert.assertTrue(cartProductContainer.isCartEmpty());
    }

/*    @DataProvider // (parallel = true)
    public Object[][] validDifferentUsers() {
        return new Object[][] {
                { UserRepository.get().johnWick(), UserRepository.get().yaroslav(), ProductRepository.getMacBook()},
        };
    }

    @Test(dataProvider = "validDifferentUsers")
    public void differentLoggedUsersCartTest(IUser firstUser, IUser secondUser, IProduct product) {
        IUser user = firstUser;
        int step = 1;
        while (step <= 2){
            if (step == 2) user = secondUser;
            // Steps
            MyAccountPage myAccountPage = loadApplication()
                    .gotoLoginPage()
                    .successLogin(user);
            CartProductContainer cartProductContainer = myAccountPage
                    .gotoHomePage()
                    .addProductToCart(product)
                    .refresh()
                    .openCartProductContainer();
            List<CartProductComponent> cartProductComponents = cartProductContainer
                    .getCartProductComponents();
            // Check
            Assert.assertTrue(cartProductComponents.size() == 1);
            // Steps
            cartProductComponents.clear();
            step += step;
            myAccountPage
                    .refresh()
                    .gotoHomePage()
                    .openCartProductContainer()
                    .removeProductByName(product)
                    .refresh()
                    .logout()
                    .continueHomePage();
        }
    }*/

    @DataProvider // (parallel = true)
    public Object[][] validDifferentUsers() {
        return new Object[][]{
                {UserRepository.get().johnWick(), ProductRepository.getMacBook()},
                {UserRepository.get().yaroslav(), ProductRepository.getIPhone3()}
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
                .addProductToCart(product)
                .openCartProductContainer();
        List<CartProductComponent> cartProductComponents = cartProductContainer
                .getCartProductComponents();
        // Check
        Assert.assertEquals(cartProductComponents.get(0).getCartProductNameText(),
                product.getName());
        // Steps
        myAccountPage
                .refresh()
                .logout()
                .continueHomePage();
    }
}

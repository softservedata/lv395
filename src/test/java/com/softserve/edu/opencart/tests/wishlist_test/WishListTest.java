package com.softserve.edu.opencart.tests.wishlist_test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.softserve.edu.opencart.data.IUser;
import com.softserve.edu.opencart.data.UserRepository;
import com.softserve.edu.opencart.pages.account.AccountLogoutPage;
import com.softserve.edu.opencart.pages.common.HomePage;
import com.softserve.edu.opencart.pages.account.MyAccountPage;
import com.softserve.edu.opencart.pages.shop.ShoppingCartPage;
import com.softserve.edu.opencart.pages.account.WishListUpdated;
import com.softserve.edu.opencart.pages.account.WishListPage;

/**
 * This test suit is for testing "Wish List functional"
 *
 * @author Bohdan Vasyliv
 */

public class WishListTest extends TestRunner {

    @DataProvider // (parallel = true)
    public Object[][] validUsers() {
        // Read from ...
        return new Object[][]{{UserRepository.get().wishUser()},};
    }

    @DataProvider // (parallel = true)
    public Object[][] SomeProduct() {
        // Read from ...
        return new Object[][]{{UserRepository.get().wishUser(), "MacBook"},};
    }


    @Test(dataProvider = "validUsers", priority = 1)
    public void checkEmptyWishList(IUser validUser) {
        MyAccountPage myAccountPage = loadApplication().gotoLoginPage().gotoLoginPage().successLogin(validUser);
        executionTimeout(1000);

        myAccountPage.clickWishList();
       executionTimeout(1000);

         //Login with correct credentials

        Assert.assertEquals(myAccountPage.gotoEmptyWishListPage().getEmptyWishListText(),
                myAccountPage.gotoEmptyWishListPage().EXPECT_EMPTY_WISH_TEXT);
        executionTimeout(1000);

        myAccountPage = myAccountPage.gotoEmptyWishListPage().clickEmptyWishListButtonContinue();

        // Return to previous state
        // Steps
        AccountLogoutPage accountLogoutPage = myAccountPage.logout();
        accountLogoutPage.continueHomePage();
        executionTimeout(1000);
    }

    // Add product to wish list
    @Test(dataProvider = "SomeProduct", priority = 2)
    public void addProductToWishList(IUser validUser, String partialProductName) {
        // Precondition
        // Steps
        MyAccountPage myAccountPage = loadApplication().gotoLoginPage().successLogin(validUser);
        executionTimeout(1000);

        HomePage homePage = myAccountPage.gotoHomePage();

        int countWishList = homePage.getWishListNumber();

        // Add product to Wish List
        homePage = homePage.putToWishProductByPartialName(partialProductName);
        executionTimeout(1000);

        // Check Message about add to Wish List
        Assert.assertEquals(homePage.getAlertMessageText(),
                String.format(homePage.EXPECTED_MESSAGE_WISH, partialProductName));
        executionTimeout(1000);

        // Close message
        homePage = homePage.closeAlertMessage();
        executionTimeout(1000);
        // Check countWishList
        Assert.assertEquals(homePage.getWishListNumber(), countWishList + 1);

        // Go to Wish List
        homePage.clickWishList();
        executionTimeout(1000);
        // Check in table to present ProductName
        Assert.assertEquals(homePage.gotoWishListPage().getWishListProductNameByPartialName(partialProductName),
                partialProductName);
        executionTimeout(1000);

        //Remove product from wish list

        WishListUpdated wishListUpdated = homePage.gotoWishListPage()
                .removeFromWishListProductByPartialName(partialProductName);
        Assert.assertEquals(wishListUpdated.getAlertMessageText(), wishListUpdated.EXPECTED_MESSAGE_WISH_REMOVE);
        executionTimeout(1000);

        // Close message
        WishListPage wishListPage = wishListUpdated.closeAlertMessage();

        // Go to myAccountPage and logout page
        myAccountPage = wishListPage.clickWishListButtonContinue();
        //delayExecution(1000);

        AccountLogoutPage accountLogoutPage = myAccountPage.logout();
        accountLogoutPage.continueHomePage();
        executionTimeout(1000);

    }

    // Add product to cart from wish list
    @Test(dataProvider = "SomeProduct", priority = 3)
    public void addProductToCartFromWishList(IUser validUser, String partialProductName) {
        //
        // Precondition
        // Steps
        MyAccountPage myAccountPage = loadApplication().gotoLoginPage().successLogin(validUser);
        executionTimeout(1000);

        HomePage homePage = myAccountPage.gotoHomePage();
        executionTimeout(1000);

        // Add product to Wish List
        homePage = homePage.putToWishProductByPartialName(partialProductName);
        executionTimeout(1000);

        // Close message
        homePage = homePage.closeAlertMessage();
        executionTimeout(1000);

        // Go to Wish List
        homePage.clickWishList();
        executionTimeout(1000);

        WishListUpdated wishListUpdated = homePage.gotoWishListPage()
                .putFromWishListToCartProductByPartialName(partialProductName);
        Assert.assertEquals(wishListUpdated.getAlertMessageText(),
                String.format(HomePage.EXPECTED_MESSAGE_CART, partialProductName));
        executionTimeout(1000);

        //Remove product from wish list
        wishListUpdated = homePage.gotoWishListPage().removeFromWishListProductByPartialName(partialProductName);

        // Close message
        WishListPage wishListPage = wishListUpdated.closeAlertMessage();

        //Go to ShoppingCart and check if goods was added
        ShoppingCartPage shoppingCartPage = wishListPage.gotoShoppingCartPage();
        Assert.assertTrue(shoppingCartPage.getProductsCartListComponent()
                .getProductsNameList()
                .contains(partialProductName));
        executionTimeout(1000); //ForDemonstration


        //Return to previous state: remove ProductName from cart List
        shoppingCartPage.removeProductQuantityByPartialName(partialProductName);
        homePage = shoppingCartPage.openEmptyCart().gotoHomePage();
        executionTimeout(1000); //ForDemonstration

        //Logout
        homePage.logout().gotoHomePage();
        executionTimeout(1000); //ForDemonstration
    }

    protected void executionTimeout(long miliseconds) {
        try {
            Thread.sleep(miliseconds);
        } catch (InterruptedException e) {

            System.err.println("Cannot thread sleep!");

        }
    }
}
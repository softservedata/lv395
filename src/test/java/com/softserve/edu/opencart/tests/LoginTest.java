package com.softserve.edu.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.softserve.edu.opencart.data.IUser;
import com.softserve.edu.opencart.data.UserRepository;
import com.softserve.edu.opencart.pages.common.AddressBookPage;
import com.softserve.edu.opencart.pages.common.HomePage;
import com.softserve.edu.opencart.pages.common.LoginPage;
import com.softserve.edu.opencart.pages.common.MyAccountPage;

public class LoginTest extends ATestRunner {

    @DataProvider // (parallel = true)
    public Object[][] validUsers() {
        return new Object[][] {
            { UserRepository.get().yaroslav() },
        };
    }

    //@Test(dataProvider = "validUsers")
    public void checkLogin(IUser user) throws Exception {
        // Steps
        MyAccountPage myAccountPage = loadApplication()
                .gotoLoginPage()
                .successLogin(user);
        // Check
        Assert.assertTrue(myAccountPage.getMyAccountLabelText()
                .equals(MyAccountPage.MY_ACCOUNT_MESSAGE));
        // Steps
        AddressBookPage addressBookPage = myAccountPage
                .gotoAddressBookPage(user);
        // Check
        Assert.assertTrue(addressBookPage.getTableEntriesText()
                .contains(user.getFirstname()));
        Assert.assertTrue(addressBookPage.getTableEntriesText()
                .contains(user.getLastname()));
        // Steps
        HomePage homePage = addressBookPage
                .logout()
                .continueHomePage();
        // Check
        Assert.assertTrue(homePage.getSlideshow0FirstImageAttributeSrcText()
                .contains(HomePage.IPHONE_IMAGE));
    }

    @Test(dataProvider = "validUsers")
    public void checkLogin2(IUser user) throws Exception {
        // Steps
        AddressBookPage addressBookPage = loadApplication()
                .gotoLoginPage()
                .gotoAddressBookPage(user);
        // Check
        Assert.assertTrue(addressBookPage.getTableEntriesText()
                .contains(user.getFirstname()));
        Assert.assertTrue(addressBookPage.getTableEntriesText()
                .contains(user.getLastname()));
        // Steps
        HomePage homePage = addressBookPage
                .logout()
                .continueHomePage();
        // Check
        Assert.assertTrue(homePage.getSlideshow0FirstImageAttributeSrcText()
                .contains(HomePage.IPHONE_IMAGE));
    }

}

package com.softserve.edu.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.softserve.edu.opencart.data.IUser;
import com.softserve.edu.opencart.data.UserRepository;
import com.softserve.edu.opencart.pages.common.HomePage;
import com.softserve.edu.opencart.pages.common.MyAccountPage;
import com.softserve.edu.opencart.pages.common.ProductComponent;

public class LoginTest extends ATestRunner {

    @DataProvider // (parallel = true)
    public Object[][] validUsers() {
        return new Object[][] {
            { UserRepository.get().johnwick() },
        };
    }

    @Test(dataProvider = "validUsers")
    public void checkLogin(IUser user) throws Exception {
        // Steps
        MyAccountPage myAccountPage = loadApplication()
                .gotoLoginPage()
                .successLogin(user);
        // Check
        Assert.assertTrue(myAccountPage.getMyAccountLabelText()
                .equals(MyAccountPage.MY_ACCOUNT_MESSAGE));
        // Steps
        HomePage homePage = myAccountPage
                .logout()
                .continueHomePage();
        // Check
        Assert.assertTrue(homePage.getSlideshow0FirstImageAttributeSrcText()
                .contains(HomePage.IPHONE_IMAGE));
    }

}

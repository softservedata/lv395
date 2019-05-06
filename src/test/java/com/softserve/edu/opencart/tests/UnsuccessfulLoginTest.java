package com.softserve.edu.opencart.tests;

import com.softserve.edu.opencart.data.IUser;
import com.softserve.edu.opencart.data.UserRepository;
import com.softserve.edu.opencart.pages.common.UnsuccessfulLoginPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class UnsuccessfulLoginTest extends ATestRunner {
    @DataProvider // (parallel = true)
    public Object[][] invalidUsers() {
        return new Object[][] {
                { UserRepository.get().userWithIncorrectCredentials() },
        };
    }

    @Test(dataProvider = "invalidUsers")
    public void checkLogin(IUser user) throws Exception {
        // Steps
        UnsuccessfulLoginPage unsuccessfulLoginPage = loadApplication()
                .gotoLoginPage()
                .unsuccessfulLogin(user);
        // Check
        Assert.assertTrue(unsuccessfulLoginPage.getUnsuccessfulMessageText()
                .equals(UnsuccessfulLoginPage.ERROR_MESSAGE));
    }
}

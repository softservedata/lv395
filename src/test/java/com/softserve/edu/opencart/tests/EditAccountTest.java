package com.softserve.edu.opencart.tests;

import com.softserve.edu.opencart.data.IUser;
import com.softserve.edu.opencart.data.UserRepository;
import com.softserve.edu.opencart.pages.common.MyAccountPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class EditAccountTest extends ATestRunner {
    @DataProvider // (parallel = true)
    public Object[][] validUser() {
        return new Object[][] {
                { UserRepository.get().johnWick() },
        };
    }

    @Test(dataProvider = "validUser")
    public void checkLogin(IUser user) throws Exception {
        // Steps
        MyAccountPage myAccountPage = loadApplication()
                .gotoLoginPage()
                .successLogin(user)
                .clickEditAccountButton()
                .changeUserInfo(user);

        // Check
        Assert.assertTrue(myAccountPage.getMessage()
                .equals(MyAccountPage.MY_ACCOUNT_UPDATE_MESSAGE));
    }
}

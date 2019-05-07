package com.softserve.edu.opencart.tests;

import com.softserve.edu.opencart.data.IUser;
import com.softserve.edu.opencart.data.UserRepository;
import com.softserve.edu.opencart.pages.account.MyAccountPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author Yurii Antokhiv
 * @version 1.0
 */
public class ChangePasswordTest extends ATestRunner{
    @DataProvider // (parallel = true)
    public Object[][] validUser() {
        return new Object[][] {
                { UserRepository.get().johnWick() },
        };
    }

    @Test(dataProvider = "validUser")
    public void changePasswordTest(IUser user) throws Exception {
        // Steps
        MyAccountPage myAccountPage = loadApplication()
                .gotoLoginPage()
                .successLogin(user)
                .clickPasswordButton()
                //TODO add data provider with password values
                .changePassword("qwerty");

        // Check
        Assert.assertEquals(MyAccountPage.MY_PASSWORD_UPDATE_MESSAGE
                , myAccountPage.getMessageText());
    }
}

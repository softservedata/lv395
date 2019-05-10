package com.softserve.edu.opencart.tests.register_test;

import com.softserve.edu.opencart.data.IUser;
import com.softserve.edu.opencart.data.UserRepository;
import com.softserve.edu.opencart.pages.account.AccountLogoutPage;
import com.softserve.edu.opencart.pages.account.MyAccountPage;
import com.softserve.edu.opencart.pages.account.RegisterPage;
import com.softserve.edu.opencart.pages.account.SuccessfullyRegisterPage;
import com.softserve.edu.opencart.pages.common.HomePage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CorrectDataTest extends TestRunner {

    @DataProvider
    public Object[][] validMinValuesUser() {
        return new Object[][]{
                {UserRepository.get().validUserWithBoundaryValues1()},
        };
    }

    @DataProvider
    public Object[][] validMaxValuesUser() {
        return new Object[][]{
                {UserRepository.get().validUserWithBoundaryValues2()},
        };
    }

    @Test(dataProvider = "validMinValuesUser")
    public void checkValidRegister(IUser user) {
        //Steps
        SuccessfullyRegisterPage sc = loadApplication()
                .gotoRegisterPage()
                .successfullyRegisterUser(user);

        //Check
        Assert.assertTrue(sc.getExpectedSuccessMessage().
                equals(sc.EXPECTED_SUCCESS_MESSAGE));
        //Step
        AccountLogoutPage accountLogoutPage = sc
                .logOut();
        //Check
        Assert.assertTrue(accountLogoutPage.getActualAccountLogoutMessage()
                .equals(accountLogoutPage.EXPECTED_ACCOUNT_MESSAGE));
    }

    @Test(dataProvider = "validMaxValuesUser")
    public void checkValidRegister2(IUser user) {
        //Steps
        SuccessfullyRegisterPage sc = loadApplication()
                .gotoRegisterPage()
                .successfullyRegisterUser(user);

        //Check
        Assert.assertTrue(sc.getExpectedSuccessMessage().
                equals(sc.EXPECTED_SUCCESS_MESSAGE));
    }
}








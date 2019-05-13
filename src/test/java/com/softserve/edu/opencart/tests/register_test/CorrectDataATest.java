package com.softserve.edu.opencart.tests.register_test;

import com.softserve.edu.opencart.data.IUser;
import com.softserve.edu.opencart.data.UserRepository;
import com.softserve.edu.opencart.pages.account.AccountLogoutPage;
import com.softserve.edu.opencart.pages.account.SuccessfullyRegisterPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CorrectDataATest extends ATestRunner {

    @DataProvider
    public Object[][] validMinValuesUser() {
        return new Object[][]{
                {UserRepository.get().validUserWithBoundaryValues1()},
                {UserRepository.get().validUserWithBoundaryValues2()}
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
        AccountLogoutPage accountLogoutPage = sc.logOut();
        //Check
        Assert.assertTrue(accountLogoutPage.getActualAccountLogoutMessage()
                .equals(accountLogoutPage.EXPECTED_ACCOUNT_MESSAGE));
    }

    //TODO if 2 users had been created delete them, if not throw exception
}








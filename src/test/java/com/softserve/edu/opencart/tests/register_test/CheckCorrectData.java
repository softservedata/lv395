package com.softserve.edu.opencart.tests.register_test;

import com.softserve.edu.opencart.data.IUser;
import com.softserve.edu.opencart.data.UserRepository;
import com.softserve.edu.opencart.pages.account.MyAccountPage;
import com.softserve.edu.opencart.pages.account.RegisterPage;
import com.softserve.edu.opencart.pages.account.SuccessfullyRegisterPage;
import com.softserve.edu.opencart.pages.common.HomePage;
import com.softserve.edu.opencart.tests.ATestRunner;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CheckCorrectData extends ATestRunner {

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








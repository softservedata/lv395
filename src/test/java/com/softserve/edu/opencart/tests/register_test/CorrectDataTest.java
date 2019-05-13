package com.softserve.edu.opencart.tests.register_test;

import com.softserve.edu.opencart.data.IUser;
import com.softserve.edu.opencart.data.UserRepository;
import com.softserve.edu.opencart.pages.account.AccountLogoutPage;
import com.softserve.edu.opencart.pages.account.SuccessfullyRegisterPage;
import com.softserve.edu.opencart.tools.DataBaseUtils;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Epic("Functional Testings")
@Feature("Register with correct data")
public class CorrectDataTest extends ATestRunner {

    @DataProvider
    public Object[][] validMinValuesUser() {
        return new Object[][]{
                {UserRepository.get().validUserWithBoundaryValues1()},
                {UserRepository.get().validUserWithBoundaryValues2()}
        };
    }

    @Description("Run two tests with correct boundary user values")
    @Severity(SeverityLevel.MINOR)
    @Story("Go to RegisterPage, fill field with correct user data")
    @Test(dataProvider = "validMinValuesUser", description = "CheckValidRegister")
    public void checkValidRegister(IUser user) {
        log.debug("checkValidRegister start");
        //Steps
        SuccessfullyRegisterPage sc = loadApplication()
                .gotoRegisterPage()
                .successfullyRegisterUser(user);
        saveImageAttach("Actualresult");
        DataBaseUtils db = new DataBaseUtils();
        //Check
        Assert.assertTrue(sc.getExpectedSuccessMessage().
                equals(sc.EXPECTED_SUCCESS_MESSAGE));
        //Step
        AccountLogoutPage accountLogoutPage = sc.logOut();
        //Check
        Assert.assertTrue(accountLogoutPage.getActualAccountLogoutMessage()
                .equals(accountLogoutPage.EXPECTED_ACCOUNT_MESSAGE));
        Assert.assertTrue(db.isEmailInDb(user));
        log.debug("checkValidRegister end");
    }

    //TODO if 2 users had been created delete them, if not throw exception
}








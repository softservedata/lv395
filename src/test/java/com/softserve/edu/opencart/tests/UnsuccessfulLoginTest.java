package com.softserve.edu.opencart.tests;

import com.softserve.edu.opencart.data.IUser;
import com.softserve.edu.opencart.data.UserRepository;
import com.softserve.edu.opencart.pages.account.UnsuccessfulLoginPage;
import com.softserve.edu.opencart.tools.DataBaseUtils;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Yurii Antokhiv
 * @version 1.0
 */
public class UnsuccessfulLoginTest extends ATestRunner {
    private int attempts = 5;
    private DataBaseUtils dbUtils;

    @DataProvider // (parallel = true)
    public Object[][] invalidUsers() {
        return new Object[][]{
                {UserRepository.get().userWithIncorrectCredentials()},
                {UserRepository.get().userWithIncorrectCredentials()},
                {UserRepository.get().userWithIncorrectCredentials()},
                {UserRepository.get().userWithIncorrectCredentials()},
                {UserRepository.get().userWithIncorrectCredentials()},
        };
    }

    @Test(dataProvider = "invalidUsers")
    public void checkUnsuccessfulLogin(IUser invalidUser) throws Exception {
        // Steps
        UnsuccessfulLoginPage unsuccessfulLoginPage = loadApplication()
                .gotoLoginPage()
                .unsuccessfulLogin(invalidUser);
        attempts--;

        // Check
        if (attempts >= 0) {
            Assert.assertEquals(UnsuccessfulLoginPage.LOGIN_PAGE_INCORRECT_CREDENTIALS_MESSAGE
                    , unsuccessfulLoginPage.getUnsuccessfulMessageText());
        } else {
            Assert.assertEquals(UnsuccessfulLoginPage.TOO_MUCH_LOGIN_ATTEMPTS
                    , unsuccessfulLoginPage.getUnsuccessfulMessageText());

        }
    }

    @AfterClass
    public void setAttemptsInDatabaseToNull(){
        System.out.println("Attempts set to null !!!");
        dbUtils = new DataBaseUtils();
        dbUtils.setAttemptsToNull();
    }
}

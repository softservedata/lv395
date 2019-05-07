package com.softserve.edu.opencart.tests.login_test;

import com.softserve.edu.opencart.data.IUser;
import com.softserve.edu.opencart.data.UserRepository;
import com.softserve.edu.opencart.pages.account.UnsuccessfulLoginPage;
import com.softserve.edu.opencart.tests.ATestRunner;
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
    private int attempts = 0;
    private DataBaseUtils dbUtils;

    @DataProvider // (parallel = true)
    public Object[][] invalidUsers() {
        return new Object[][]{
                //TODO add normal data, delete this shit.
                {UserRepository.get().userWithIncorrectCredentials()},
                {UserRepository.get().userWithIncorrectCredentials()},
                {UserRepository.get().userWithIncorrectCredentials()},
                {UserRepository.get().userWithIncorrectCredentials()},
                {UserRepository.get().userWithIncorrectCredentials()},
                {UserRepository.get().userWithIncorrectCredentials()}
        };
    }

    @Test(dataProvider = "invalidUsers")
    public void checkUnsuccessfulLogin(IUser invalidUser) throws Exception {
        // Steps
        UnsuccessfulLoginPage unsuccessfulLoginPage = loadApplication()
                .gotoLoginPage()
                .unsuccessfulLogin(invalidUser);
        // Check
        if (attempts < 5) {
            Assert.assertEquals(UnsuccessfulLoginPage.LOGIN_PAGE_INCORRECT_CREDENTIALS_MESSAGE
                    , unsuccessfulLoginPage.getUnsuccessfulMessageText());
        } else {
            Assert.assertEquals(UnsuccessfulLoginPage.TOO_MUCH_LOGIN_ATTEMPTS
                    , unsuccessfulLoginPage.getUnsuccessfulMessageText());
        }
        attempts++;
    }

    @AfterClass
    public void setAttemptsInDatabaseToNull(){
        dbUtils = new DataBaseUtils();
        dbUtils.setAttemptsToNull();
    }
}

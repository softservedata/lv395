package com.softserve.edu.opencart.tests.register_test;

import com.softserve.edu.opencart.data.IUser;
import com.softserve.edu.opencart.data.UserRepository;
import com.softserve.edu.opencart.pages.account.UnsuccessfullyRegisterPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class IncorrectDataTest extends ATestRunner {

    @DataProvider
    public Object[][] invalidValuesUser() {
        return new Object[][]{
                {UserRepository.get().invalidUserWithBoundaryValues()},
                {UserRepository.get().invalidUserWithBoundaryValues2()}
        };
    }

    @Test(dataProvider = "invalidValuesUser")
    public void checkInvalidRegister(IUser user) {
        //Steps
        UnsuccessfullyRegisterPage unsuccessRegisterPage = loadApplication()
                .gotoRegisterPage()
                .userWithBadData(user);

        //Check
        unsuccessRegisterPage.checkErrorMessages();
    }
    //Todo if user has been created delete and throw exception

    //Todo test login without PrivacyPolicy
}

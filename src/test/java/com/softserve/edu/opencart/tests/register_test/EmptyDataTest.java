package com.softserve.edu.opencart.tests.register_test;

import com.softserve.edu.opencart.data.IUser;
import com.softserve.edu.opencart.data.UserRepository;
import com.softserve.edu.opencart.pages.account.UnsuccessfullyRegisterPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class EmptyDataTest extends TestRunner {

    @DataProvider
    public Object[][] emptyUser() {
        return new Object[][]{
                {UserRepository.get().emptyFieldsUser()}
        };
    }

    /**
     * Negative test.
     * Used technique: Decision Tables.
     * This test checks if user can be registered
     * with empty fields.
     */
    @Test(dataProvider = "emptyUser")
    public void checkIfCanRegisterWithNoDataEntered(IUser emptyUser) {
        UnsuccessfullyRegisterPage unsuccessfullyRegisterPage = loadApplication()
                .gotoRegisterPage()
                .userWithNoData(emptyUser);

        unsuccessfullyRegisterPage.checkErrorMessages();
    }
}

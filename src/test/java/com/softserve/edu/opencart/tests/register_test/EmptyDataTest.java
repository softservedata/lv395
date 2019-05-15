package com.softserve.edu.opencart.tests.register_test;

import com.softserve.edu.opencart.data.IUser;
import com.softserve.edu.opencart.data.UserRepository;
import com.softserve.edu.opencart.pages.account.UnsuccessfullyRegisterPage;
import com.softserve.edu.opencart.tools.DataBaseUtils;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static com.softserve.edu.opencart.pages.account.UnsuccessfullyRegisterPage.*;

@Epic("Functional Test")
@Feature("Register with empty data")
public class EmptyDataTest extends ATestRunner {

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
    @Description("Run two tests with empty user values")
    @Severity(SeverityLevel.NORMAL)
    @Story("Go to RegisterPage, fill field with empty user data")
    @Test(dataProvider = "emptyUser", description = "checkIfCanRegisterWithNoDataEntered")
    public void checkIfCanRegisterWithNoDataEntered(IUser emptyUser) {
        log.debug("checkIfCanRegisterWithNoDataEntered starts");
        UnsuccessfullyRegisterPage uRegPage = loadApplication()
                .gotoRegisterPage()
                .userWithNoData(emptyUser);
        saveImageAttach("Actualresult");

        Assert.assertEquals(EXPECTED_WARNING_FIRST_NAME, uRegPage.getActualFirstNameError());
        Assert.assertEquals(EXPECTED_WARNING_LAST_NAME, uRegPage.getActualLastNameError());
        Assert.assertEquals(EXPECTED_WARNING_EMAIL, uRegPage.getActualEmailError());
        Assert.assertEquals(EXPECTED_WARNING_TELEPHONE, uRegPage.getActualTelephoneError());
        Assert.assertEquals(EXPECTED_WARNING_ADDRESS1, uRegPage.getActualAddressError());
        Assert.assertEquals(EXPECTED_WARNING_CITY, uRegPage.getActualCityError());
        Assert.assertEquals(EXPECTED_WARNING_REGION, uRegPage.getActualRegionError());
        Assert.assertEquals(EXPECTED_WARNING_PASSWORD, uRegPage.getActualPasswordError());

        DataBaseUtils db = new DataBaseUtils();
        Assert.assertFalse(db.isEmailInDb(emptyUser));

        log.debug("checkIfCanRegisterWithNoDataEntered ends");
    }
}

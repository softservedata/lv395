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
@Feature("Register with incorrect data")
public class IncorrectDataTest extends ATestRunner {

    @DataProvider
    public Object[][] invalidValuesUser() {
        return new Object[][]{
                {UserRepository.get().invalidUserWithBoundaryValues()},
                {UserRepository.get().invalidUserWithBoundaryValues2()}
        };
    }

    @Description("Run two tests with incorrect boundary user values")
    @Severity(SeverityLevel.NORMAL)
    @Story("Go to RegisterPage, fill field with incorrect user data")
    @Test(dataProvider = "invalidValuesUser", description = "CheckInvalidRegister")
    public void checkInvalidRegister(IUser user) {
        log.debug("CheckInvalidRegister starts");
        //Steps
        UnsuccessfullyRegisterPage uRegPage = loadApplication()
                .gotoRegisterPage()
                .userWithBadData(user);
        saveImageAttach("Actualresult");

        //Check
        Assert.assertEquals(EXPECTED_WARNING_FIRST_NAME, uRegPage.getActualFirstNameError());
        Assert.assertEquals(EXPECTED_WARNING_LAST_NAME, uRegPage.getActualLastNameError());
        Assert.assertEquals(EXPECTED_WARNING_EMAIL, uRegPage.getActualEmailError());
        Assert.assertEquals(EXPECTED_WARNING_TELEPHONE, uRegPage.getActualTelephoneError());
        Assert.assertEquals(EXPECTED_WARNING_ADDRESS1, uRegPage.getActualAddressError());
        Assert.assertEquals(EXPECTED_WARNING_CITY, uRegPage.getActualCityError());
        Assert.assertEquals(EXPECTED_WARNING_REGION, uRegPage.getActualRegionError());
        Assert.assertEquals(EXPECTED_WARNING_PASSWORD, uRegPage.getActualPasswordError());

        DataBaseUtils db = new DataBaseUtils();
        Assert.assertFalse(db.isEmailInDb(user));

        log.debug("CheckInvalidRegister ends");
    }
    //Todo if user has been created delete and throw exception
}

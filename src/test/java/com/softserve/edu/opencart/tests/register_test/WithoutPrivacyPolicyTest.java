package com.softserve.edu.opencart.tests.register_test;

import com.softserve.edu.opencart.data.IUser;
import com.softserve.edu.opencart.data.UserRepository;
import com.softserve.edu.opencart.pages.account.*;
import com.softserve.edu.opencart.tools.DataBaseUtils;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Epic("Functional Test")
@Feature("Register without PrivacyPolicy")
public class WithoutPrivacyPolicyTest extends ATestRunner {
    @DataProvider
    public Object[][] validValuesUser() {
        return new Object[][]{
                {UserRepository.get().validUserWithBoundaryValues1()}
        };
    }

    @Description("Run test with correct user values without PrivacyPolicy")
    @Severity(SeverityLevel.NORMAL)
    @Story("Go to RegisterPage, fill field with correct user data, but without PrivacyPolicy")
    @Test(dataProvider = "validValuesUser", description = "checkWithoutPrivacyPolicy")
    public void checkWithoutPrivacyPolicy(IUser user) {
        log.debug("checkWithoutPrivacyPolicy starts");
        PrivacyPolicyMessagePage pp = loadApplication()
                .gotoRegisterPage()
                .userWithNoPrivacyPolicy(user);
        saveImageAttach("Actualresult");

        Assert.assertEquals(pp.getActualPolicyErrorText(),
                pp.EXPECTED_WARNING_POLICY);

        DataBaseUtils db = new DataBaseUtils();
        Assert.assertFalse(db.isEmailInDb(user));

        log.debug("checkWithoutPrivacyPolicy ends");
    }
}

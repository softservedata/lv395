package com.softserve.edu.opencart.tests.register_test;

import com.softserve.edu.opencart.data.IUser;
import com.softserve.edu.opencart.data.UserRepository;
import com.softserve.edu.opencart.pages.account.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class WithoutPrivacyPolicyTest extends ATestRunner {
    @DataProvider
    public Object[][] validValuesUser() {
        return new Object[][]{
                {UserRepository.get().validUserWithBoundaryValues1()}
        };
    }

    @Test(dataProvider = "validValuesUser")
    public void checkValidRegister(IUser user) {
        PrivacyPolicyMessagePage pp = loadApplication()
                .gotoRegisterPage()
                .userWithNoPrivacyPolicy(user);
        Assert.assertEquals(pp.getActualPolicyErrorText(),
                pp.EXPECTED_WARNING_POLICY);
    }
}

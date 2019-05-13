package com.softserve.edu.opencart.tests.register_test;

import com.softserve.edu.opencart.data.IUser;
import com.softserve.edu.opencart.data.UserRepository;
import com.softserve.edu.opencart.pages.account.AccountLogoutPage;
import com.softserve.edu.opencart.pages.account.PrivacyPolicyMessageComponent;
import com.softserve.edu.opencart.pages.account.SuccessfullyRegisterPage;
import com.softserve.edu.opencart.pages.account.UnsuccessfullyRegisterPage;
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

//    @DataProvider
//    public Object[][] validMaxValuesUser() {
//        return new Object[][]{
//                {UserRepository.get().validUserWithBoundaryValues2()},
//        };
//    }

    @Test(dataProvider = "validValuesUser")
    public void checkValidRegister(IUser user) {
        //Steps
//        UnsuccessfullyRegisterPage sc = loadApplication()
//                .gotoRegisterPage()
//                .userWithNoPrivacyPolicy(user);
//        //Check
////        Assert.assertTrue(sc.getActualPolicyErrorText().
////                equals(sc.EXPECTED_WARNING_POLICY));
//        sc.checkPolicyError();
        UnsuccessfullyRegisterPage sc = loadApplication()
                .gotoRegisterPage()
                .userWithNoPrivacyPolicy(user);
//        PrivacyPolicyMessageComponent pr = new PrivacyPolicyMessageComponent();
        Assert.assertEquals(PrivacyPolicyMessageComponent.getActualPolicyErrorText(), PrivacyPolicyMessageComponent.EXPECTED_WARNING_POLICY);
    }
}

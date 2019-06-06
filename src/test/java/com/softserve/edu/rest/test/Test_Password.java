package com.softserve.edu.rest.test;

import com.softserve.edu.rest.data.*;
import com.softserve.edu.rest.engine.LoginResource;
import com.softserve.edu.rest.entity.RestParameters;
import com.softserve.edu.rest.service.AdminService;
import com.softserve.edu.rest.service.GuestService;
import com.softserve.edu.rest.service.UserService;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Epic("Bohdan's tests")
@Feature("Check & Change Password Test")

public class Test_Password {
    private AdminService adminService;

    @BeforeClass
    public void beforeClass() {
        GuestService guestService = new GuestService();
        adminService = guestService.SuccessfulAdminLogin(UserRepository.getAdmin());
    }

    @AfterMethod
    public void afterClass() {
        adminService.resetServiceToInitialState();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Test to assign 5 Lorem text paragraphs as password. \n" +
            "Expected result: password will NOT change")
    @Story("Long Lorem Password")
    @Test
    public void Password_Test_1() {
        adminService.changePassword(PasswordRepository.LONG_LOREM_PASSWORD);
        Assert.assertEquals(adminService.changePassword(PasswordRepository.LONG_LOREM_PASSWORD),"true");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Test to assign 16 digits as password. \n" +
            "Expected result: password will NOT change")
    @Story("16 digits Password")
    @Test()
    public void Password_Test_2() {
        adminService.changePassword(PasswordRepository.LONG_NUMERIC_PASSWORD);
        Assert.assertNotEquals(adminService.changePassword(PasswordRepository.LONG_NUMERIC_PASSWORD),"true");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Test to assign 66 chars as password. \n" +
            "Expected result: password will NOT change")
    @Story("66 chars Password")
    @Test()
    public void Password_Test_3() {
        adminService.changePassword(PasswordRepository.LONG_CHAR_PASSWORD_SPACELESS);
        Assert.assertEquals(adminService.changePassword(PasswordRepository.LONG_CHAR_PASSWORD_SPACELESS),"true");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Test to assign 6 chars as password. \n" +
            "Expected result: password will NOT change")
    @Story("Short Password")
    @Test()
    public void Password_Test_4() {
        adminService.changePassword(PasswordRepository.SHORT_NUMERIC_PASSWORD);
        Assert.assertNotEquals(adminService.changePassword(PasswordRepository.SHORT_NUMERIC_PASSWORD),"true");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Test to assign 26 chars as password. \n" +
            "Expected result: password will NOT change")
    @Story("26 chars Password")
    @Test()
    public void Password_Test_5() {
        adminService.changePassword(PasswordRepository.SHORT_CHAR_PASSWORD_SPACELESS);
        Assert.assertEquals(adminService.changePassword(PasswordRepository.SHORT_CHAR_PASSWORD_SPACELESS),"true");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Test to assign 18 symbolic chars as password. \n" +
            "Expected result: password will NOT change")
    @Story("Long Symbolic Password")
    @Test()
    public void Password_Test_6() {
        adminService.changePassword(PasswordRepository.LONG_SYMBOLIC_RANDOM);
        Assert.assertEquals(adminService.changePassword(PasswordRepository.LONG_SYMBOLIC_RANDOM),"true");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Test to assign 16 randomly generated symbols, letters and numbers as password. \n" +
            "Expected result: password will NOT change")
    @Story("Strong random Password (1)")
    @Test()
    public void Password_Test_7() {
        adminService.changePassword(PasswordRepository.STRONG_RANDOM_PASSWORD_1);
        Assert.assertEquals(adminService.changePassword(PasswordRepository.STRONG_RANDOM_PASSWORD_1),"true");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Test to assign 16 randomly generated symbols, letters and numbers as password. \n" +
            "Expected result: password will NOT change")
    @Story("Strong random Password (2)")
    @Test()
    public void Password_Test_8() {
        adminService.changePassword(PasswordRepository.STRONG_RANDOM_PASSWORD_2);
        Assert.assertEquals(adminService.changePassword(PasswordRepository.STRONG_RANDOM_PASSWORD_2),"true");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Test to assign 16 randomly generated symbols, letters and numbers as password. \n" +
            "Expected result: password will NOT change")
    @Story("Strong random Password(3)")
    @Test()
    public void Password_Test_9() {
        adminService.changePassword(PasswordRepository.STRONG_RANDOM_PASSWORD_3);
        Assert.assertEquals(adminService.changePassword(PasswordRepository.STRONG_RANDOM_PASSWORD_3),"true");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Test to assign 16 randomly generated symbols, letters and numbers as password. \n" +
            "Expected result: password will NOT change")
    @Story("Strong random Password (4)")
    @Test()
    public void Password_Test_10() {
        adminService.changePassword(PasswordRepository.STRONG_RANDOM_PASSWORD_4);
        Assert.assertEquals(adminService.changePassword(PasswordRepository.STRONG_RANDOM_PASSWORD_4),"true");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Test to assign 16 randomly generated symbols, letters and numbers as password. \n" +
            "Expected result: password will NOT change")
    @Story("Strong random Password (5)")
    @Test()
    public void Password_Test_11() {
        adminService.changePassword(PasswordRepository.STRONG_RANDOM_PASSWORD_5);
        Assert.assertEquals(adminService.changePassword(PasswordRepository.STRONG_RANDOM_PASSWORD_5),"true");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Test to check, if we can make SQL-Injection via password change request. \n" +
            "Expected result: error 400")
    @Story("Injection Cooldown")
    @Test()
    public void Password_Test_13() {
        adminService.changePassword(PasswordRepository.SQL_INJECTION_PASSWORD);
        Assert.assertEquals(adminService.changePassword(PasswordRepository.SQL_INJECTION_PASSWORD),"true");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Test to assign empty statement of password. \n" +
            "Expected result: password will change")
    @Story("Empty Password")
    @Test()
    public void Password_Test_14() {
        adminService.changePassword(PasswordRepository.EMPTY_PASSWORD);
        Assert.assertNotEquals(adminService.changePassword(PasswordRepository.EMPTY_PASSWORD),"true");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Test to assign 128 digits for password. \n" +
            "Expected result: password will NOT change")
    @Story("Long Password")
    @Test()
    public void Password_Test_15() {
        adminService.changePassword(PasswordRepository.LONGEST_PASSWORD);
        Assert.assertEquals(adminService.changePassword(PasswordRepository.LONGEST_PASSWORD),"true");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Test to assign Unicode symbols as password. \n" +
            "Expected result: password will NOT change")
    @Story("Unicode Password")
    @Test()
    public void Password_Test_16() {
        adminService.changePassword(PasswordRepository.UNICODE_PASSWORD);
        Assert.assertEquals(adminService.changePassword(PasswordRepository.UNICODE_PASSWORD),"true");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Test to assign EMOJI symbols code as password. \n" +
            "Expected result: password will NOT change")
    @Story("EMOJI Password")
    @Test()
    public void Password_Test_17() {
        adminService.changePassword(PasswordRepository.SPECIALCHARS_PASSWORD);
        Assert.assertEquals(adminService.changePassword(PasswordRepository.SPECIALCHARS_PASSWORD),"true");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Test to assign 1 Lorem text paragraph as password. \n" +
            "Expected result: password will NOT change")
    @Story("Short Lorem Password")
    @Test()
    public void Password_Test_18() {
        adminService.changePassword(PasswordRepository.SHORT_LOREM_PASSWORD);
        Assert.assertEquals(adminService.changePassword(PasswordRepository.SHORT_LOREM_PASSWORD),"true");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Test to assign 5 Lorem text paragraphs as as password. \n" +
            "Expected result: password will NOT change")
    @Story("Long Lorem Cooldown")
    @Test()
    public void Password_Test_19() {
        adminService.changePassword(PasswordRepository.LONG_LOREM_PASSWORD);
        Assert.assertEquals(adminService.changePassword(PasswordRepository.LONG_LOREM_PASSWORD),"true");
    }
}
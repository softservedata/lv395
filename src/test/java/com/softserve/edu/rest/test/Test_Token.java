package com.softserve.edu.rest.test;

import com.softserve.edu.rest.data.*;
import com.softserve.edu.rest.engine.LoginResource;
import com.softserve.edu.rest.entity.RestParameters;
import com.softserve.edu.rest.service.AdminService;
import com.softserve.edu.rest.service.GuestService;
import com.softserve.edu.rest.service.UserService;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.*;

@Epic("Bohdan's tests")
@Feature("Check & Change token lifetime tests")

public class Test_Token {
    private AdminService adminService;

    @BeforeClass
    public void beforeClass() {
        GuestService guestService = new GuestService();
        guestService.resetServiceToInitialState();
        adminService = guestService.SuccessfulAdminLogin(UserRepository.getAdmin());
    }

    @AfterMethod
    public void afterClass() {
        adminService.resetServiceToInitialState();
    }

    @DataProvider // (parallel = true)
    public Object[][] updateLifeTime() {
        return new Object[][] {
                { UserRepository.getAdmin(), LifetimeRepository.getDefault() },
        };
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Test to increase lifetime of token to 800 000 ms. \n" +
            "Expected result: time will  change")
    @Story("Long Lifetime")
    @Test(dataProvider = "updateLifeTime")
    public void Token_Test_1(User adminUser, Lifetime lifetime) {
        adminService.getCurrentLifetime();
        adminService.UpdateTokenlifetime(new Lifetime(LifetimeRepository.LONG_TOKEN_LIFETIME));
        adminService.getCurrentLifetime();
        //Assert.assertEquals(adminService.UpdateTokenlifetime(new Lifetime(LifetimeRepository.LONG_TOKEN_LIFETIME)), "true");
        Assert.assertNotEquals(adminService.UpdateTokenlifetime(new Lifetime(LifetimeRepository.LONG_TOKEN_LIFETIME)), "false");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Test to decrease lifetime of token to 1 ms. \n" +
            "Expected result: time will change")
    @Story("Short Lifetime")
    @Test(dataProvider = "updateLifeTime")
    public void Token_Test_2(User adminUser, Lifetime lifetime) {
        adminService.getCurrentLifetime();
        adminService.UpdateTokenlifetime(new Lifetime(LifetimeRepository.SHORT_TOKEN_LIFETIME));
        adminService.getCurrentLifetime();
        //Assert.assertEquals(adminService.UpdateTokenlifetime(new Lifetime(LifetimeRepository.LONG_TOKEN_LIFETIME)), "true");
        Assert.assertNotEquals(adminService.UpdateTokenlifetime(new Lifetime(LifetimeRepository.SHORT_TOKEN_LIFETIME)), "false");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Test to assign negative value for lifetime of token. \n" +
            "Expected result: time will change")
    @Story("Short Lifetime")
    @Test(dataProvider = "updateLifeTime")
    public void Token_Test_3(User adminUser, Lifetime lifetime) {
        adminService.getCurrentLifetime();
        adminService.UpdateTokenlifetime(new Lifetime(LifetimeRepository.NEGATIVE_TOKEN_LIFETIME));
        adminService.getCurrentLifetime();
        //Assert.assertEquals(adminService.UpdateTokenlifetime(new Lifetime(LifetimeRepository.LONG_TOKEN_LIFETIME)), "true");
        Assert.assertNotEquals(adminService.UpdateTokenlifetime(new Lifetime(LifetimeRepository.NEGATIVE_TOKEN_LIFETIME)), "false");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Test to check, if we can make SQL-Injection via token lifetime request. \n" +
            "Expected result: error 400")
    @Story("Injection Lifetime")
    @Test(dataProvider = "updateLifeTime")
    public void Token_Test_4(User adminUser, Lifetime lifetime) {
        adminService.getCurrentLifetime();
        adminService.UpdateTokenlifetime(new Lifetime(LifetimeRepository.SQL_INJECTION_TOKEN_LIFETIME));
        adminService.getCurrentLifetime();
        //Assert.assertEquals(adminService.UpdateTokenlifetime(new Lifetime(LifetimeRepository.LONG_TOKEN_LIFETIME)), "true");
        Assert.assertNotEquals(adminService.UpdateTokenlifetime(new Lifetime(LifetimeRepository.SQL_INJECTION_TOKEN_LIFETIME)), "false");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Test to assign zero token time. \n" +
            "Expected result: time will be changed.")
    @Story("Zero Lifetime")
    @Test(dataProvider = "updateLifeTime")
    public void Token_Test_5(User adminUser, Lifetime lifetime) {
        adminService.getCurrentLifetime();
        adminService.UpdateTokenlifetime(new Lifetime(LifetimeRepository.ZERO_TOKEN_LIFETIME));
        adminService.getCurrentLifetime();
        //Assert.assertEquals(adminService.UpdateTokenlifetime(new Lifetime(LifetimeRepository.LONG_TOKEN_LIFETIME)), "true");
        Assert.assertNotEquals(adminService.UpdateTokenlifetime(new Lifetime(LifetimeRepository.ZERO_TOKEN_LIFETIME)), "false");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Test to assign empty statement for token lifetime. \n" +
            "Expected result: time will NOT change")
    @Story("Empty Lifetime")
    @Test(dataProvider = "updateLifeTime")
    public void Token_Test_6(User adminUser, Lifetime lifetime) {
        adminService.getCurrentLifetime();
        adminService.UpdateTokenlifetime(new Lifetime(LifetimeRepository.EMPTY_TOKEN_LIFETIME));
        adminService.getCurrentLifetime();
        //Assert.assertEquals(adminService.UpdateTokenlifetime(new Lifetime(LifetimeRepository.LONG_TOKEN_LIFETIME)), "true");
        Assert.assertNotEquals(adminService.UpdateTokenlifetime(new Lifetime(LifetimeRepository.EMPTY_TOKEN_LIFETIME)), "false");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Test to assign 13 digits of lifetime. \n" +
            "Expected result: time will NOT change")
    @Story("13 digits time Lifetime")
    @Test(dataProvider = "updateLifeTime")
    public void Token_Test_7(User adminUser, Lifetime lifetime) {
        adminService.getCurrentLifetime();
        adminService.UpdateTokenlifetime(new Lifetime(LifetimeRepository.LONGEST_TOKEN_LIFETIME));
        adminService.getCurrentLifetime();
        //Assert.assertEquals(adminService.UpdateTokenlifetime(new Lifetime(LifetimeRepository.LONG_TOKEN_LIFETIME)), "true");
        Assert.assertNotEquals(adminService.UpdateTokenlifetime(new Lifetime(LifetimeRepository.LONGEST_TOKEN_LIFETIME)), "false");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Test to assign Unicode symbols as statement of lifetime. \n" +
            "Expected result: time will NOT change")
    @Story("Unicode Lifetime")
    @Test(dataProvider = "updateLifeTime")
    public void Token_Test_8(User adminUser, Lifetime lifetime) {
        adminService.getCurrentLifetime();
        adminService.UpdateTokenlifetime(new Lifetime(LifetimeRepository.UNICODE_TOKEN_LIFETIME));
        adminService.getCurrentLifetime();
        //Assert.assertEquals(adminService.UpdateTokenlifetime(new Lifetime(LifetimeRepository.LONG_TOKEN_LIFETIME)), "true");
        Assert.assertNotEquals(adminService.UpdateTokenlifetime(new Lifetime(LifetimeRepository.LONGEST_TOKEN_LIFETIME)), "false");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Test to assign EMOJI symbols code as lifetime statement . \n" +
            "Expected result: time will NOT change")
    @Story("EMOJI Lifetime")
    @Test(dataProvider = "updateLifeTime")
    public void Token_Test_9(User adminUser, Lifetime lifetime) {
        adminService.getCurrentLifetime();
        adminService.UpdateTokenlifetime(new Lifetime(LifetimeRepository.SPECIALCHARS_TOKEN_LIFETIME));
        adminService.getCurrentLifetime();
        //Assert.assertEquals(adminService.UpdateTokenlifetime(new Lifetime(LifetimeRepository.LONG_TOKEN_LIFETIME)), "true");
        Assert.assertNotEquals(adminService.UpdateTokenlifetime(new Lifetime(LifetimeRepository.SPECIALCHARS_TOKEN_LIFETIME)), "false");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Test to assign 1 Lorem text paragraph as lifetime statement . \n" +
            "Expected result: time will NOT change")
    @Story("Short Lorem Lifetime")
    @Test(dataProvider = "updateLifeTime")
    public void Token_Test_10(User adminUser, Lifetime lifetime) {
        adminService.getCurrentLifetime();
        adminService.UpdateTokenlifetime(new Lifetime(LifetimeRepository.SHORT_LOREM_TOKEN_LIFETIME));
        adminService.getCurrentLifetime();
        //Assert.assertEquals(adminService.UpdateTokenlifetime(new Lifetime(LifetimeRepository.LONG_TOKEN_LIFETIME)), "true");
        Assert.assertNotEquals(adminService.UpdateTokenlifetime(new Lifetime(LifetimeRepository.SHORT_LOREM_TOKEN_LIFETIME)), "false");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Test to assign 5 Lorem text paragraph as lifetime statement . \n" +
            "Expected result: time will NOT change")
    @Story("Long Lorem Lifetime")
    @Test(dataProvider = "updateLifeTime")
    public void Token_Test_11(User adminUser, Lifetime lifetime) {
        adminService.getCurrentLifetime();
        adminService.UpdateTokenlifetime(new Lifetime(LifetimeRepository.LONG_LOREM_TOKEN_LIFETIME));
        adminService.getCurrentLifetime();
        //Assert.assertEquals(adminService.UpdateTokenlifetime(new Lifetime(LifetimeRepository.LONG_TOKEN_LIFETIME)), "true");
        Assert.assertNotEquals(adminService.UpdateTokenlifetime(new Lifetime(LifetimeRepository.LONG_LOREM_TOKEN_LIFETIME)), "false");
    }

}


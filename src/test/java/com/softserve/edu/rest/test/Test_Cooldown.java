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
@Feature("Check & Change cooldown tests")

public class Test_Cooldown {
    private AdminService adminService;
    private UserService usersservice;

    @BeforeClass
    public void beforeClass() {
        GuestService guestService = new GuestService();
        adminService = guestService.SuccessfulAdminLogin(UserRepository.getAdmin());
        usersservice = guestService.SuccessfulUserLogin(UserRepository.getUser1());
    }

    @AfterMethod
    public void afterClass() {
        adminService.resetServiceToInitialState();
    }


    @DataProvider // (parallel = true)
    public Object[][] loginData() {
        return new Object[][]{
                {UserRepository.getAdmin()},
                //{UserRepository.getUser1()}
        };
    }


    @Severity(SeverityLevel.CRITICAL)
    @Description("Test for double assign default cool down time. \n" +
            "Expected result: time will be changed.")
    @Story("Default Cooldown")
    @Test(dataProvider = "loginData")
public void CoolDown_Test_1(User adminUser) {
        adminService.getCurrentCooldown();
        adminService.changeCooldown(CoolDownRepository.DEFAULT_COOLDOWN_TIME);
        adminService.getCurrentCooldown();
        Assert.assertEquals(adminService.changeCooldown(CoolDownRepository.DEFAULT_COOLDOWN_TIME),"true");

}

    @Severity(SeverityLevel.CRITICAL)
    @Description("Test for assign long cool down time. \n" +
            "Expected result: time will be changed.")
    @Story("Long Cooldown")
    @Test(dataProvider = "loginData")
    public void CoolDown_Test_2(User adminUser) {
        adminService.getCurrentCooldown();
        adminService.changeCooldown(CoolDownRepository.LONG_COOLDOWN_TIME);
        adminService.getCurrentCooldown();
        Assert.assertEquals(adminService.changeCooldown(CoolDownRepository.LONG_COOLDOWN_TIME),"true");

    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Test for assign short cool down time. \n" +
            "Expected result: time will be changed.")
    @Story("Short Cooldown")
    @Test(dataProvider = "loginData")
    public void CoolDown_Test_3(User adminUser) {
        adminService.getCurrentCooldown();
        adminService.changeCooldown(CoolDownRepository.SHORT_COOLDOWN_TIME);
        adminService.getCurrentCooldown();
        Assert.assertEquals(adminService.changeCooldown(CoolDownRepository.SHORT_COOLDOWN_TIME),"true");

    }


    @Severity(SeverityLevel.CRITICAL)
    @Description("Test to assign negative cool down time. \n" +
            "Expected result: time will be changed.")
    @Story("Negative Cooldown")
    @Test(dataProvider = "loginData")
    public void CoolDown_Test_4(User adminUser) {
        adminService.getCurrentCooldown();
        adminService.changeCooldown(CoolDownRepository.NEGATIVE_COOLDOWN_TIME);
        adminService.getCurrentCooldown();
        Assert.assertEquals(adminService.changeCooldown(CoolDownRepository.NEGATIVE_COOLDOWN_TIME),"false");

    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Test to check, if we can make SQL-Injection via cool down time request. \n" +
            "Expected result: error 400")
    @Story("Injection Cooldown")
    @Test(dataProvider = "loginData")
    public void CoolDown_Test_5(User adminUser) {
        adminService.getCurrentCooldown();
        adminService.changeCooldown(CoolDownRepository.SQL_INJECTION_COOLDOWN_TIME);
        adminService.getCurrentCooldown();
        Assert.assertEquals(adminService.changeCooldown(CoolDownRepository.SQL_INJECTION_COOLDOWN_TIME),"true");

    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Test to assign zero cool down time. \n" +
            "Expected result: time will be changed.")
    @Story("Zero Cooldown")
    @Test(dataProvider = "loginData")
    public void CoolDown_Test_6(User adminUser) {
        adminService.getCurrentCooldown();
        adminService.changeCooldown(CoolDownRepository.ZERO_COOLDOWN_TIME);
        adminService.getCurrentCooldown();
        Assert.assertEquals(adminService.changeCooldown(CoolDownRepository.ZERO_COOLDOWN_TIME),"true");

    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Test to assign 13 digits of cool down time. \n" +
            "Expected result: time will NOT change")
    @Story("13 digits time Cooldown")
    @Test(dataProvider = "loginData")
    public void CoolDown_Test_7(User adminUser) {
        adminService.getCurrentCooldown();
        adminService.changeCooldown(CoolDownRepository.LONGEST_COOLDOWN_TIME);
        adminService.getCurrentCooldown();
        Assert.assertEquals(adminService.changeCooldown(CoolDownRepository.LONGEST_COOLDOWN_TIME),"true");

    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Test to assign empty statement of cool down time. \n" +
            "Expected result: time will NOT change")
    @Story("Empty Cooldown")
    @Test(dataProvider = "loginData")
    public void CoolDown_Test_8(User adminUser) {
        adminService.getCurrentCooldown();
        adminService.changeCooldown(CoolDownRepository.EMPTY_COOLDOWN_TIME);
        adminService.getCurrentCooldown();
        Assert.assertEquals(adminService.changeCooldown(CoolDownRepository.EMPTY_COOLDOWN_TIME),"true");

    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Test to assign Unicode symbols as statement of cool down time. \n" +
            "Expected result: time will NOT change")
    @Story("Unicode Cooldown")
    @Test(dataProvider = "loginData")
    public void CoolDown_Test_9(User adminUser) {
        adminService.getCurrentCooldown();
        adminService.changeCooldown(CoolDownRepository.UNICODE_COOLDOWN_TIME);
        adminService.getCurrentCooldown();
        Assert.assertEquals(adminService.changeCooldown(CoolDownRepository.UNICODE_COOLDOWN_TIME),"true");

    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Test to assign EMOJI symbols code as statement of cool down time. \n" +
            "Expected result: time will NOT change")
    @Story("EMOJI Cooldown")
    @Test(dataProvider = "loginData")
    public void CoolDown_Test_10(User adminUser) {
        adminService.getCurrentCooldown();
        adminService.changeCooldown(CoolDownRepository.SPECIALCHARS_COOLDOWN_TIME);
        adminService.getCurrentCooldown();
        Assert.assertEquals(adminService.changeCooldown(CoolDownRepository.SPECIALCHARS_COOLDOWN_TIME),"true");

    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Test to assign 1 Lorem text paragraph as statement of cool down time. \n" +
            "Expected result: time will NOT change")
    @Story("Short Lorem Cooldown")
    @Test(dataProvider = "loginData")
    public void CoolDown_Test_11(User adminUser) {
        adminService.getCurrentCooldown();
        adminService.changeCooldown(CoolDownRepository.SHORT_LOREM_COOLDOWN_TIME);
        adminService.getCurrentCooldown();
        Assert.assertEquals(adminService.changeCooldown(CoolDownRepository.SHORT_LOREM_COOLDOWN_TIME),"true");

    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Test to assign 5 Lorem text paragraphs as statement of cool down time. \n" +
            "Expected result: time will NOT change")
    @Story("Long Lorem Cooldown")
    @Test(dataProvider = "loginData")
    public void CoolDown_Test_12(User adminUser) {
        adminService.getCurrentCooldown();
        adminService.changeCooldown(CoolDownRepository.LONG_LOREM_COOLDOWN_TIME);
        adminService.getCurrentCooldown();
        Assert.assertEquals(adminService.changeCooldown(CoolDownRepository.LONG_LOREM_COOLDOWN_TIME), "true");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Test for assign short cool down time. \n" +
            "Expected result: time will be changed.")
    @Story("Short Cooldown")
    @Test(dataProvider = "loginData")
        public void CoolDown_Test_13(User adminUser) {
            adminService.getCurrentCooldown();
            adminService.changeCooldown(CoolDownRepository.SHORT_COOLDOWN_TIME);
            adminService.getCurrentCooldown();
            Assert.assertEquals(adminService.changeCooldown(CoolDownRepository.SHORT_COOLDOWN_TIME),"true");

        }
}
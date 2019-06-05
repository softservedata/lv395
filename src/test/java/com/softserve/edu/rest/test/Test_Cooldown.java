package com.softserve.edu.rest.test;

import com.softserve.edu.rest.data.*;
import com.softserve.edu.rest.engine.LoginResource;
import com.softserve.edu.rest.entity.RestParameters;
import com.softserve.edu.rest.service.AdminService;
import com.softserve.edu.rest.service.GuestService;
import com.softserve.edu.rest.service.UserService;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;
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


    @DataProvider // (parallel = true)
    public Object[][] loginData() {
        return new Object[][]{
                {UserRepository.getAdmin()},
                {UserRepository.getUser1()}
        };
    }

//    @DataProvider // (parallel = true)
//    public Object[][] cooldownRepo() {
//        return new Object[][]{
//                {CoolDownRepository.getDefault()},
//                {CoolDownRepository.GetLongTime()},
//                {CoolDownRepository.GetShortTime()},
//                {CoolDownRepository.GetNegativeTime()},
//                {CoolDownRepository.GetSQLITime()},
//                {CoolDownRepository.GetZeroTime()},
//                {CoolDownRepository.GetLongestTime()},
//                {CoolDownRepository.GetEmptyTime()},
//                {CoolDownRepository.GetUnicodeTime()},
//                {CoolDownRepository.GetSmileyTime()},
//                {CoolDownRepository.GetSLoremTime()},
//                {CoolDownRepository.GetLLoremTime()}
//        };
//    }



    @Test(dataProvider = "loginData")
public void CoolDown_Test_1(User adminUser) {
        adminService.getCurrentCooldown();
        adminService.changeCooldown(CoolDownRepository.DEFAULT_COOLDOWN_TIME);
        adminService.getCurrentCooldown();
        Assert.assertEquals(adminService.changeCooldown(CoolDownRepository.DEFAULT_COOLDOWN_TIME),"true");

}

    @Test(dataProvider = "loginData")
    public void CoolDown_Test_2(User adminUser) {
        adminService.getCurrentCooldown();
        adminService.changeCooldown(CoolDownRepository.LONG_COOLDOWN_TIME);
        adminService.getCurrentCooldown();
        Assert.assertEquals(adminService.changeCooldown(CoolDownRepository.LONG_COOLDOWN_TIME),"true");

    }

    @Test(dataProvider = "loginData")
    public void CoolDown_Test_3(User adminUser) {
        adminService.getCurrentCooldown();
        adminService.changeCooldown(CoolDownRepository.SHORT_COOLDOWN_TIME);
        adminService.getCurrentCooldown();
        Assert.assertEquals(adminService.changeCooldown(CoolDownRepository.SHORT_COOLDOWN_TIME),"true");

    }

    @Test(dataProvider = "loginData")
    public void CoolDown_Test_4(User adminUser) {
        adminService.getCurrentCooldown();
        adminService.changeCooldown(CoolDownRepository.NEGATIVE_COOLDOWN_TIME);
        adminService.getCurrentCooldown();
        Assert.assertEquals(adminService.changeCooldown(CoolDownRepository.NEGATIVE_COOLDOWN_TIME),"true");

    }

    @Test(dataProvider = "loginData")
    public void CoolDown_Test_5(User adminUser) {
        adminService.getCurrentCooldown();
        adminService.changeCooldown(CoolDownRepository.SQL_INJECTION_COOLDOWN_TIME);
        adminService.getCurrentCooldown();
        Assert.assertEquals(adminService.changeCooldown(CoolDownRepository.SQL_INJECTION_COOLDOWN_TIME),"true");

    }

    @Test(dataProvider = "loginData")
    public void CoolDown_Test_6(User adminUser) {
        adminService.getCurrentCooldown();
        adminService.changeCooldown(CoolDownRepository.ZERO_COOLDOWN_TIME);
        adminService.getCurrentCooldown();
        Assert.assertEquals(adminService.changeCooldown(CoolDownRepository.ZERO_COOLDOWN_TIME),"true");

    }

    @Test(dataProvider = "loginData")
    public void CoolDown_Test_7(User adminUser) {
        adminService.getCurrentCooldown();
        adminService.changeCooldown(CoolDownRepository.LONGEST_COOLDOWN_TIME);
        adminService.getCurrentCooldown();
        Assert.assertEquals(adminService.changeCooldown(CoolDownRepository.LONGEST_COOLDOWN_TIME),"true");

    }

    @Test(dataProvider = "loginData")
    public void CoolDown_Test_8(User adminUser) {
        adminService.getCurrentCooldown();
        adminService.changeCooldown(CoolDownRepository.EMPTY_COOLDOWN_TIME);
        adminService.getCurrentCooldown();
        Assert.assertEquals(adminService.changeCooldown(CoolDownRepository.EMPTY_COOLDOWN_TIME),"true");

    }

    @Test(dataProvider = "loginData")
    public void CoolDown_Test_9(User adminUser) {
        adminService.getCurrentCooldown();
        adminService.changeCooldown(CoolDownRepository.UNICODE_COOLDOWN_TIME);
        adminService.getCurrentCooldown();
        Assert.assertEquals(adminService.changeCooldown(CoolDownRepository.UNICODE_COOLDOWN_TIME),"true");

    }

    @Test(dataProvider = "loginData")
    public void CoolDown_Test_10(User adminUser) {
        adminService.getCurrentCooldown();
        adminService.changeCooldown(CoolDownRepository.SPECIALCHARS_COOLDOWN_TIME);
        adminService.getCurrentCooldown();
        Assert.assertEquals(adminService.changeCooldown(CoolDownRepository.SPECIALCHARS_COOLDOWN_TIME),"true");

    }

    @Test(dataProvider = "loginData")
    public void CoolDown_Test_11(User adminUser) {
        adminService.getCurrentCooldown();
        adminService.changeCooldown(CoolDownRepository.SHORT_LOREM_COOLDOWN_TIME);
        adminService.getCurrentCooldown();
        Assert.assertEquals(adminService.changeCooldown(CoolDownRepository.SHORT_LOREM_COOLDOWN_TIME),"true");

    }

    @Test(dataProvider = "loginData")
    public void CoolDown_Test_12(User adminUser) {
        adminService.getCurrentCooldown();
        adminService.changeCooldown(CoolDownRepository.LONG_LOREM_COOLDOWN_TIME);
        adminService.getCurrentCooldown();
        Assert.assertEquals(adminService.changeCooldown(CoolDownRepository.LONG_LOREM_COOLDOWN_TIME), "true");

    }
        @Test(dataProvider = "loginData")
        public void CoolDown_Test_13(User adminUser) {
            usersservice.getCurrentCooldown();
            usersservice.changeCooldown(CoolDownRepository.SHORT_COOLDOWN_TIME);
            usersservice.getCurrentCooldown();
            Assert.assertEquals(usersservice.changeCooldown(CoolDownRepository.SHORT_COOLDOWN_TIME),"true");

        }
}
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
        guestService.resetServiceToInitialState();
        adminService = guestService.SuccessfulAdminLogin(UserRepository.getAdmin());
    }

    @AfterMethod
    public void afterClass() {
        adminService.resetServiceToInitialState();
    }

    @DataProvider // (parallel = true)
    public Object[][] loginData() {
        return new Object[][]{
                {UserRepository.getAdmin()}
        };
    }

    @Test(dataProvider = "loginData")
    public void Password_Test_1(User adminUser) {
        adminService.ChangeCurrentPassword(PasswordRepository.LONG_NUMERIC_PASSWORD);

        //System.out.println(adminUser.getPassword());
        //Assert.assertEquals(adminService.changePassword(PasswordRepository.STRONG_RANDOM_PASSWORD_1),"true");
    }

}
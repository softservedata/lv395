package com.softserve.edu.rest.test;

import com.softserve.edu.rest.data.*;
import com.softserve.edu.rest.engine.LoginResource;
import com.softserve.edu.rest.entity.RestParameters;
import com.softserve.edu.rest.service.AdminService;
import com.softserve.edu.rest.service.GuestService;
import com.softserve.edu.rest.service.UserService;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class loginTest {
    @DataProvider // (parallel = true)
    public Object[][] loginData() {
        return new Object[][] {
                { UserRepository.getAdmin()},
        };
    }

    @Test(dataProvider = "loginData")
    public void checkLoginReport(User adminUser) {
        // Steps
        GuestService guestService = new GuestService();
        UserService userService = guestService
                .SuccessfulUserLogin(adminUser);
        //check token
        Assert.assertTrue(adminUser.getToken() != null);
        //check user name
        Assert.assertEquals(userService.getUserName(),adminUser.getName());



        //Steps
        userService.LogoutUser();
        //check user logout
        Assert.assertEquals(adminUser.getToken(),"");

    }

    @Test(dataProvider = "loginData",
            dependsOnMethods = "checkLoginReport")
    public void logout(User adminUser) {
    }
}

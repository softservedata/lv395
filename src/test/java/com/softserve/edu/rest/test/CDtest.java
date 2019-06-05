package com.softserve.edu.rest.test;

import com.softserve.edu.rest.data.*;
import com.softserve.edu.rest.engine.LoginResource;
import com.softserve.edu.rest.entity.RestParameters;
import com.softserve.edu.rest.service.AdminService;
import com.softserve.edu.rest.service.GuestService;
import com.softserve.edu.rest.service.UserService;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CDtest {
    private AdminService adminService;

    @BeforeClass
    public void beforeClass() {
        GuestService guestService = new GuestService();
        adminService = guestService.SuccessfulAdminLogin(UserRepository.getAdmin());
    }


    @DataProvider // (parallel = true)
    public Object[][] loginData() {
        return new Object[][]{
                {UserRepository.getAdmin()},
        };
    }

    @Test(dataProvider = "loginData")
    public void lifetime(User adminUser) {
        adminService.getCurrentCooldown();
//        adminService.changeCooldown();
        adminService.getCurrentCooldown();
//        Assert.assertEquals(adminService.changeCooldown(),"true");
//        GuestService guestService = new GuestService();
//        AdminService userService = guestService
//                .SuccessfulAdminLogin(adminUser);
//        Assert.assertTrue(adminUser.getToken() != null);
//        guestService.getCurrentCooldown();
//        //userService.changePassword("3333");
//        userService.changeCooldown("9999");
//        guestService.getCurrentCooldown();


        //Lifetime testLifeTime = new Lifetime("8888") ;
        //userService.UpdateTokenlifetime(Lifetime, testLifeTime);
    }

//    @Test
//    public void checkCooldown(User adminUser) {
//
//    }
}
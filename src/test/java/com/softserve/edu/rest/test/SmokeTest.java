package com.softserve.edu.rest.test;

import com.softserve.edu.rest.data.User;
import com.softserve.edu.rest.data.UserRepository;
import com.softserve.edu.rest.service.AdminService;
import com.softserve.edu.rest.service.GuestService;
import com.softserve.edu.rest.service.UserService;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SmokeTest {
    @Test()
    public void loginAdmin() {
        //Steps
        GuestService guestService=new GuestService();
        AdminService adminService = guestService.SuccessfulAdminLogin(UserRepository.getAdmin());
        //Check
        Assert.assertTrue(adminService.getUserName().contains(UserRepository.getAdmin().getName()));

    }
    @Test(dependsOnMethods = "loginAdmin")
    public void logoutAdmin(){
        GuestService guestService=new GuestService();
        AdminService adminService = guestService.SuccessfulAdminLogin(UserRepository.getAdmin());
        //Step
        adminService.LogoutUser();
        //Check
        Assert.assertEquals(UserRepository.getAdmin().getToken(),null);
    }
}

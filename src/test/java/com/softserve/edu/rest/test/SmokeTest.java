package com.softserve.edu.rest.test;

import com.softserve.edu.rest.data.User;
import com.softserve.edu.rest.data.UserRepository;
import com.softserve.edu.rest.service.AdminService;
import com.softserve.edu.rest.service.GuestService;
import com.softserve.edu.rest.service.UserService;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
@Epic("Ira`s tests")
@Feature("Smoke Test")
public class SmokeTest {
    /**
     * Here we have smoke test for login
     * if this test won`t be worked, then there is no reasons
     * to continue testing
     */
    @Test()
    @Severity(SeverityLevel.BLOCKER)
    @Description("Here we have smoke test for login" +
            "if this test won`t be worked, then there is no reasons " +
            "to continue testing  )")
    public void loginAndLogoutAdmin() {
        //Steps
        GuestService guestService=new GuestService();
        AdminService adminService = guestService.SuccessfulAdminLogin(UserRepository.getAdmin());
        //Check
        Assert.assertTrue(adminService.getUserName().contains(UserRepository.getAdmin().getName()));
        //Step
        adminService.LogoutUser();
        //Check
        Assert.assertEquals(UserRepository.getAdmin().getToken(),null);

    }

}

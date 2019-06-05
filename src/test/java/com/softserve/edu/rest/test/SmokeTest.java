package com.softserve.edu.rest.test;

import com.softserve.edu.rest.data.UserRepository;
import com.softserve.edu.rest.service.AdminService;
import com.softserve.edu.rest.service.GuestService;
import io.qameta.allure.*;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Ira`s tests")
@Feature("Smoke Test")
public class SmokeTest {
    private final Logger log = Logger.getLogger(this.getClass());

    /**
     * Here we have smoke test for login
     * if this test won`t work, then there is no reasons
     * to continue testing
     */
    @Test()
    @Severity(SeverityLevel.BLOCKER)
    @Description("Here we have smoke test for login" +
            "if this test won`t be worked, then there is no reasons " +
            "to continue testing  )")
    public void loginAndLogoutAdminTest() {
        log.debug("loginAndLogoutAdminTest started!");
        //Steps
        GuestService guestService = new GuestService();
        AdminService adminService = guestService.SuccessfulAdminLogin(UserRepository.getAdmin());
        //Check
        Assert.assertTrue(adminService.getUserName().contains(UserRepository.getAdmin().getName()));
        //Step
        adminService.logoutUser();
        //Check
        Assert.assertNull(UserRepository.getAdmin().getToken());
        log.debug("loginAndLogoutAdminTest finished!");
    }

}

package com.softserve.edu.rest.test.locking;

import com.softserve.edu.rest.data.UserRepository;
import com.softserve.edu.rest.service.AdminService;
import com.softserve.edu.rest.service.GuestService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

public abstract class ATestRunner {
    protected AdminService adminService;

    @BeforeClass
    public void beforeClass() {
        GuestService guestService = new GuestService();
        adminService = guestService.SuccessfulAdminLogin(UserRepository.getAdmin());
    }

    @AfterMethod
    public void afterMethod() {
        adminService.unlockAllUsers();
    }
}

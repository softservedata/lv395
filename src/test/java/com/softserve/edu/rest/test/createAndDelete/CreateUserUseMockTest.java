package com.softserve.edu.rest.test.createAndDelete;

import com.softserve.edu.rest.data.User;
import com.softserve.edu.rest.data.UserRepository;
import com.softserve.edu.rest.service.AdminService;
import com.softserve.edu.rest.service.GuestService;
import com.softserve.edu.rest.service.UserService;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;

public class CreateUserUseMockTest {
    private final Logger log = Logger.getLogger(this.getClass());
    /**
     * Test for creating new user
     * using mock for test
     */
    @Test()
    @Severity(SeverityLevel.CRITICAL)
    @Description(" Test for creating new \n" +
            "using mock for test")
    @Story("Create user")
    public void createUserWithMockTest() {
        log.debug("createUserWithMockTest started!");
        User admin=UserRepository.getAdmin();
        GuestService guestService=new GuestService();
        AdminService adminService = guestService.SuccessfulAdminLogin(admin);
        User newUser = UserRepository.newUserWithoutAdminRights();

        AdminService spy = spy(adminService);
        when(spy.isUserCreated(newUser)).thenReturn(true);
        Boolean userIsCreated = adminService.createUser(newUser);
        //Check user is created
        Assert.assertTrue(userIsCreated);
        //try to login new user
        UserService userService = guestService.SuccessfulUserLogin(newUser);
        Assert.assertTrue(spy.isUserCreated(newUser));
        userService.logoutUser();
        log.debug("createUserWithMockTest finished!");
    }
}

package com.softserve.edu.rest.test.createAndDelete;

import com.softserve.edu.rest.data.User;
import com.softserve.edu.rest.data.UserRepository;
import com.softserve.edu.rest.service.AdminService;
import com.softserve.edu.rest.service.GuestService;
import io.qameta.allure.*;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Epic("Ira`s tests")
@Feature("Create and delete Test")
public class CreateAndDeleteUserTest {

    private GuestService guestService;
    private AdminService adminService;
    private final Logger log = Logger.getLogger(this.getClass());

    @BeforeClass
    public void beforeClass() {
        guestService = new GuestService();
        adminService = guestService.SuccessfulAdminLogin(UserRepository.getAdmin());
    }

    @AfterClass
    public void afterClass() {
        adminService.resetServiceToInitialState();
    }

    /**
     * 1.Create user with admin rights.
     * 2.Delete this user.
     * 3.Change user`s admin rights.
     * 4.Create user, but this time without admin rights.
     * Expected result: user will be created without admin rights.
     */
    @Severity(SeverityLevel.CRITICAL)
    @Description("1.Create user with admin rights.\n" +
            " 2.Delete this user.\n" +
            " 3.Change user`s admin rights.\n" +
            " 4.Create user, but this time without admin rights.\n" +
            " Expected result: user will be created without admin rights.")
    @Story("Create user")
    @Test
    public void createAdminThenCreateUserTest() {
        log.debug("createAdminThenCreateUserTest started");

        //Steps
        User user = UserRepository.newUserWithAdminRights();
        adminService.removeUser(user.getName());
        //create admin user
        adminService.createUser(user);
        //check
        Assert.assertTrue(adminService.isUserCreated(user));
        log.debug("user created");
        Assert.assertTrue(adminService.isUserAdmin(user));
        log.debug("user is admin");
        //delete user
        adminService.removeUser(user.getName());
        Assert.assertFalse(adminService.isUserCreated(user));
        log.debug("user is deleted");
        //change user`s admin rights
        user.setAdminRights(false);
        //create user without admin rights
        adminService.createUser(user);
        //check, that user is without admin rights
        Assert.assertTrue(adminService.isUserCreated(user));
        log.debug("user is created");
        Assert.assertFalse(adminService.isUserAdmin(user));
        log.debug("user is admin");
        log.debug("createAdminThenCreateUserTest finished!");
    }

    /**
     * 1.Create user without admin rights.
     * 2.Delete this user.
     * 3.Change user`s admin rights.
     * 4.Create user, but this time with admin rights.
     * Expected result: user will be created with admin rights.
     */
    @Severity(SeverityLevel.CRITICAL)
    @Description("1.Create user without admin rights.\n" +
            " 2.Delete this user.\n" +
            " 3.Change user`s admin rights.\n" +
            " 4.Create user, but this time with admin rights.\n" +
            " Expected result: user will be created with admin rights.")
    @Story("Create user")
    @Test
    public void createUserThenCreateAdminTest() {

        log.debug("createUserThenCreateAdminTest started!");
        //Steps
        User user = UserRepository.newUserWithoutAdminRights();
        //create user without admin rights
        adminService.removeUser(user.getName());
        adminService.createUser(user);
        //check that user is with admin rights
        Assert.assertTrue(adminService.isUserCreated(user));
        log.debug("user created");
        Assert.assertFalse(adminService.isUserAdmin(user));
        log.debug("user without admin rights");
        //delete user
        adminService.removeUser(user.getName());
        Assert.assertFalse(adminService.isUserCreated(user));
        log.debug("user deleted");
        //change user`s admin rights
        user.setAdminRights(true);
        //create user with admin rights
        adminService.createUser(user);
        //check, that user is with admin rights
        Assert.assertTrue(adminService.isUserCreated(user));
        Assert.assertTrue(adminService.isUserAdmin(user));
        log.debug("user is admin");
        log.debug("createUserThenCreateAdminTest finished!");
    }


}

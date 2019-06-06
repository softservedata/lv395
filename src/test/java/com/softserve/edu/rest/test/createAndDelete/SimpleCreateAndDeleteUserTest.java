package com.softserve.edu.rest.test.createAndDelete;

import com.softserve.edu.rest.data.User;
import com.softserve.edu.rest.data.UserRepository;
import com.softserve.edu.rest.service.AdminService;
import com.softserve.edu.rest.service.GuestService;
import com.softserve.edu.rest.service.UserService;
import io.qameta.allure.*;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;

import static org.mockito.Mockito.*;

@Epic("Ira`s tests")
@Feature("Create and delete Test")
public class SimpleCreateAndDeleteUserTest {
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
     * This data is for two tests: "createUserTest",
     * "deleteUserTest"
     *
     * @return new user
     */
    @DataProvider
    public Object[][] newUserData() {
        return new Object[][]{
                {UserRepository.newUserWithoutAdminRights()}
        };
    }

    /**
     * Test for creating new user without admin rights
     * Expected result: user will be created
     */
    @Severity(SeverityLevel.CRITICAL)
    @Description(" Test for creating new user without admin rights \n" +
            "Expected result: user will be created")
    @Story("Create user")
    @Test(dataProvider = "newUserData")
    public void createUserTest(User newUser) {
        log.debug("createUserTest started!");
        adminService.createUser(newUser);
        //Check user is created
        Assert.assertTrue(adminService.isUserCreated(newUser));
        //try to login new user
        UserService userService = guestService.SuccessfulUserLogin(newUser);
        Assert.assertTrue(adminService.isUserLogged(newUser));
        //check that this is not admin
        Assert.assertFalse(adminService.getAllAdmins().contains(newUser.getName()));
        userService.logoutUser();
        Assert.assertFalse(adminService.isUserLogged(newUser));
        log.debug("createUserTest finished!");
    }

    /**
     * This data is for two tests: "createAdminTest",
     * "deleteAdminTest"
     *
     * @return new user
     */
    @DataProvider
    public Object[][] newAdminData() {
        return new Object[][]{
                {UserRepository.newUserWithAdminRights()}
        };
    }


    /**
     * Test for creating new user with admin rights
     * Expected result: user will be created
     */
    @Test(dataProvider = "newAdminData")
    @Severity(SeverityLevel.CRITICAL)
    @Description(" Test for creating new user with admin rights \n" +
            "Expected result: user will be created")
    @Story("Create user")
    public void createAdminTest(User newAdmin) {
        log.debug("createAdminTest started!");
        adminService.createUser(newAdmin);
        //Check user is created
        Assert.assertTrue(adminService.isUserCreated(newAdmin));
        Assert.assertTrue(adminService.getAllAdmins().contains(newAdmin.getName()));
        //try to login new user
        AdminService adminService1 = guestService.SuccessfulAdminLogin(newAdmin);
        //check, that this is really admin
        Assert.assertTrue(adminService.isUserLogged(newAdmin));
        adminService1.logoutUser();
        Assert.assertFalse(adminService.isUserLogged(newAdmin));
        log.debug("createAdminTest finished!");
    }

    /**
     * Test for creating existing user
     * Expected result: user won`t be created
     */
    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description(" Test for creating existing user \n" +
            "Expected result: user won`t be created")
    @Story("Create user")
    public void createExistingUserTest() {
        log.debug("createExistingUserTest started!");
        User user = UserRepository.getUser1();
        Boolean userIsCreated = adminService.createUser(user);
        //Check user won`t be created
        Assert.assertFalse(userIsCreated);
        log.debug("createExistingUserTest finished!");

    }



    /**
     * Delete user.
     * Here we are deleting new user, that has been
     * created in test "createUserTest", this is why
     * this test depends on method "createUserTest"
     * Expected result: user will be deleted.
     */
    @Test(dataProvider = "newUserData", dependsOnMethods = "createUserTest")
    @Severity(SeverityLevel.CRITICAL)
    @Description(" Delete user without admin rights \n" +
            "Expected result: user will be deleted.")
    @Story("Delete user")
    public void deleteUserTest(User user) {
        log.debug("deleteUserTest started!");
        adminService.removeUser(user.getName());
        Assert.assertFalse(adminService.getAllUsers().contains(user.getName()));
        log.debug("deleteUserTest finished!");
    }

    /**
     * Delete admin.
     * Here we are deleting new user, that has been
     * created in test "createAdminTest", this is why
     * this test depends on method "createAdminTest"
     * Expected result: addmin will be deleted.
     */
    @Test(dataProvider = "newAdminData", dependsOnMethods = "createAdminTest")
    @Severity(SeverityLevel.CRITICAL)
    @Description(" Delete user with admin rights \n" +
            "Expected result: user will be deleted.")
    @Story("Delete user")
    public void deleteAdminTest(User admin) {
        log.debug("deleteAdminTest started!");
        adminService.removeUser(admin.getName());
        Assert.assertFalse(adminService.getAllUsers().contains(admin.getName()));
        log.debug("deleteAdminTest finished!");
    }

    /**
     * Delete user that doesn`t exist.
     * Expected result: user will be deleted.
     */
    @Test()
    @Severity(SeverityLevel.CRITICAL)
    @Description(" Delete not existing user \n" +
            "Expected result: user will be deleted.")
    @Story("Delete user")
    public void deleteNotExistingUserTest() {
        log.debug("deleteNotExistingUserTest started!");
        User notExistingUser = UserRepository.notExistingUser();
        Boolean isUserDeleted = adminService.removeUser(notExistingUser.getName());
        Assert.assertFalse(isUserDeleted);
        log.debug("deleteNotExistingUserTest finished!");
    }




}

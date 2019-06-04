package com.softserve.edu.rest.test;

import com.softserve.edu.rest.data.User;
import com.softserve.edu.rest.data.UserRepository;
import com.softserve.edu.rest.service.AdminService;
import com.softserve.edu.rest.service.GuestService;
import com.softserve.edu.rest.service.UserService;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;
@Epic("Ira`s tests")
@Feature("Create and delete Test")
public class CreateAndDeleteUserTest {
    private GuestService guestService;
    private AdminService adminService;

    @BeforeMethod
    public void beforeClass() {
        guestService = new GuestService();
        adminService = guestService.SuccessfulAdminLogin(UserRepository.getAdmin());
    }


    /**
     * Test for creating new user without admin rights
     * Expected result: user will be created
     */
    @Test()
    @Severity(SeverityLevel.CRITICAL)
    @Description(" Test for creating new user without admin rights \n" +
            "Expected result: user will be created")
    @Story("Create user")
    public void createUserTest() {
        User newUser = UserRepository.newUserWithoutAdminRihts();
        adminService.createUser(newUser);
        //Check user is created
        Assert.assertTrue(adminService.getAllUsers().contains(newUser.getName()));
        //try to login new user
        UserService userService = guestService.SuccessfulUserLogin(newUser);
        Assert.assertTrue(adminService.isUserLogged(newUser));
        //check that this is not admin
        Assert.assertFalse(adminService.getAllAdmins().contains(newUser.getName()));
        userService.logoutUser();
        Assert.assertFalse(adminService.isUserLogged(newUser));

    }

    /**
     * Test for creating new user with admin rights
     * Expected result: user will be created
     */
    @Test()
    @Severity(SeverityLevel.CRITICAL)
    @Description(" Test for creating new user with admin rights \n" +
            "Expected result: user will be created")
    @Story("Create user")
    public void createAdminTest() {
        User newAdmin = UserRepository.newUserWithAdminRihts();
        adminService.createUser(newAdmin);
        //Check user is created
        Assert.assertTrue(adminService.getAllAdmins().contains(newAdmin.getName()));
        //try to login new user
        AdminService adminService1 = guestService.SuccessfulAdminLogin(newAdmin);
        //check, that this is really admin
        Assert.assertTrue(adminService.isUserLogged(newAdmin));
        adminService1.logoutUser();
        Assert.assertFalse(adminService.isUserLogged(newAdmin));
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
        User user = UserRepository.getUser1();
        Boolean userIsCreated=adminService.createUser(user);
        //Check user won`t be created
        Assert.assertFalse(userIsCreated);

    }

    @DataProvider
    public Object[][] usersWeWantToRemove() {
        return new Object[][]{
                {UserRepository.newUserWithoutAdminRihts()},
                {UserRepository.newUserWithAdminRihts()}
        };
    }

    /**
     * Delete user.
     * Expected result: user will be deleted.
     */
    @Test(dataProvider = "usersWeWantToRemove")
    @Severity(SeverityLevel.CRITICAL)
    @Description(" Delete user without admin rights \n" +
            "Expected result: user will be deleted.")
    @Story("Delete user")
    public void deleteUserTest(User userWeWantToRemove) {
        adminService.removeUser(userWeWantToRemove.getName());
        Assert.assertFalse(adminService.getAllUsers().contains(userWeWantToRemove.getName()));
    }

    /**
     * Delete user that doesn`t exist.
     * Expected result: user will be deleted.
     */
    @Test()
    @Severity(SeverityLevel.CRITICAL)
    @Description(" Delete user with admin rights \n" +
            "Expected result: user will be deleted.")
    @Story("Delete user")
    public void deleteNotExistingUserTest() {
        User notExistingUser = UserRepository.notExistingUser();
        Boolean isUserDeleted = adminService.removeUser(notExistingUser.getName());
        Assert.assertTrue(isUserDeleted);
    }

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
        AdminService adminService1 = guestService.SuccessfulAdminLogin(UserRepository.getAdmin());
        User newUser = UserRepository.newUserWithoutAdminRihts();

        AdminService spy = spy(adminService1);
        Boolean createUser = adminService1.createUser(newUser);
        when(spy.isUserCreated(newUser)).thenReturn(true);
        Boolean userIsCreated = adminService1.createUser(newUser);
        //Check user is created
        Assert.assertTrue(createUser);
        Assert.assertTrue(userIsCreated);

        //try to login new user
        UserService userService = guestService.SuccessfulUserLogin(newUser);
        Assert.assertTrue(adminService1.isUserLogged(newUser));
        userService.logoutUser();

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
    public void createAdminThenCreateUserTest(){
        //Steps
        User user=UserRepository.newUserWithAdminRihts();
        //create admin user
        adminService.createUser(user);
        //check
        Assert.assertTrue(adminService.isUserCreated(user));
        Assert.assertTrue(adminService.isUserAdmin(user));
        //delete user
        adminService.removeUser(user.getName());
        Assert.assertFalse(adminService.isUserCreated(user));
        //change user`s admin rights
        user.setAdminRights(false);
        //create user without admin rights
        adminService.createUser(user);
        //check, that user is without admin rights
        Assert.assertTrue(adminService.isUserCreated(user));
        Assert.assertFalse(adminService.isUserAdmin(user));

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
    public void createUserThenCreateAdminTest(){
        //Steps
        User user=UserRepository.newUserWithoutAdminRihts();
        //create user without admin rights
        adminService.createUser(user);
        //check that user is with admin rights
        Assert.assertTrue(adminService.isUserCreated(user));
        Assert.assertFalse(adminService.isUserAdmin(user));
        //delete user
        adminService.removeUser(user.getName());
        Assert.assertFalse(adminService.isUserCreated(user));
        //change user`s admin rights
        user.setAdminRights(true);
        //create user with admin rights
        adminService.createUser(user);
        //check, that user is with admin rights
        Assert.assertTrue(adminService.isUserCreated(user));
        Assert.assertTrue(adminService.isUserAdmin(user));

    }

}

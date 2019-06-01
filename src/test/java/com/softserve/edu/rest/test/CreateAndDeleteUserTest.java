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
    GuestService guestService;
    AdminService adminService;

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
        userService.LogoutUser();
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
        adminService1.LogoutUser();
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
        //Check user won`t be created
        Assert.assertFalse(adminService.getAllUsers().contains(user.getName()));

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
        Boolean userIsDeleted = adminService.removeUser(notExistingUser.getName());
        Assert.assertTrue(userIsDeleted);
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
        userService.LogoutUser();

    }


}

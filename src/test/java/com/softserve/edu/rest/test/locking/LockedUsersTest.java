package com.softserve.edu.rest.test.locking;


import com.softserve.edu.rest.data.User;
import com.softserve.edu.rest.data.UserRepository;
import com.softserve.edu.rest.service.AdminService;
import com.softserve.edu.rest.service.GuestService;
import com.softserve.edu.rest.service.UserService;
import org.testng.Assert;
import org.testng.annotations.*;

public class LockedUsersTest extends ATestRunner {

    @DataProvider
    public Object[][] wrongPasswordData() {
        User newAdmin = UserRepository.newUserWithAdminRihts();
        adminService.createUser(newAdmin);        ;
        return new Object[][]{
                {UserRepository.getUserWrongPassword(), UserRepository.getUser1() }, //user
                {UserRepository.getAdminWrongPassword(), UserRepository.newUserWithAdminRihts()} //admin
        };
    }

    @Test (dataProvider = "wrongPasswordData")
    public void loginWithWrongPasswordTest(User userWrongPW, User userCorrectPW) {
        // Steps
        GuestService guestService = new GuestService();
        //UserService userService = guestService.SuccessfulUserLogin(userWrongPW);
        int i;
        for (i = 0; i < 3; i++) {
            System.out.println("Attempt " + (i + 1) + " to login with wrong password");
            Assert.assertFalse(adminService.isUserLocked(userWrongPW));
            guestService.SuccessfulUserLogin(userWrongPW);
        }

        //Check list with locked users
        Assert.assertTrue(adminService.isUserLocked(userWrongPW));
        System.out.println("Attempt " + (i + 1) +  " to login with wrong password");
        guestService.SuccessfulUserLogin(userWrongPW);
        System.out.println("Attempt to login with correct password");
        guestService.SuccessfulUserLogin(userCorrectPW);
    }

    @Test (dataProvider = "wrongPasswordData")
    public void successfulLoginAfterUnsuccessfulTest(User userWrongPW, User userCorrectPW) {
        // Steps
        GuestService guestService = new GuestService();
        for (int i = 0; i < 2; ){
            System.out.println("Attempt " + i++ + " to login with wrong password");
            Assert.assertFalse(adminService.isUserLocked(userWrongPW));
            guestService.SuccessfulUserLogin(userWrongPW);
        }
        //Check list with locked users
        Assert.assertFalse(adminService.isUserLocked(userWrongPW));
        System.out.println("Attempt to login with correct password");
        UserService userService = guestService.SuccessfulUserLogin(userCorrectPW);
        System.out.println("Getting all logged users");
        Assert.assertTrue(adminService.isUserLogged(userCorrectPW));
        userService.LogoutUser();
    }


}

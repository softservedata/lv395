package com.softserve.edu.rest.test;

import com.softserve.edu.rest.data.*;
import com.softserve.edu.rest.service.AdminService;
import com.softserve.edu.rest.service.GuestService;
import com.softserve.edu.rest.service.UserService;
import com.softserve.edu.rest.tools.LoginUserThread;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class loginLogoutTest {
    private AdminService adminService;

    @BeforeClass
    public void beforeClass() {
        GuestService guestService = new GuestService();
        adminService = guestService.SuccessfulAdminLogin(UserRepository.getAdmin());
    }

    @AfterClass
    public void afterClass() {
        adminService.LogoutUser();
    }


    @DataProvider
    public Object[][] correctLoginData() {
        return new Object[][]{
                {UserRepository.getUser1()},
        };
    }

    @Test(dataProvider = "correctLoginData")
    public void loginPositiveTest(User user) {
        //Steps
        GuestService guestService = new GuestService();
        UserService userService = guestService.SuccessfulUserLogin(user);
        //Check
        Assert.assertTrue(adminService.isUserLogged(user));
        //Step
        userService.LogoutUser();
    }

    @DataProvider
    public Object[][] wrongLoginData() {
        return new Object[][]{
                {UserRepository.getUserWrongLogin()},
                {UserRepository.getUserWrongPassword()},
                {UserRepository.getUserWrongPasswordAndLogin()}
        };
    }

    @Test(dataProvider = "wrongLoginData")
    public void loginNegativeTest(User user) {
        //Steps
        GuestService guestService = new GuestService();
        UserService userService = guestService.SuccessfulUserLogin(user);
        System.out.println(user.getToken());
        //Check
        Assert.assertFalse(adminService.isUserLogged(user));
    }

    @Test(dataProvider = "correctLoginData")
    public void logoutTest(User user) {
        //steps
        GuestService guestService = new GuestService();
        UserService userService = guestService.SuccessfulUserLogin(user);
        //check that user is logged
        Assert.assertTrue(adminService.isUserLogged(user));
        //step
        userService.LogoutUser();
        //check user is logout
        Assert.assertFalse(adminService.isUserLogged(user));
    }

    @Test(dataProvider = "correctLoginData",
            expectedExceptions = RuntimeException.class)
    public void logoutNegativeTest(User user) {
        //steps
        GuestService guestService = new GuestService();
        UserService userService = guestService.SuccessfulUserLogin(user);
        //check that user is logged
        Assert.assertTrue(adminService.isUserLogged(user));
        //step(wrong token)
        user.setToken("111");
        //here we are expecting for exception
        userService.LogoutUser();
    }


    /**
     * Two users are trying to login at the same time
     * both have correct data.
     * Expected result:both users will be logged
     *
     * @throws InterruptedException - because of using threads
     */
    @Test()
    public void loginTwoUsers1() throws InterruptedException {
        User user1 = UserRepository.getUser1();
        User user2 = UserRepository.getUser2();
        //first thread start
        LoginUserThread loginUserThread = new LoginUserThread(user1);
        Thread t = new Thread(loginUserThread);
        t.start();
        //second thread start
        LoginUserThread loginUserThread1 = new LoginUserThread(user2);
        Thread t1 = new Thread(loginUserThread1);
        t1.start();
        t.join();
        t1.join();
        //check users are logined
        Assert.assertTrue(adminService.isUserLogged(user1));
        Assert.assertTrue(adminService.isUserLogged(user2));
        //check tokens
        System.out.println(user1.getToken());
        System.out.println(user2.getToken());
        Assert.assertNotEquals(user1.getToken(), user2.getToken());
        UserService userService1 = new UserService(user1);
        UserService userService2 = new UserService(user2);
        //check names
        Assert.assertTrue(user1.getName().equals(userService1.getUserName()));
        Assert.assertTrue(user2.getName().equals(userService2.getUserName()));
        //logout user1
        userService1.LogoutUser();
        Assert.assertFalse(adminService.isUserLogged(user1));
        userService2.LogoutUser();
        //logout user2
        Assert.assertFalse(adminService.isUserLogged(user2));
    }

    /**
     * Two users are trying to login at the same time
     * one of them has correct data and another has
     * incorrect data.
     * Expected result: user with correct data will be
     * logged and user with incorrect data
     * won`t be logged
     *
     * @throws InterruptedException - because of using threads
     */
    @Test
    public void loginTwoUsers2() throws InterruptedException {
        //Steps
        User user = UserRepository.getUser1();
        User incorrectUser = UserRepository.getUserWrongLogin();
        //first thread start
        LoginUserThread loginUserThread = new LoginUserThread(user);
        Thread t = new Thread(loginUserThread);
        t.start();
        //second thread start
        LoginUserThread loginUserThread1 = new LoginUserThread(incorrectUser);
        Thread t1 = new Thread(loginUserThread1);
        t1.start();
        t.join();
        t1.join();
        //check users are logged
        Assert.assertTrue(adminService.isUserLogged(user));
        Assert.assertFalse(adminService.isUserLogged(incorrectUser));
        //Step
        UserService userService = new UserService(user);
        //check name
        Assert.assertTrue(user.getName().equals(userService.getUserName()));
        //logout user1
        userService.LogoutUser();
        Assert.assertFalse(adminService.isUserLogged(user));
    }

    /**
     * Two users are trying to login at the same time
     * both have incorrect dat
     * Expected result: both users won`t be logged
     *
     * @throws InterruptedException - because of using threads
     */
    @Test
    public void loginTwoUsers3() throws InterruptedException {
        //Steps
        User incorrectUser1 = UserRepository.getUserWrongPassword();
        User incorrectUser2 = UserRepository.getUserWrongLogin();
        //first thread start
        LoginUserThread loginUserThread = new LoginUserThread(incorrectUser1);
        Thread t = new Thread(loginUserThread);
        t.start();
        //second thread start
        LoginUserThread loginUserThread1 = new LoginUserThread(incorrectUser2);
        Thread t1 = new Thread(loginUserThread1);
        t1.start();
        t.join();
        t1.join();
        //check users are logged
        Assert.assertFalse(adminService.isUserLogged(incorrectUser1));
        Assert.assertFalse(adminService.isUserLogged(incorrectUser2));

    }


}

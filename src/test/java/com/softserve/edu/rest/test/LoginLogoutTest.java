package com.softserve.edu.rest.test;

import com.softserve.edu.rest.data.*;
import com.softserve.edu.rest.service.AdminService;
import com.softserve.edu.rest.service.GuestService;
import com.softserve.edu.rest.service.UserService;
import com.softserve.edu.rest.tools.LoginUserThread;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Epic("Ira`s tests")
@Feature("Login and logout tests")
public class LoginLogoutTest {
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
    public Object[][] correctUser() {
        return new Object[][]{
                {UserRepository.getUser1()},
        };
    }

    /**
     * Test for login(correct data).
     * Expected result: user will be logged
     *
     * @param user - user that we want to login
     */
    @Test(dataProvider = "correctUser")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test for login(correct data). \n" +
            "Expected result: user will be logged")
    @Story("Login")
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

    /**
     * Test for login.(incorrect data)
     * Expected result: user won`t be logged
     *
     * @param user - user that we want to login
     */
    @Test(dataProvider = "wrongLoginData")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test for login(incorrect data). \n" +
            "Expected result: user won`t be logged")
    @Story("Login")
    public void loginNegativeTest(User user) {
        //Steps
        GuestService guestService = new GuestService();
        UserService userService = guestService.SuccessfulUserLogin(user);
        System.out.println(user.getToken());
        //Check
        Assert.assertFalse(adminService.isUserLogged(user));
    }

    /**
     * Test for logout
     * Expected result: user will logout
     *
     * @param user - user that we want to logout
     */
    @Test(dataProvider = "correctUser")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test for logout. \n" +
            "Expected result: user will logout")
    @Story("Logout")
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

    /**
     * Test for logout
     * User`s data are correct, but we are changing token
     * to incorrect.
     * Expected result: exception
     *
     * @param user - user we want to logout
     */
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test for logout. \n" +
            "Token is invalid \n" +
            "Expected result: user won`t logout")
    @Story("logout")

    @Test(dataProvider = "correctUser",
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
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test for login. \n" +
            "Two users are trying to login at the same time \n" +
            "both have correct data \n" +
            "Expected result: both users will be logged")
    @Story("Two users trying to login")
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
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test for login. \n" +
            "Two users are trying to login at the same time \n" +
            "one of them has correct data and another has \n" +
            " incorrect data. \n" +
            "Expected result: user with correct data will be \n"+
            " logged and user with incorrect data ")
    @Story("Two users trying to login")
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
     * both have incorrect data
     * Expected result: both users won`t be logged
     *
     * @throws InterruptedException - because of using threads
     */

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test for login. \n" +
            "Two users are trying to login at the same time \n" +
            "both have incorrect data \n" +
            "Expected result: both users won`t be logged ")
    @Story("Two users trying to login")
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

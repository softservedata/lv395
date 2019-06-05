package com.softserve.edu.rest.test;

import com.softserve.edu.rest.data.*;
import com.softserve.edu.rest.service.AdminService;
import com.softserve.edu.rest.service.GuestService;
import com.softserve.edu.rest.service.UserService;
import com.softserve.edu.rest.tools.LoginUserThread;
import io.qameta.allure.*;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Epic("Ira`s tests")
@Feature("Login and logout tests")
public class LoginLogoutTest {
    private AdminService adminService;
    private final Logger log = Logger.getLogger(this.getClass());
    private final String INCORRECT_TOKEN="111";
    @BeforeClass
    public void beforeClass() {
        GuestService guestService = new GuestService();
        adminService = guestService.SuccessfulAdminLogin(UserRepository.getAdmin());
    }

    @AfterClass
    public void afterClass() {
        adminService.logoutUser();
        adminService.resetServiceToInitialState();
        adminService.resetServiceToInitialState();
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
        log.debug("loginPositiveTest started!");
        //Steps
        GuestService guestService = new GuestService();
        UserService userService = guestService.SuccessfulUserLogin(user);
        //Check
        Assert.assertTrue(adminService.isUserLogged(user));
        //Step
        userService.logoutUser();
        log.debug("loginPositiveTest finished!");
    }

    /**
     * Here we have negative data fot login
     * @return data for login
     */
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
        log.debug("loginNegativeTest started!");
        //Steps
        GuestService guestService = new GuestService();
        guestService.SuccessfulUserLogin(user);
        //Check
        Assert.assertFalse(adminService.isUserLogged(user));
        log.debug("loginNegativeTest finished!");
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
        log.debug("logoutTest started!");
        //steps
        GuestService guestService = new GuestService();
        UserService userService = guestService.SuccessfulUserLogin(user);
        //check that user is logged
        Assert.assertTrue(adminService.isUserLogged(user));
        //step
        userService.logoutUser();
        //check user is logout
        Assert.assertFalse(adminService.isUserLogged(user));
        log.debug("logoutTest finished!");
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
    @Story("Logout")

    @Test(dataProvider = "correctUser",
            expectedExceptions = RuntimeException.class)
    public void logoutNegativeTest(User user) {
        log.debug("logoutNegativeTest started!");
        //steps
        GuestService guestService = new GuestService();
        UserService userService = guestService.SuccessfulUserLogin(user);
        //check that user is logged
        Assert.assertTrue(adminService.isUserLogged(user));
        //step(wrong token)
        user.setToken(INCORRECT_TOKEN);
        //here we are expecting for exception
        userService.logoutUser();
        log.debug("logoutNegativeTest finished!");
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
        log.debug("loginTwoUsers1 started!");
        User user1 = UserRepository.getUser1();
        User user2 = UserRepository.getUser2();
        //first thread start
        LoginUserThread loginUserThread1 = new LoginUserThread(user1);
        Thread threadForUserCreating1 = new Thread(loginUserThread1);
        threadForUserCreating1.start();
        //second thread start
        LoginUserThread loginUserThread2 = new LoginUserThread(user2);
        Thread threadForUserCreating2 = new Thread(loginUserThread2);
        threadForUserCreating2.start();
        threadForUserCreating1.join();
        threadForUserCreating2.join();
        //check users are logged
        Assert.assertTrue(adminService.isUserLogged(user1));
        Assert.assertTrue(adminService.isUserLogged(user2));
        //check tokens
        Assert.assertNotEquals(user1.getToken(), user2.getToken());
        UserService userService1 = new UserService(user1);
        UserService userService2 = new UserService(user2);
        //check names
        Assert.assertEquals(user1.getName(), userService1.getUserName());
        Assert.assertEquals(user2.getName(), userService2.getUserName());
        //logout user1
        userService1.logoutUser();
        Assert.assertFalse(adminService.isUserLogged(user1));
        Assert.assertTrue(adminService.isUserLogged(user2));
        userService2.logoutUser();
        //logout user2
        Assert.assertFalse(adminService.isUserLogged(user2));
        log.debug("loginTwoUsers1 finished!");
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
            "Expected result: user with correct data will be \n" +
            " logged and user with incorrect data ")
    @Story("Two users trying to login")
    public void loginTwoUsers2() throws InterruptedException {
        log.debug("loginTwoUsers2 started!");
        //Steps
        User user = UserRepository.getUser1();
        User incorrectUser = UserRepository.getUserWrongLogin();
        //first thread start
        LoginUserThread loginUserThread1 = new LoginUserThread(user);
        Thread threadForUserCreating1 = new Thread(loginUserThread1);
        threadForUserCreating1.start();
        //second thread start
        LoginUserThread loginUserThread2 = new LoginUserThread(incorrectUser);
        Thread threadForUserCreating2 = new Thread(loginUserThread2);
        threadForUserCreating2.start();
        threadForUserCreating1.join();
        threadForUserCreating2.join();
        //check users are logged
        Assert.assertTrue(adminService.isUserLogged(user));
        Assert.assertFalse(adminService.isUserLogged(incorrectUser));
        //Step
        UserService userService = new UserService(user);
        //check name
        Assert.assertEquals(user.getName(), userService.getUserName());
        //logout user1
        userService.logoutUser();
        Assert.assertFalse(adminService.isUserLogged(user));
        log.debug("loginTwoUsers2 finished!");
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
    public void  loginTwoUsers3() throws InterruptedException {
        log.debug("loginTwoUsers3 started!");
        //Steps
        User incorrectUser1 = UserRepository.getUserWrongPassword();
        User incorrectUser2 = UserRepository.getUserWrongLogin();
        //first thread start
        LoginUserThread loginUserThread1 = new LoginUserThread(incorrectUser1);
        Thread threadForUserCreating1 = new Thread(loginUserThread1);
        threadForUserCreating1.start();
        //second thread start
        LoginUserThread loginUserThread2 = new LoginUserThread(incorrectUser2);
        Thread threadForUserCreating2 = new Thread(loginUserThread2);
        threadForUserCreating2.start();
        threadForUserCreating1.join();
        threadForUserCreating2.join();
        //check users are logged
        Assert.assertFalse(adminService.isUserLogged(incorrectUser1));
        Assert.assertFalse(adminService.isUserLogged(incorrectUser2));
        log.debug("loginTwoUsers3 finished!");

    }


}

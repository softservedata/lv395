package com.softserve.edu.rest.test;

import com.softserve.edu.rest.data.*;
import com.softserve.edu.rest.engine.LoginResource;
import com.softserve.edu.rest.entity.RestParameters;
import com.softserve.edu.rest.service.AdminService;
import com.softserve.edu.rest.service.GuestService;
import com.softserve.edu.rest.service.UserService;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class loginTest {
    @DataProvider // (parallel = true)
    public Object[][] loginData() {
        return new Object[][]{
                {UserRepository.getAdmin()},
        };
    }

    @Test(dataProvider = "loginData")
    public void loginPositiveTest(User user) {
        //Steps
        GuestService guestService=new GuestService();
        UserService userService=guestService.SuccessfulUserLogin(user);

        //Check
        Assert.assertTrue(user.isLogin());
    }

    @DataProvider // (parallel = true)
    public Object[][] wrongLoginData() {
        return new Object[][]{
                {UserRepository.getAdminWrongLogin()},
                {UserRepository.getAdminWrongPassword()},
                {UserRepository.getAdminWrongPasswordAndLogin()}
        };
    }
    @Test(dataProvider = "wrongLoginData")
    public void loginNeegativeTest(User user) {
        //Steps
        GuestService guestService=new GuestService();
        UserService userService=guestService.SuccessfulUserLogin(user);
        System.out.println(user.getToken());
        //Check
        Assert.assertFalse(user.isLogin());
    }
//    @DataProvider // (parallel = true)
//    public Object[][] loginDataForUsers() {
//        return new Object[][]{
//                {UserRepository.getAdmin(), UserRepository.getOtlumtc()},
//        };
//    }
//
//    @Test(dataProvider = "loginDataForUsers")
//    public void loginTwoUsers(User adminUser, User user) {
//        //first thread start
//        LoginUserThread loginUserThread = new LoginUserThread(adminUser);
//        loginUserThread.run();
//        Thread t = new Thread(loginUserThread);
//        t.start();
//
//        //second thread start
//        LoginUserThread loginUserThread1 = new LoginUserThread(user);
//        Thread t1 = new Thread(loginUserThread1);
//        t1.start();
//
//        System.out.println(adminUser.getToken());
//        System.out.println(user.getToken());
//
//        UserService userService = new UserService(adminUser);
//        userService.LogoutUser();
//
//        System.out.println("adminUser token " + adminUser.getToken());
//        System.out.println("User token " + user.getToken());
//
//
//    }
}

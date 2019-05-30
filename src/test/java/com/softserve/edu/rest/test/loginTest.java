package com.softserve.edu.rest.test;

import com.softserve.edu.rest.data.*;
import com.softserve.edu.rest.service.GuestService;
import com.softserve.edu.rest.service.UserService;
import com.softserve.edu.rest.tools.LoginUserThread;
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
        Assert.assertTrue(user.isUserLogined());
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
        Assert.assertFalse(user.isUserLogined());
    }
    @Test(dataProvider = "loginData", dependsOnMethods = "loginPositiveTest")
    public void logoutTest(User user){
        //steps
        GuestService guestService=new GuestService();
        UserService userService= guestService.SuccessfulUserLogin(user);
        //check that user is logged
        Assert.assertTrue(user.isUserLogined());
        //step
        userService.LogoutUser();
        //check user is logout
        Assert.assertFalse(user.isUserLogined());
    }

    @Test(dataProvider = "loginData", dependsOnMethods = "loginPositiveTest",
            expectedExceptions = RuntimeException.class)
    public void logoutNegativeTest(User user){
        //steps
        GuestService guestService=new GuestService();
        UserService userService= guestService.SuccessfulUserLogin(user);
        //check that user is logged
        Assert.assertTrue(user.isUserLogined());
        //step(wrohg token)
        user.setToken("111");
        //here we are expecting for exception
        userService.LogoutUser();
    }




    @DataProvider // (parallel = true)
    public Object[][] loginDataForUsers() {
        return new Object[][]{
                {UserRepository.getAdmin(), UserRepository.getOtlumtc()},
        };
    }

    @Test(dataProvider = "loginDataForUsers")
    public void loginTwoUsers(User adminUser, User user) {
        //first thread start
        LoginUserThread loginUserThread = new LoginUserThread(adminUser);
        loginUserThread.run();
        Thread t = new Thread(loginUserThread);
        t.start();

        //second thread start
        LoginUserThread loginUserThread1 = new LoginUserThread(user);
        Thread t1 = new Thread(loginUserThread1);
        t1.start();

//        //get tokens
//        System.out.println(adminUser.getToken());
//        System.out.println(user.getToken());

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertTrue(adminUser.isUserLogined());
        Assert.assertTrue(user.isUserLogined());


        UserService userService = new UserService(adminUser);
        UserService userService1 =new UserService(user);
        //check
        Assert.assertTrue(adminUser.getName().equals(userService.getUserName()));
        Assert.assertTrue(user.getName().equals(userService1.getUserName()));


        userService.LogoutUser();
        Assert.assertFalse(adminUser.isUserLogined());
//        System.out.println("adminUser token " + adminUser.getToken());
//        System.out.println("User token " + user.getToken());

        userService1.LogoutUser();
        Assert.assertFalse(user.isUserLogined());


    }
}

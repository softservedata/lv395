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
    public void checkLoginReport(User adminUser) {
        // Steps
        GuestService guestService = new GuestService();
        AdminService userService = guestService
                .SuccessfulAdminLogin(adminUser);
        //check token
        Assert.assertTrue(adminUser.getToken() != null);
        //check user name
        Assert.assertEquals(userService.getUserName(), adminUser.getName());

        //Steps

        String isCreated = userService.createUser("Ivan", "qwerty");
        System.out.println("is=" + isCreated);
        Assert.assertEquals(isCreated, "true");
        //Steps


        //check user logout
//        Assert.assertEquals(adminUser.getToken(), "");
        User user = new User("Ivan", "qwerty");
        guestService.SuccessfulUserLogin(user);
        System.out.println("token="+user.getToken());
        System.out.println(userService.getAllUsers());
        userService.LogoutUser();
//        Assert.assertNotEquals(user.getToken(),"ERROR, user not found");


    }


    @DataProvider // (parallel = true)
    public Object[][] loginDataForUsers() {
        return new Object[][]{
                {UserRepository.getAdmin(),UserRepository.getOtlumtc()},
        };
    }

    @Test(dataProvider = "loginDataForUsers")
    public void loginTwoUsers(User adminUser, User user) {
        //first thread start
        LoginUserThread  loginUserThread = new LoginUserThread(adminUser);
        loginUserThread.run();
        Thread t=new Thread(loginUserThread);
        t.start();

        //second thread start
        LoginUserThread loginUserThread1 = new LoginUserThread(user);
        Thread t1=new Thread(loginUserThread1);
        t1.start();

        System.out.println(adminUser.getToken());
        System.out.println(user.getToken());

        UserService userService=new UserService(adminUser);
        userService.LogoutUser();

        System.out.println("adminUser token "+ adminUser.getToken());
        System.out.println("User token "+ user.getToken());


    }
}

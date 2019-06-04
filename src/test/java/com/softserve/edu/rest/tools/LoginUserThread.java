package com.softserve.edu.rest.tools;

import com.softserve.edu.rest.data.User;
import com.softserve.edu.rest.service.AdminService;
import com.softserve.edu.rest.service.GuestService;
import com.softserve.edu.rest.service.UserService;
import io.qameta.allure.Step;


public class LoginUserThread implements Runnable {
    private User user;


    public LoginUserThread(User user) {
        this.user = user;
    }

    @Step("New thread for user user login")
    @Override
    public void run() {
        GuestService guestService = new GuestService();
        if (user.isAdminRights()) {
            AdminService userService = guestService.SuccessfulAdminLogin(user);
        } else {
            UserService userService = guestService.SuccessfulUserLogin(user);
        }
        System.out.println("user " + user.getName() + " is logged");

    }

}

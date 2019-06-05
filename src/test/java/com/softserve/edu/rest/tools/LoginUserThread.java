package com.softserve.edu.rest.tools;

import com.softserve.edu.rest.data.User;
import com.softserve.edu.rest.service.AdminService;
import com.softserve.edu.rest.service.GuestService;
import com.softserve.edu.rest.service.UserService;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;


public class LoginUserThread implements Runnable {
    private User user;
    private final Logger log = Logger.getLogger(this.getClass());


    public LoginUserThread(User user) {
        this.user = user;
    }

    @Step("New thread for user user login")
    @Override
    public void run() {
        log.info("Thread run!");
        GuestService guestService = new GuestService();
        if (user.isAdminRights()) {
            AdminService userService = guestService.SuccessfulAdminLogin(user);
        } else {
            UserService userService = guestService.SuccessfulUserLogin(user);
        }
        log.info("user " + user.getName() + " is logged");


    }

}

package com.softserve.edu.rest.test;

import com.softserve.edu.rest.data.User;
import com.softserve.edu.rest.service.GuestService;
import com.softserve.edu.rest.service.UserService;

public class LoginUserThread implements Runnable {
    private User user;
    private UserService userService;
    private GuestService guestService;

    public LoginUserThread(User user) {
        this.user=user;
        guestService=new GuestService();
    }
    @Override
    public void run() {
        userService= guestService.SuccessfulUserLogin(user);
        System.out.println("user "+user.getName() + " is logged");

    }
//    public GuestService logout(){
//        return userService.LogoutUser();
//    }
}

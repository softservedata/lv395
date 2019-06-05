package com.softserve.edu.rest.service;

import com.softserve.edu.rest.data.User;
import com.softserve.edu.rest.engine.*;

import com.softserve.edu.rest.entity.RestParameters;
import com.softserve.edu.rest.entity.SimpleEntity;
import io.qameta.allure.Step;

public class UserService extends GuestService {

    protected LogoutResource logoutResource;
    protected UserResource userResource;
    protected User user;
    protected UsersResourse usersResourse;

    public UserService(User user) {
        // super(); // by default
        logoutResource = new LogoutResource();
        userResource = new UserResource();
        usersResourse = new UsersResourse();
        this.user = user;
    }

    public UserService(LoginResource loginResource,
                       TokenlifetimeResource tokenlifetimeResource,
                       User user) {
        super(loginResource, tokenlifetimeResource);
        this.user = user;
    }

    @Step("Logout user")
    public GuestService logoutUser() {
        RestParameters bodyParameters = new RestParameters()
                .addParameter("name", user.getName())
                .addParameter("token", user.getToken());
        // SimpleEntity simpleEntity = loginResource
        //.httpDeleteAsEntity(null, null, bodyParameters);
        SimpleEntity simpleEntity = logoutResource
                .httpPostAsEntity(null, null, bodyParameters);
        checkEntity(simpleEntity, "Error Logout");
        user.setToken("");
        return new GuestService();
    }

    public String getUserName() {
        RestParameters urlParameters = new RestParameters()
                .addParameter("token", user.getToken());
        SimpleEntity simpleEntity = userResource
                .httpGetAsEntity(null, urlParameters);
        checkEntity(simpleEntity, user.getName());
        return simpleEntity.getContent();
    }


    public String changePassword(String newPassword) {
        RestParameters bodyParameters = new RestParameters()
                .addParameter("token", user.getToken())
                .addParameter("oldpassword", user.getPassword())
                .addParameter("newpassword", newPassword);
        SimpleEntity simpleEntity = userResource
                .httpPutAsEntity(null, null, bodyParameters);
        checkEntity(simpleEntity, user.getPassword());
        return simpleEntity.getContent();
    }

    public String changeCooldown(String newTime){
        RestParameters bodyParameters = new RestParameters()
                .addParameter("token", user.getToken())
                .addParameter("time", newTime);
        SimpleEntity simpleEntity = cooldownResource
                .httpPutAsEntity(null, null, bodyParameters);
        //checkEntity(simpleEntity, user.getPassword());
        return simpleEntity.getContent();
    }

}
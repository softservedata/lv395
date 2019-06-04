package com.softserve.edu.rest.service;

import com.softserve.edu.rest.data.Lifetime;
import com.softserve.edu.rest.data.User;
import com.softserve.edu.rest.engine.LoginResource;
import com.softserve.edu.rest.engine.TokenlifetimeResource;
import com.softserve.edu.rest.entity.RestParameters;
import com.softserve.edu.rest.entity.SimpleEntity;

import io.qameta.allure.Step;

public class AdminService extends UserService {

    public AdminService(User user) {
        super(user);
    }

    public AdminService(LoginResource loginResource,
            TokenlifetimeResource tokenlifetimeResource,
            User user) {
        super(loginResource, tokenlifetimeResource, user);
    }

    @Step("UpdateTokenlifetime() STEP")
    public AdminService UpdateTokenlifetime(Lifetime lifetime) {
        // System.out.println("lifetime = " + lifetime.getTimeAsString() + " User = " +
        // user);
        RestParameters bodyParameters = new RestParameters()
                .addParameter("token", user.getToken())
                .addParameter("time", lifetime.getTimeAsString());
        SimpleEntity simpleEntity = tokenlifetimeResource
                .httpPutAsEntity(null, null, bodyParameters);
        checkEntity(simpleEntity, "Error Update Tokenlifetime");
        return this;
    }

}

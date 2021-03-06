package com.softserve.edu.rest.service;

import com.softserve.edu.rest.data.Lifetime;
import com.softserve.edu.rest.data.User;
import com.softserve.edu.rest.engine.LoginResource;
import com.softserve.edu.rest.engine.TokenlifetimeResource;
import com.softserve.edu.rest.entity.RestParameters;
import com.softserve.edu.rest.entity.SimpleEntity;

import io.qameta.allure.Step;

public class GuestService {

    protected LoginResource loginResource;
    protected TokenlifetimeResource tokenlifetimeResource;

    public GuestService() {
        loginResource = new LoginResource();
        tokenlifetimeResource = new TokenlifetimeResource();
    }

    public GuestService(LoginResource loginResource, TokenlifetimeResource tokenlifetimeResource) {
        this.loginResource = loginResource;
        this.tokenlifetimeResource = tokenlifetimeResource;
    }

    protected void checkEntity(SimpleEntity simpleEntity, String message) {
        // if (!simpleEntity.getContent().toLowerCase().equals("true"))
        if ((simpleEntity.getContent() == null)
                || (simpleEntity.getContent().isEmpty())
                || (simpleEntity.getContent().toLowerCase().equals("false"))) {
            // TODO Develop Custom Exception
            throw new RuntimeException(message);
        }
    }

    @Step("getCurrentLifetime() STEP")
    public Lifetime getCurrentLifetime() {
        SimpleEntity simpleEntity = tokenlifetimeResource.httpGetAsEntity(null, null);
        return new Lifetime(simpleEntity.getContent());
    }

    // TODO
//    public void UnsuccessfulUserLogin(IUser user)
//    {
//    }
//
//    public void SuccessfulUserLogin(IUser user)
//    {
//    }

    @Step("SuccessfulAdminLogin() STEP")
    public AdminService SuccessfulAdminLogin(User adminUser) {
        RestParameters bodyParameters = new RestParameters()
                .addParameter("name", adminUser.getName())
                .addParameter("password", adminUser.getPassword());
        SimpleEntity simpleEntity = loginResource
                .httpPostAsEntity(null, null, bodyParameters);
        checkEntity(simpleEntity, "Error Login");
        adminUser.setToken(simpleEntity.getContent());
        return new AdminService(adminUser);
    }

}

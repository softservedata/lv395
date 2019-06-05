package com.softserve.edu.rest.service;

import com.softserve.edu.rest.data.Cooldown;
import com.softserve.edu.rest.data.Lifetime;
import com.softserve.edu.rest.data.User;
import com.softserve.edu.rest.engine.CooldownResource;
import com.softserve.edu.rest.engine.LoginResource;
import com.softserve.edu.rest.engine.ResetApiResource;
import com.softserve.edu.rest.engine.TokenlifetimeResource;
import com.softserve.edu.rest.entity.RestParameters;
import com.softserve.edu.rest.entity.SimpleEntity;
import io.qameta.allure.Step;

public class GuestService {

    protected LoginResource loginResource;
    protected TokenlifetimeResource tokenlifetimeResource;
    protected CooldownResource cooldownResource;
    private ResetApiResource resetApiResource;

    public GuestService() {
        loginResource = new LoginResource();
        tokenlifetimeResource = new TokenlifetimeResource();
        cooldownResource = new CooldownResource();
        resetApiResource = new ResetApiResource();
    }

    public GuestService(LoginResource loginResource, TokenlifetimeResource tokenlifetimeResource) {
        this.loginResource = loginResource;
        this.tokenlifetimeResource = tokenlifetimeResource;
    }

    public void resetServiceToInitialState() {
        resetApiResource.httpGetAsEntity(null, null);
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

    public boolean isUserLockedAfterTryToLogin(User user){
        RestParameters bodyParameters=new RestParameters()
                .addParameter("name", user.getName())
                .addParameter("password",user.getPassword());
        SimpleEntity simpleEntity = loginResource
                .httpPostAsEntity(null, null, bodyParameters);
        checkEntity(simpleEntity, "Error Login");
        return simpleEntity.getContent().equals("ERROR, user locked");
    }

    public Lifetime getCurrentLifetime() {
        SimpleEntity simpleEntity = tokenlifetimeResource.httpGetAsEntity(null, null);
        return new Lifetime(simpleEntity.getContent());
    }

    public Cooldown getCurrentCooldown() {
        SimpleEntity simpleEntity = cooldownResource.httpGetAsEntity(null, null);
        return new Cooldown(simpleEntity.getContent());
    }

    // TODO
//    public void UnsuccessfulUserLogin(IUser user)
//    {
//    }
//
    @Step("Login user")
    public synchronized UserService SuccessfulUserLogin(User user) {
        RestParameters bodyParameters = new RestParameters()
                .addParameter("name", user.getName())
                .addParameter("password", user.getPassword());
        SimpleEntity simpleEntity = loginResource
                .httpPostAsEntity(null, null, bodyParameters);
        checkEntity(simpleEntity, "Error Login");
        user.setToken(simpleEntity.getContent());
        return new UserService(user);
    }

    @Step("Login admin")
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

    public AdminService ChangeCurrentPassword(User adminUser) {
        String pass = "1111";
        RestParameters bodyParameters = new RestParameters()
                .addParameter("token", adminUser.getToken())
                .addParameter("oldpassword", adminUser.getPassword())
                .addParameter("newpassword", pass);
        SimpleEntity simpleEntity = loginResource
                .httpPostAsEntity(null, null, bodyParameters);
        checkEntity(simpleEntity, "Error Login");
        adminUser.setToken(simpleEntity.getContent());
        return new AdminService(adminUser);
    }

}

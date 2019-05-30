package com.softserve.edu.rest.service;

import com.softserve.edu.rest.data.Lifetime;
import com.softserve.edu.rest.data.User;
import com.softserve.edu.rest.engine.LoginResource;
import com.softserve.edu.rest.engine.TokenlifetimeResource;
import com.softserve.edu.rest.engine.UserResource;
import com.softserve.edu.rest.entity.RestParameters;
import com.softserve.edu.rest.entity.SimpleEntity;

public class AdminService extends UserService {


    public AdminService(User user) {
        super(user);
    }

    public AdminService(LoginResource loginResource,
            TokenlifetimeResource tokenlifetimeResource,
            User user) {
        super(loginResource, tokenlifetimeResource, user);
    }

    public AdminService UpdateTokenlifetime(Lifetime lifetime) {
        // System.out.println("lifetime = " + lifetime.getTimeAsString() + " User = " +
        // user);
        RestParameters bodyParameters = new RestParameters()
                .addParameter("adminToken", user.getToken())
                .addParameter("time", lifetime.getTimeAsString());
        SimpleEntity simpleEntity = tokenlifetimeResource
                .httpPutAsEntity(null, null, bodyParameters);
        checkEntity(simpleEntity, "Error Update Tokenlifetime");
        return this;
    }
//    public String getAdminName() {
//        RestParameters urlParameters = new RestParameters()
//                .addParameter("token",user.getToken());
//        SimpleEntity simpleEntity = userResource
//                .httpGetAsEntity(null, urlParameters);
//        checkEntity(simpleEntity, user.getName());
//        return simpleEntity.getContent();
//    }
    public String createUser(String name, String password){
        RestParameters bodyParameters = new RestParameters()
                .addParameter("token",user.getToken())
                .addParameter("name",name)
                .addParameter("password",password);
        SimpleEntity simpleEntity = userResource.
                httpPostAsEntity(null,null,bodyParameters);

        checkEntity(simpleEntity,"true");
        return simpleEntity.getContent();

    }
    public String getAllUsers(){
        RestParameters urlParameters = new RestParameters()
                .addParameter("token",user.getToken());
        SimpleEntity simpleEntity = usersResourse.httpGetAsEntity(null, urlParameters);
        return simpleEntity.getContent();
    }

}

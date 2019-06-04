package com.softserve.edu.rest.service;

import com.softserve.edu.rest.data.Lifetime;
import com.softserve.edu.rest.data.User;
import com.softserve.edu.rest.engine.*;
import com.softserve.edu.rest.entity.RestParameters;
import com.softserve.edu.rest.entity.SimpleEntity;
import io.qameta.allure.Step;

public class AdminService extends UserService {

    private AdminsResource adminsResource;
    private LoggedAdminsResource loggedAdminsResource;
    private LoggedUsersResource loggedUsersResource;
    private LockedUsersResource lockedUsersResource;
    private UnlockAllUsersResource unlockAllUsersResource;
    private LockUnlockUserResource lockUnlockUserResource;
    //protected CooldownResource cooldownResource;

    public AdminService(User user) {
        super(user);
        adminsResource = new AdminsResource();
        loggedAdminsResource = new LoggedAdminsResource();
        loggedUsersResource = new LoggedUsersResource();
        lockedUsersResource = new LockedUsersResource();
        unlockAllUsersResource = new UnlockAllUsersResource();
        lockUnlockUserResource = new LockUnlockUserResource();
        cooldownResource = new CooldownResource();}

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
    @Step("Create user")
    public Boolean createUser(User newUser) {
        RestParameters bodyParameters = new RestParameters()
                .addParameter("token", user.getToken())
                .addParameter("name", newUser.getName())
                .addParameter("password", newUser.getPassword())
                .addParameter("rights", newUser.isAdminRights() + "");

        SimpleEntity simpleEntity = userResource.
                httpPostAsEntity(null, null, bodyParameters);
        if (simpleEntity.getContent().equals("true")) {
            return true;
        } else {
            return false;
        }

    }

    public Boolean setCooldownTime(User adminUser) {
        RestParameters bodyParameters = new RestParameters()
                .addParameter("token", user.getToken())
//                .addParameter("time", adminUser.getTime())
                .addParameter("time", "3333" )
                ;

        SimpleEntity simpleEntity = userResource.
                httpPutAsEntity(null, bodyParameters, null);

        checkEntity(simpleEntity, "true");
        if (simpleEntity.getContent().equals("true")) {
            return true;
        } else {
            return false;
        }

    }


    public String getAllUsers() {
        RestParameters urlParameters = new RestParameters()
                .addParameter("token", user.getToken());
        SimpleEntity simpleEntity = usersResourse.httpGetAsEntity(null, urlParameters);
        return simpleEntity.getContent();
    }
@Step("Delete user")
    public Boolean removeUser(String removedName) {
        RestParameters urlParameters = new RestParameters()
                .addParameter("token", user.getToken())
                .addParameter("name", removedName);
        SimpleEntity simpleEntity = userResource.httpDeleteAsEntity(null, urlParameters, null);
        checkEntity(simpleEntity, "true");
        if (simpleEntity.getContent().equals("true")) {
            return true;
        } else {
            return false;
        }

    }

    public String getAllAdmins() {
        RestParameters urlParameters = new RestParameters()
                .addParameter("token", user.getToken());
        SimpleEntity simpleEntity = adminsResource.httpGetAsEntity(null, urlParameters);
        return simpleEntity.getContent();
    }

    public String getAllLoggedAdmins() {
        RestParameters urlParameters = new RestParameters()
                .addParameter("token", user.getToken());
        SimpleEntity simpleEntity = loggedAdminsResource.httpGetAsEntity(null, urlParameters);
        return simpleEntity.getContent();
    }

    public String getAllLoggedUsers() {
        RestParameters urlParameters = new RestParameters()
                .addParameter("token", user.getToken());
        SimpleEntity simpleEntity = loggedUsersResource.httpGetAsEntity(null, urlParameters);
        return simpleEntity.getContent();
    }

    public Boolean unlockUser(User unlockingUser) {
        RestParameters bodyParameters = new RestParameters()
                .addParameter("token", user.getToken())
                .addParameter("name", unlockingUser.getName());
        RestParameters pathVariable = new RestParameters()
                .addParameter("lockName", unlockingUser.getName());

        SimpleEntity simpleEntity = lockUnlockUserResource.
                httpPutAsEntity(pathVariable, null, bodyParameters);

        checkEntity(simpleEntity, "true");
        if (simpleEntity.getContent().equals("true")) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean lockUser(User lockingUser) {
        RestParameters bodyParameters = new RestParameters()
                .addParameter("token", user.getToken())
                .addParameter("name", lockingUser.getName());
        RestParameters pathVariable = new RestParameters()
                .addParameter("lockName", lockingUser.getName());

        SimpleEntity simpleEntity = lockUnlockUserResource.
                httpPostAsEntity(pathVariable, null, bodyParameters);

        checkEntity(simpleEntity, "true");
        if (simpleEntity.getContent().equals("true")) {
            return true;
        } else {
            return false;
        }
    }

    public String getLockedUsers() {
        RestParameters urlParameters = new RestParameters()
                .addParameter("token", user.getToken());
        SimpleEntity simpleEntity = lockedUsersResource.httpGetAsEntity(null, urlParameters);
        return simpleEntity.getContent();
    }

    public boolean isUserLogged(User user) {

        if (getAllLoggedUsers().contains(user.getName())) {
            return true;
        } else {
            return false;
        }
    }

    public String unlockAllUsers() {
        RestParameters bodyParameters = new RestParameters()
                .addParameter("token", user.getToken());
        SimpleEntity simpleEntity = unlockAllUsersResource.httpPutAsEntity(null, null, bodyParameters);
        return simpleEntity.getContent();
    }

    public boolean isUserLocked(User user){
        if (getLockedUsers().contains(user.getName())) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isUserCreated(User user) {
        if (getAllUsers().contains(user.getName())) {
            return true;
        } else {
            return false;
        }

    }

    public String changeCooldown(){
        RestParameters bodyParameters = new RestParameters()
                .addParameter("token", user.getToken())
                .addParameter("time", "9999");
        SimpleEntity simpleEntity = cooldownResource
                .httpPutAsEntity(null, null, bodyParameters);
        //checkEntity(simpleEntity, user.getPassword());
        return simpleEntity.getContent();
    }

    public boolean isUserAdmin(User user) {
        if (getAllAdmins().contains(user.getName())) {
            return true;
        } else {
            return false;
        }
    }
}

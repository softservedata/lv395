package com.softserve.edu.rest.data;

public final class RestUrlRepository {

    private static String server = "http://localhost:8080/";

    private RestUrlRepository() {
    }

    public static String getServer() {
        return server;
    }

    public static void setServer(String server) {
        RestUrlRepository.server = server;
    }


    // Resource Loggined User
    public static RestUrl getLogin() {
        return new RestUrl()
                .addUrlServer(server)
                .addUriGet("/login/users")
                .addUriPost("login/")
                .addUriPut("")
                // .addUriDelete("logout/");
                .addUriDelete("");
    }

    public static RestUrl getLogout() {
        return new RestUrl()
                .addUrlServer(server)
                .addUriGet("")
                .addUriPost("logout/")
                .addUriPut("")
                .addUriDelete("logout/");
    }

    public static RestUrl getTokenLifetime() {
        return new RestUrl()
                .addUrlServer(server)
                .addUriGet("tokenlifetime/")
                .addUriPost("")
                .addUriPut("tokenlifetime/")
                .addUriDelete("");
    }
    public static RestUrl getUser(){
        return new RestUrl()
                .addUrlServer(server)
                .addUriGet("user/")
                .addUriPost("user/")
                .addUriPut("user/")
                .addUriDelete("user/");
    }
    public static RestUrl getUsers(){
        return new RestUrl()
                .addUrlServer(server)
                .addUriGet("users/");
    }

    public static RestUrl getCooldownTime(){
        return new RestUrl()
                .addUrlServer(server)
                .addUriGet("/cooldowntime")
                .addUriPut("/cooldowntime");
    }
    public static RestUrl getLockedAdmins() {
        return new RestUrl()
                .addUrlServer(server)
                .addUriGet("locked/admins");
    }
    public static RestUrl getLockedUsers() {
        return new RestUrl()
                .addUrlServer(server)
                .addUriGet("locked/users");
    }
    public static RestUrl lockUnlockUser() {
        return new RestUrl()
                .addUrlServer(server)
                .addUriPost("locked/user/{lockName}")
                .addUriPut("locked/user/{lockName}");
    }

    /*public static RestUrl unlockUser() {
        return new RestUrl()
                .addUrlServer(server)
                .addUriPut("locked/user/");
    }*/

    public static RestUrl unlockAllUsers() {
        return new RestUrl()
                .addUrlServer(server)
                .addUriPut("locked/reset");
    }
    public static RestUrl getAdmins() {
        return new RestUrl()
                .addUrlServer(server)
                .addUriGet("/admins");
    }
    public static RestUrl getLoginAdmins() {
        return new RestUrl()
                .addUrlServer(server)
                .addUriGet("login/admins");
    }
    public static RestUrl getLoggedUsers() {
        return new RestUrl()
                .addUrlServer(server)
                .addUriGet("login/users");
    }

    public static RestUrl getActiveTokens() {
        return new RestUrl()
                .addUrlServer(server)
                .addUriGet("/login/tockens");
    }
    public static RestUrl getChangePassword() {
        return new RestUrl()
                .addUrlServer(server)
                .addUriPut("/user");
    }

}


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

//    public static RestUrl getAdmin(){
//        return new RestUrl()
//                .addUrlServer(server)
//                .addUriGet("user/")
//                .addUriPost("user/")
//                .addUriPut("user/")
//                .addUriDelete("user/");
//    }

}

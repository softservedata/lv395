package com.softserve.edu.rest.data;

public final class  UserRepository {

    private UserRepository() {
    }

    public static User getAdmin() {
        return new User("admin", "qwerty")
                .setAdminRights(true)
                .addItem(new Item(0, "My data"));
    }
    public static User getUser1(){
        return new User("otlumtc","qwerty")
                .addItem(new Item(0,"My data"));
    }
    public static User getUserWrongLogin(){
        return new User("hahaha","qwerty")
                .addItem(new Item(0,"My data"));
    }
    public static User getUserWrongPassword(){
        return new User("otlumtc","hahaha")
                .addItem(new Item(0,"My data"));
    }
    public static User getUserWrongPasswordAndLogin(){
        return new User("hahaha","hahaha")
                .addItem(new Item(0,"My data"));
    }
    public static User getUser2(){
        return new User("vbudktc","qwerty")
                .addItem(new Item(0,"My data"));
    }
    public static User newUserWithoutAdminRights(){
        return new User("Ivan","qwerty")
                .addItem(new Item(0,"My data"))
                .setAdminRights(false);
    }
    public static User newUserWithAdminRights(){
        return new User("Petro","qwerty")
                .addItem(new Item(0,"My data"))
                .setAdminRights(true);
    }
    public static User getAdminWrongPassword(){
        return new User("Petro","hahaha")
                .addItem(new Item(0,"My data"))
                .setAdminRights(true);
    }
    public static User notExistingUser(){
        return new User("Anna","qwerty")
                .addItem(new Item(0,"My data"))
                .setAdminRights(true);
    }


}
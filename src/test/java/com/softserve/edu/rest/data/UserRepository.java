package com.softserve.edu.rest.data;

public final class  UserRepository {

    private UserRepository() {
    }

    public static User getAdmin() {
        return new User("admin", "qwerty")
                .setAdminRights(true)
                .addItem(new Item(0, "My data"));
    }
    public static User getOtlumtc(){
        return new User("otlumtc","qwerty")
                .addItem(new Item(0,"My data"));
    }

}

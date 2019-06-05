package com.softserve.edu.rest.data;

public class Password {

    // Use long, etc.
    private String password;

    public Password(String password) {
        this.password = password;
    }

    public String getPasswordAsString() {
        return password;
    }

    @Override
    public String toString() {
        return "Password [password=" + password + "]";
    }

}

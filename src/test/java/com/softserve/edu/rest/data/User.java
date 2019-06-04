package com.softserve.edu.rest.data;

import io.qameta.allure.Step;

import java.util.ArrayList;
import java.util.List;

// TODO Add Builder
public class User {

    private String name;
    private String password;
    private String token;
    private String time;
    private List<Item> items;
    private boolean adminRights;
    private final String ERROR_USER_LOCKED = "ERROR, user locked";
    private final String ERROR_USER_NOT_FOUND = "ERROR, user not found";

    // TODO Develop Builder
    public User(String name, String password) {
        this.name = name;
        this.password = password;
        items = new ArrayList<>();
        adminRights = false;
    }


    // setters

    public User setAdminRights(boolean adminRights) {
        this.adminRights = adminRights;
        return this;
    }

    @Step("Change token")
    public User setToken(String token) {
        this.token = token;
        return this;
    }

    public User setTime(String time) {
        this.time = time;
        return this;
    }

    public User addItem(Item item) {
        items.add(item);
        return this;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }


    // getters

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getToken() {
        return token;
    }

    public String getTime() {
        return time;
    }

    public boolean isAdminRights() {
        return adminRights;
    }

    public List<Item> getItems() {
        return items;
    }


    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", token='" + token + '\'' +
                ", items=" + items +
                ", adminRights=" + adminRights +
                '}';
    }
}

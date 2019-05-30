package com.softserve.edu.rest.data;

import java.util.ArrayList;
import java.util.List;

// TODO Add Builder
public class User {

    private String name;
    private String password;
    private String token;
    private List<Item> items;
    private boolean adminRights;

    // TODO Develop Builder
    public User(String name, String password) {
        this.name = name;
        this.password = password;
        items = new ArrayList<>();
        adminRights = false;
    }

    public boolean checkToken() {
        if (this.token.equals("ERROR, user locked") ||
                this.token.equals("ERROR, user not found")||
                this.getToken().equals("")) {
            return false;
        }
        return true;
    }

    // setters

    public User setAdminRights(boolean adminRights) {
        this.adminRights = adminRights;
        return this;
    }

    public User setToken(String token) {
        this.token = token;
        return this;
    }

    public User addItem(Item item) {
        items.add(item);
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

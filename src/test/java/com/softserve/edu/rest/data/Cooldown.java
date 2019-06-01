package com.softserve.edu.rest.data;

public class Cooldown {

    // Use long, etc.
    private String cooldown;

    public Cooldown(String cooldown) {
        this.cooldown = cooldown;
    }

    public String getTimeAsString() {
        return cooldown;
    }

    @Override
    public String toString() {
        return "Cooldown [cooldown=" + cooldown + "]";
    }

}

package com.softserve.edu.rest.data;

public final class Lifetime {

    public static final String DEFAULT_LIFETIME = "999999";
    // Use long, etc.
    private String time;

    public Lifetime(String time) {
        this.time = time;
    }

    public String getTimeAsString() {
        return time;
    }

    public static Lifetime getDefaultLifetime() {
        return new Lifetime(DEFAULT_LIFETIME);
    }

    public long getTimeAsLong() {
        return Long.parseLong(time);
    }

    @Override
    public String toString() {
        return "Lifetime [time=" + time + "]";
    }

}

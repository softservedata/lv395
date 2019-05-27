package com.softserve.edu.rest.data;

public class Lifetime {

    // Use long, etc.
    private String time;

    public Lifetime(String time) {
        this.time = time;
    }

    public String getTimeAsString() {
        return time;
    }

    public long getTimeAsLong() {
        return Long.parseLong(time);
    }

    @Override
    public String toString() {
        return "Lifetime [time=" + time + "]";
    }

}

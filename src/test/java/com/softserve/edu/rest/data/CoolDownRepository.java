package com.softserve.edu.rest.data;

public final class CoolDownRepository {

    public static final String DEFAULT_COOLDOWN_TIME = "180000";
    public static final String LONG_COOLDOWN_TIME = "999999";

    private CoolDownRepository() {
    }

    public static Cooldown getDefault() {
        return new Cooldown(DEFAULT_COOLDOWN_TIME);
    }

    public static Cooldown GetLongTime() {
        return new Cooldown(LONG_COOLDOWN_TIME);
    }

}

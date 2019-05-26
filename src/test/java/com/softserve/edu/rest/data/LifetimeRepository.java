package com.softserve.edu.rest.data;

public final class LifetimeRepository {

    public static final String DEFAULT_TOKEN_LIFETIME = "300000";
    public static final String LONG_TOKEN_LIFETIME = "800000";

    private LifetimeRepository() {
    }

    public static Lifetime getDefault() {
        return new Lifetime(DEFAULT_TOKEN_LIFETIME);
    }

    public static Lifetime GetLongTime() {
        return new Lifetime(LONG_TOKEN_LIFETIME);
    }

}

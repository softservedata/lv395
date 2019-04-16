package com.softserve.createuserpage;

/**
 * This is TestData class
 * provides data for dataproviders.
 */
public class TestData {
    /**
     *
     */
    public String[] items;

    /**
     * Constructor with params.
     * @param items items.
     */
    public TestData(String... items) {
        this.items = items;
    }

    /**
     * Getter.
     * @param x
     * @return
     */
    public String get(int x) {
        return items[x];
    }
}

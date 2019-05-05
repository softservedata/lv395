package com.softserve.edu.opencart.data;

public final class ProductRepository {

    public ProductRepository() {
    }

    public static Product getDefault() {
        return getMacBook();
    }
    
    public static Product getMacBook() {
        return new Product("MacBook",
                "Intel Core 2 Duo processor Powered by an Intel Core 2 Duo processor at speeds up to 2.1..",
                "500.00");
    }

    public static Product getIPhone3() {
        return new Product("iPhone 3",
                "iPhone is a revolutionary new mobile phone that allows you to make a call by simply tapping a nam..",
                "101.00");
    }
    
}

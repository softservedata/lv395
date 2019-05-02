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
    
}

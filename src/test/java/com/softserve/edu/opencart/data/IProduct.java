package com.softserve.edu.opencart.data;

import java.util.List;

public interface IProduct {
    String getName();

    String getDescription();

    String getPriceDollarExTax();

    List<String> getCategories();

}

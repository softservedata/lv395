package com.softserve.edu.opencart.data;

import java.math.BigDecimal;
import java.util.List;

public interface IProduct {
    String getName();

    String getDescription();

    String getPriceDollarExTax();

    List<String> getCategories();

    BigDecimal getSubTotal();

    BigDecimal getEcoTax();

    BigDecimal getVat();

    BigDecimal getTotalPrice();

}

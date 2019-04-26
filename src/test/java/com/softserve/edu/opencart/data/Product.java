package com.softserve.edu.opencart.data;

public class Product {

    private String name;
    private String description;
    private String priceDollarExTax;
    //
    // TODO
    // private HashMap<EnumCurrencies, Decimal> prices;

    public Product(String name, String description, String priceDollarExTax) {
        this.name = name;
        this.description = description;
        this.priceDollarExTax = priceDollarExTax;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriceDollarExTax(String priceDollarExTax) {
        this.priceDollarExTax = priceDollarExTax;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPriceDollarExTax() {
        return priceDollarExTax;
    }

}

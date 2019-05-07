package com.softserve.edu.opencart.data;

import java.util.List;

interface IName {
    IProductBuild setName(String name);
}

interface IProductBuild {
    IProductBuild setDescription(String description);

    IProductBuild setPriceDollarExTax(String priceDollarExTax);

    IProductBuild setCategories(List<String> categories);

    IProduct build();
}

public class Product implements IName, IProductBuild, IProduct {

    private String name;
    private String description;
    private String priceDollarExTax;
    private List<String> categories;
    //
    // TODO
    // private HashMap<EnumCurrencies, Decimal> prices;

//    public Product(String name, String description, String priceDollarExTax) {
//        this.name = name;
//        this.description = description;
//        this.priceDollarExTax = priceDollarExTax;
//    }

    //    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public void setPriceDollarExTax(String priceDollarExTax) {
//        this.priceDollarExTax = priceDollarExTax;
//    }



    private Product() {
        description = new String();
        priceDollarExTax = new String();
    }

    public static IName get() {
        return new Product();
    }

    public IProductBuild setName(String name) {
        this.name = name;
        return this;
    }

    public IProductBuild setDescription(String description) {
        this.description = description;
        return this;
    }

    public IProductBuild setPriceDollarExTax(String priceDollarExTax) {
        this.priceDollarExTax=priceDollarExTax;
        return this;
    }
    public IProductBuild setCategories(List<String > categories){
        this.categories=categories;
        return this;
    }


    public IProduct build(){
        return this;
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

    public List<String> getCategories(){ return categories;}

}

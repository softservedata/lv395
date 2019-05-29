package com.softserve.edu.opencart.data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public final class ProductRepository {

    public ProductRepository() {
    }

    public static IProduct getDefault() {
        return getMacBook();
    }

    public static List<IProduct> getAllProducts(){
        List<IProduct> products = new ArrayList<>();
        products.add(getSamsungGalaxyTab());
        products.add(getIMac());
        products.add(getSonyVAIO());
        products.add(getMacBook());
        products.add(getCanonEOS5D());
        products.add(getAppleCinema());
        products.add(getIPhone());
        products.add(getIPodShuffle());
        products.add(getHPLP3065());
        products.add(getHTCTouchHD());
        products.add(getIPodClassic());
        products.add(getIPodNano());
        products.add(getIPodTouch());
        products.add(getMacBookAir());
        products.add(getMacBookPro());
        products.add(getNikonD300());
        products.add(getPalmTreoPro());
        products.add(getProduct8());
        products.add(getSamsungSyncMaster941BW());

        return products;
    }

    public static IProduct getMacBook() {
        return Product.get().setName("MacBook")
                .setDescription("Intel Core 2 Duo processor")
                .setPriceDollarExTax("500.00")
                .setSubTotal(new BigDecimal(500.00))
                .setEcoTax(new BigDecimal(2.00))
                .setVat(new BigDecimal(100.00))
                .setTotalPrice(new BigDecimal(602.00))
                .build();

    }

    public static IProduct getAppleCinema() {
        return Product.get().setName("Apple Cinema 30\"")
                .setDescription("The 30-inch Apple Cinema HD Display " +
                        "delivers an amazing 2560 x 1600 pixel resolution." +
                        " Designed sp..")
                .setPriceDollarExTax("110.00")
                .build();
    }
    public static IProduct getCanonEOS5D(){
        return Product.get().setName("Canon EOS 5D")
                .setDescription("Canon's press material for the" +
                        " EOS 5D states that it 'defines (a) new " +
                        "D-SLR category', while we'r..")
                .setPriceDollarExTax("98.00")
                .build();
    }

    public static IProduct getIPhone(){
        return Product.get().setName("iPhone")
                .setPriceDollarExTax("101.00")
                .build();
    }

    public static IProduct getIPhone3(){
        return Product.get().setName("iPhone 3")
                .setPriceDollarExTax("101.00")
                .setSubTotal(new BigDecimal(101.00))
                .setEcoTax(new BigDecimal(2.00))
                .setVat(new BigDecimal(20.20))
                .setTotalPrice(new BigDecimal(123.20))
                .build();
    }

    public static IProduct getSonyVAIO(){
            List<String> categories=new ArrayList<>();
            categories.add("Laptops & Notebooks");
            categories.add(" Desktops");
        return Product.get().setName("Sony VAIO")
                .setDescription("Unprecedented power. The next" +
                        " generation of processing technology has" +
                        " arrived. Built into the new..")
                .setPriceDollarExTax("1,202.00")
                .setCategories(categories)
                .build();
    }
public static IProduct getIMac(){
        List<String> categories=new ArrayList<>();
        categories.add("Mac");
        return Product.get()
                .setName("iMac")
                .setCategories(categories)
                .build();
}

    public static IProduct getHPLP3065(){
        return Product.get().setName("HP LP3065").build();
    }

    public static IProduct getHTCTouchHD(){
        return Product.get().setName("HTC Touch HD").build();
    }

//    public static IProduct getIPhone(){
//        return  Product.get().setName("iPhone").build();
//    }
    public static IProduct getIPodClassic(){
        return  Product.get().setName("iPod Classic").build();
    }
    public static IProduct getIPodNano(){
        return  Product.get().setName("iPod Nano").build();
    }
    public static IProduct getIPodShuffle(){
        return  Product.get().setName("iPod Shuffle").build();
    }
    public static IProduct getIPodTouch(){
        return  Product.get().setName("iPod Touch").build();
    }
    public static IProduct getMacBookAir(){
        return  Product.get().setName("MacBook Air").build();
    }
    public static IProduct getMacBookPro(){
        return  Product.get().setName("MacBook Pro").build();
    }
    public static IProduct getNikonD300(){
        return  Product.get().setName("Nikon D300").build();
    }
    public static IProduct getPalmTreoPro(){
        return  Product.get().setName("Palm Treo Pro").build();
    }
    public static IProduct getProduct8(){
        return  Product.get().setName("Product 8").build();
    }
    public static IProduct getSamsungSyncMaster941BW(){
        return  Product.get().setName("Samsung SyncMaster 941BW").build();
    }

    public static IProduct getSamsungGalaxyTab(){
        return  Product.get().setName("Samsung Galaxy Tab 10.1").build();
    }

}

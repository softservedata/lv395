package com.softserve.edu.opencart.data;

import java.math.BigDecimal;
import java.util.ArrayList;

public final class ProductRepository {

    public ProductRepository() {
    }

    public static IProduct getDefault() {
        return getMacBook();
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
        return Product.get().setName("Apple Cinema 30")
                .setDescription("The 30-inch Apple Cinema HD Display delivers an amazing 2560 x 1600 pixel resolution. Designed sp..")
                .setPriceDollarExTax("110.00")
                .build();
    }
    public static IProduct getCanonEOS5D(){
        return Product.get().setName("Canon EOS 5D")
                .setDescription("Canon's press material for the EOS 5D states that it 'defines (a) new D-SLR category', while we'r..")
                .setPriceDollarExTax("98.00")
                .build();
    }

    public static IProduct getIPhone3(){
        return Product.get().setName("iPhone 3")
                .setPriceDollarExTax("101.00")
                .build();
    }
//    public static Product getHPLP3065(){
//        return new Product("HP LP3065","Stop your co-workers in their tracks with the stunning new 30-inch diagonal HP LP3065 Flat Panel ..","122.00");
//    }
//    public static Product getHTCTouchHD(){
//        return new Product("HTC Touch HDHTC Touch HD","HTC Touch - in High Definition. Watch music videos and streaming content in awe-inspiring high de..","122.00");
//    }
//    public static Product getIMac(){
//        return new Product("iMac","Just when you thought iMac had everything, now there´s even more. More powerful Intel Core 2 Duo ..","122.00");
//    }
//    public static Product getIPhone(){
//        return new Product("iPhone","iPhone is a revolutionary new mobile phone that allows you to make a call by simply tapping a nam..","123.20");
//    }
//    public static Product getIPodClassic(){
//        return new Product("iPod Classic","More room to move. With 80GB or 160GB of storage and up to 40 hours of battery l..","122.00");
//    }
//    public static Product getIPodNano(){
//        return new Product("iPod Nano","Video in your pocket. Its the small iPod with one very big idea: video. The worlds most..","122.00");
//    }
//    public static Product getIPodShuffle(){
//        return new Product("iPod Shuffle","Born to be worn. Clip on the worlds most wearable music player and take up to 240 songs wit..","122.00");
//    }
//    public static Product getIPodTouch(){
//        return new Product("iPod Touch","Revolutionary multi-touch interface. iPod touch features the same multi-touch screen technology..","122.00");
//    }
//    public static Product getMacBookAir(){
//        return new Product("MacBook Air","MacBook Air is ultrathin, ultraportable, and ultra unlike anything else. But you don’t lose..","1,202.00");
//    }
//    public static Product getMacBookPro(){
//        return new Product("MacBook Pro","Latest Intel mobile architecture Powered by the most advanced mobile processors ..","2,000.00");
//    }
//    public static Product getNikonD300(){
//        return new Product("Nikon D300","Engineered with pro-level features and performance, the 12.3-effective-megapixel D300 combine..","98.00");
//    }
//    public static Product getPalmTreoPro(){
//        return new Product("Palm Treo Pro","Redefine your workday with the Palm Treo Pro smartphone. Perfectly balanced, you can respond to b..","337.99");
//    }
//    public static Product getProduct8(){
//        return new Product("Product 8","Product 8","122.00");
//    }
//    public static Product getSamsungSyncMaster941BW(){
//        return new Product("Samsung SyncMaster 941BW","Imagine the advantages of going big without slowing down. The big 19\" 941BW monitor combines..","242.00");
//    }
//    public static Product getSonyVAIO(){
//        return new Product("Sony VAIO","Unprecedented power. The next generation of processing technology has arrived. Built into the new..","1,202.00");
//    }
//    public static Product get(){
//        return new Product("Samsung Galaxy Tab 10.1","Samsung Galaxy Tab 10.1, is the world’s thinnest tablet, measuring 8.6 mm thickness, runnin..","241.99");
//    }

}

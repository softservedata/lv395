package com.softserve.edu.opencart.tools.utils;


public class LongString {

    public static String createLongString(Integer quantityOfMembers){
        StringBuffer longString=new StringBuffer("");
        for(int i=0;i<quantityOfMembers;i++){
            longString.append("a");
        }
        return longString.toString();
    }
}

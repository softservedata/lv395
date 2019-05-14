package com.softserve.edu.opencart.tools.utils_for_search_field;


import io.qameta.allure.Step;

/**
 * Class for creating long string
 */
public class LongString {
    /**
     * Method for creating long string
     * @param quantityOfMembers - how many members you want
     * @return - string
     */
    @Step("Step: create long string")
    public  String createLongString(Integer quantityOfMembers){
        StringBuffer longString=new StringBuffer("");
        for(int i=0;i<quantityOfMembers;i++){
            longString.append("a");
        }
        return longString.toString();
    }
}

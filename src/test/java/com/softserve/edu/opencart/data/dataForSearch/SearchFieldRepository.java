package com.softserve.edu.opencart.data.dataForSearch;

public class SearchFieldRepository {
    public SearchFieldRepository() {
    }
    //positive
    //all words
    public static String getDefault() {
        return "%";
    }
    //correct word
    public static String getByCorrectName(){ return "iPhone"; }
    //correct word in lower case
    public static String getByWordInLowerCase(){ return "iphone";}
    //correct word in upper case
    public static String getByWordInUpperCase(){ return "IPHONE";}
    //beginning of the word
    public static String getByPartialNameIp(){ return "ip";}
    //Letters from correct word
    public static String getByPartialNamePo(){ return "po";}
    //ending of the word
    public static String getByPartialNameN(){ return "n";}
    //spaces before the word
    public static String getByWordWithAdditionalSpacesBefore(){ return "    iphone";}
    //spaces after the word
    public static String getByWordWithAdditionalSpacesAfter(){ return  "iphone   ";}


    //can be positive or negative
    //word from description
    public static String getByWordFromDescription(){return "GB";}

    //negative
    //empty field
    public static String getByEmptyField(){return "";}
    //incorrect data
    public static String getByIncorrectData(){return "sh7483274hdsfj";}
    //incorrect data
    public static String getBySymbols(){return "&*<>)))_+!@";}
    //incorrect word
    public static String getByIncorrectWord(){return "iphane";}
    //some elements
    public static String getBySomeElement(){return "\uF04A";}

}

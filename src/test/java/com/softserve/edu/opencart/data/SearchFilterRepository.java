package com.softserve.edu.opencart.data;

public final class SearchFilterRepository {

    private SearchFilterRepository() {
    }

    //----------------------------------------------data for search field:----------------------------------------------

    //positive
    //all words
    public static ISearchFilter getDefault() {
        return SearchFilter.get().setProductSearchName("%").isUseDescription(true).build();
    }

    //correct word
    public static ISearchFilter getByCorrectName() {
        return SearchFilter.get().setProductSearchName("iPhone").isUseDescription(true).build();
    }

    //correct word in lower case
    public static ISearchFilter getByWordInLowerCase() {
        return SearchFilter.get().setProductSearchName("iphone").isUseDescription(true).build();
    }

    //correct word in upper case
    public static ISearchFilter getByWordInUpperCase() {
        return SearchFilter.get().setProductSearchName("IPHONE").isUseDescription(true).build();
    }

    //beginning of the word
    public static ISearchFilter getByPartialNameIp() {
        return SearchFilter.get().setProductSearchName("ip").isUseDescription(true).build();
    }

    //Letters from correct word
    public static ISearchFilter getByPartialNamePo() {
        return SearchFilter.get().setProductSearchName("po").isUseDescription(true).build();
    }

    //ending of the word
    public static ISearchFilter getByPartialNameN() {
        return SearchFilter.get().setProductSearchName("n").isUseDescription(true).build();
    }

    //spaces before the word
    public static ISearchFilter getByWordWithAdditionalSpacesBefore() {
        return SearchFilter.get().setProductSearchName("    iphone").isUseDescription(true).build();
    }

    //spaces after the word
    public static ISearchFilter getByWordWithAdditionalSpacesAfter() {
        return SearchFilter.get().setProductSearchName("iphone    ").isUseDescription(true).build();
    }


    //can be positive or negative
    //word from description
    public static ISearchFilter getByWordFromDescription() {
        return SearchFilter.get().setProductSearchName("GB").isUseDescription(true).build();
    }

    //negative
    //empty field
    public static ISearchFilter getByEmptyField() {
        return SearchFilter.get().setProductSearchName("").isUseDescription(true).build();
    }

    //incorrect data
    public static ISearchFilter getByIncorrectData() {
        return SearchFilter.get().setProductSearchName("sh7483274hdsfj").isUseDescription(true).build();
    }

    //incorrect data
    public static ISearchFilter getBySymbols() {
        return SearchFilter.get().setProductSearchName("&*<>)))_+!@").isUseDescription(true).build();
    }

    //incorrect word
    public static ISearchFilter getByIncorrectWord() {
        return SearchFilter.get().setProductSearchName("iphane").isUseDescription(true).build();
    }

    //some elements
    public static ISearchFilter getBySomeElement() {
        return SearchFilter.get().setProductSearchName("\uF04A").isUseDescription(true).build();
    }


    //----------------------------------------------------data for search in product description---------------------------

    public static ISearchFilter getCanonFromName() {
        return SearchFilter.get().setProductSearchName("Canon").isUseDescription(true).build();
    }
    public static ISearchFilter getCanonFromDescription(){
        return SearchFilter.get().
                setProductSearchName(
                        "an important difference when compared to").
                isUseDescription(true).build();
    }
    public static ISearchFilter getCanonIncorrectData(){
        return SearchFilter.get().setProductSearchName("iphone").isUseDescription(true).build();
    }





    public static ISearchFilter getMacSimple() {
        return SearchFilter.get().setProductSearchName("mac").isUseDescription(false).setCategoryName(new String()).isUseSubcategory(false).build();
    }

    public static ISearchFilter getMacWithFilter() {
        return SearchFilter.get().setProductSearchName("mac").isUseDescription(true).setCategoryName("Desktops").isUseSubcategory(true).build();
    }

    public static ISearchFilter getFromDescription() {
        return SearchFilter.get().setProductSearchName("i").isUseDescription(true).setCategoryName(new String()).isUseSubcategory(false).build();
    }


    //---------------------------------data for choose category--------------------------------------------------------
    public static ISearchFilter getSonyVAIOPositiveData(){
        return SearchFilter.get().setProductSearchName("o").setCategoryName("Desktops").build();
    }
    public static ISearchFilter getSonyVAIONegativeData(){
        return SearchFilter.get().setProductSearchName("o").setCategoryName("MP3 Players").build();
    }
    public static ISearchFilter getMacBookPositiveData() {
        return SearchFilter.get().setProductSearchName("is").isUseDescription(true).setCategoryName("Desktops").build();
    }
    public static ISearchFilter getMacBookNegativeData() {
        return SearchFilter.get().setProductSearchName("is").isUseDescription(true).setCategoryName("MP3 Players").build();
    }
    public static ISearchFilter getIMacFathersCategory(){
        return SearchFilter.get()
                .setProductSearchName("a")
                .isUseDescription(false)
                .setCategoryName("Desktops")
                .build();
    }
    public static ISearchFilter getIMacPositiveData(){
        return SearchFilter.get()
                .setProductSearchName("a")
                .isUseDescription(false)
                .setCategoryName("Mac")
                .build();
    }
    public static ISearchFilter getIMacNegativeDataUseSearchInDescription(){
        return SearchFilter.get()
                .setProductSearchName("when")
                .isUseDescription(true)
                .setCategoryName("Desktops")
                .build();
    }
    public static ISearchFilter getIMacPositiveDataUseSearchInDescription(){
        return SearchFilter.get()
                .setProductSearchName("when")
                .isUseDescription(true)
                .setCategoryName("Mac")
                .build();
    }

    public static ISearchFilter getIMacWrongCategoryUseSearchInDescription(){
        return SearchFilter.get()
                .setProductSearchName("when")
                .isUseDescription(true)
                .setCategoryName("Monitors")
                .build();
    }
    public static ISearchFilter getIMacWrongCategory(){
        return SearchFilter.get()
                .setProductSearchName("a")
                .isUseDescription(false)
                .setCategoryName("Monitors")
                .build();
    }






}




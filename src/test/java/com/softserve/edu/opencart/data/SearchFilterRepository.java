package com.softserve.edu.opencart.data;

public final class SearchFilterRepository {

    private SearchFilterRepository() {
    }
    
    public static SearchFilter getDefault() {
        return new SearchFilter("%", false, new String(), false);
    }
    
    public static SearchFilter getMacSimple() {
        return new SearchFilter("mac", false, new String(), false);
    }

    public static SearchFilter getMacWithFilter() {
        return new SearchFilter("mac", true, "Desktops", true);
    }
    //-----------------------------------------------------------------
    public static SearchFilter getFromDescription() {
        return new SearchFilter("i", true, new String(), false);
    }

}

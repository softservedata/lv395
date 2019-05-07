package com.softserve.edu.opencart.data;

public final class SearchFilterRepository {

    private SearchFilterRepository() {
    }

    public static ISearchFilter getDefault() {
        return SearchFilter.get().setProductSearchName("%").isUseDescription(false).setCategoryName(new String()).isUseSubcategory(false).build();
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
}



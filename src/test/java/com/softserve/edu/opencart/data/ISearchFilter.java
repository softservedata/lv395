package com.softserve.edu.opencart.data;

public interface ISearchFilter {
     String getProductSearchName();
     boolean isUseDescription();
     String getCategoryName();
     boolean isUseSubcategory();
}

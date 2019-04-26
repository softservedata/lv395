package com.softserve.edu.opencart.data;

public class SearchFilter {

    private String productSearchName;
    private boolean useDescription;
    private String categoryName;
    private boolean useSubcategory;

    public SearchFilter(String productSearchName, boolean useDescription, String categoryName, boolean useSubcategory) {
        this.productSearchName = productSearchName;
        this.useDescription = useDescription;
        this.categoryName = categoryName;
        this.useSubcategory = useSubcategory;
    }

    // setters
    
    public void setProductSearchName(String productSearchName) {
        this.productSearchName = productSearchName;
    }

    public void setUseDescription(boolean useDescription) {
        this.useDescription = useDescription;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setUseSubcategory(boolean useSubcategory) {
        this.useSubcategory = useSubcategory;
    }

    // getters

    public String getProductSearchName() {
        return productSearchName;
    }

    public boolean isUseDescription() {
        return useDescription;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public boolean isUseSubcategory() {
        return useSubcategory;
    }
    
}

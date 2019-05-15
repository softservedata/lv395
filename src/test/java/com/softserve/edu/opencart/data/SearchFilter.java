package com.softserve.edu.opencart.data;


interface IProductSearchName {
    IProductSearchBuild setProductSearchName(String productSearchName);
}

interface IProductSearchBuild{
    IProductSearchBuild isUseDescription(Boolean useDescription);

    IProductSearchBuild setCategoryName(String categoryName);

    IProductSearchBuild isUseSubcategory(Boolean useSubcategories);

    ISearchFilter build();
}
public class SearchFilter implements IProductSearchName,IProductSearchBuild,ISearchFilter{

    private String productSearchName;
    private boolean useDescription;
    private String categoryName;
    private boolean useSubcategory;


    public static IProductSearchName get() {
        return new SearchFilter();
    }

    public IProductSearchBuild setProductSearchName(String productSearchName) {
        this.productSearchName=productSearchName;
        return this;
    }


    public IProductSearchBuild isUseDescription(Boolean useDescription) {
        this.useDescription=useDescription;
        return this;
    }

    @Override
    public IProductSearchBuild setCategoryName(String categoryName) {
        this.categoryName=categoryName;
        return this;
    }

    @Override
    public IProductSearchBuild isUseSubcategory(Boolean useSubcategories) {
        this.useSubcategory=useSubcategories;
        return this;
    }

    @Override
    public ISearchFilter build() {
        return this;
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

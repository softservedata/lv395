package com.softserve.edu.opencart.tests.search_field_tests;

import com.softserve.edu.opencart.data.ProductRepository;
import com.softserve.edu.opencart.data.SearchFilter;
import com.softserve.edu.opencart.data.SearchFilterRepository;
import com.softserve.edu.opencart.pages.common.SuccessfulSearchPage;
import com.softserve.edu.opencart.tests.ATestRunner;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SearchInSubCategoriesTest extends ATestRunner {
    @DataProvider
    public Object[][] dataForPositiveTest(){
        return new Object[][] {
                //positive test search in description is not used

                //IMac category - Mac and Mac is
                //Desktops's sub category , here we use mac category
                {SearchFilterRepository.getIMacPositiveDataUseSubCategories(),
                        ProductRepository.getIMac().getName()},
                //IMac category - Mac and Mac is
                // Desktops's sub category, here we use desktop category
                {SearchFilterRepository.getIMacFathersCategoryUseSubCategories()
                        ,ProductRepository.getIMac().getName()},

                //positive test search in description is used
                {SearchFilterRepository.getIMacPositiveDataUseSearchInDescriptionUseSubCategories(),
                        ProductRepository.getIMac().getName()},

                //IMac category - Mac and Mac is
                // Desktops's sub category, here we use desktop category
                {SearchFilterRepository.getIMacFathersCategoryUseSearchInDescriptionUseSubCategories(),
                        ProductRepository.getIMac().getName()},
        };
    }

    @Test(dataProvider = "dataForPositiveTest")
    public void findSonyPositiveTest(SearchFilter searchFilterData, String producatName){
        SuccessfulSearchPage successfulSearchPage=loadApplication()
                .gotoSearchPageWithFilters()
                .searchProductsByFilter(searchFilterData);
        Assert.assertTrue(successfulSearchPage
                .getSearchCriteriaComponent()
                .getProductsContainerComponent()
                .isProductOnThePage(producatName));
    }
    @DataProvider
    public Object[][] dataForNegativeTest(){
        return new Object[][] {
                //negative test search in description is not used
                {SearchFilterRepository.getIMacWrongCategoryUseSubCategories(),
                        ProductRepository.getIMac().getName()} ,

                //negative test search in description is used

                {SearchFilterRepository.getIMacWrongCategoryUseSearchInDescriptionUseSubCategories()
                        ,ProductRepository.getIMac().getName()},

        };
    }

    @Test(dataProvider = "dataForNegativeTest")
    public void findSonyNegativeTest(SearchFilter searchFilterData, String producatName){
        SuccessfulSearchPage successfulSearchPage=loadApplication()
                .gotoSearchPageWithFilters()
                .searchProductsByFilter(searchFilterData);
        Assert.assertTrue(successfulSearchPage
                .getSearchCriteriaComponent()
                .getProductsContainerComponent()
                .isProductOnThePage(producatName)==false);
    }

}

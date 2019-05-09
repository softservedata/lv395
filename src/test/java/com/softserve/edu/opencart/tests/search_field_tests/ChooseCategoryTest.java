package com.softserve.edu.opencart.tests.search_field_tests;

import com.softserve.edu.opencart.data.ProductRepository;
import com.softserve.edu.opencart.data.SearchFilter;
import com.softserve.edu.opencart.data.SearchFilterRepository;
import com.softserve.edu.opencart.pages.common.SuccessfulSearchPage;
import com.softserve.edu.opencart.tests.ATestRunner;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.StringReader;

public class ChooseCategoryTest extends ATestRunner {
    @DataProvider
    public Object[][] dataForPositiveTest(){
        return new Object[][] {
                //positive test search in description is not used

                //IMac category - Mac and Mac is
                //Desktops's sub category , here we use mac category
                {SearchFilterRepository.getIMacPositiveData(),
                        ProductRepository.getIMac().getName()},

                //positive test search in description is used
                {SearchFilterRepository.getIMacPositiveDataUseSearchInDescription(),
                        ProductRepository.getIMac().getName()},
        };
    }
    //positive test search in description is not used
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
                {SearchFilterRepository.getIMacWrongCategory(),
                        ProductRepository.getIMac().getName()} ,
                //IMac category - Mac and Mac is
                // Desktops's sub category, here we use desktop category
                {SearchFilterRepository.getIMacFathersCategory()
                        ,ProductRepository.getIMac().getName()},

                //positive test search in description is used
                {SearchFilterRepository.getIMacNegativeDataUseSearchInDescription(),
                        ProductRepository.getIMac().getName()},
        };
    }
    //negative test(everything  is like in previous data except category)
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

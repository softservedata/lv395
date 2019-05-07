package com.softserve.edu.opencart.tests.search_field_tests;

import com.softserve.edu.opencart.data.ISearchFilter;
import com.softserve.edu.opencart.data.SearchFilterRepository;
import com.softserve.edu.opencart.pages.common.ProductPage;
import com.softserve.edu.opencart.pages.common.ProductsContainerComponent;
import com.softserve.edu.opencart.pages.common.SuccessfulSearchPage;
import com.softserve.edu.opencart.tests.ATestRunner;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class SearchInProductDescriptionTest extends ATestRunner {
    @DataProvider
    public Object[][] searchFilterData() {
        return new Object[][] {
                { SearchFilterRepository.getFromDescription() },
        };
    }
    @Test(dataProvider = "searchFilterData")
    public void newTest(ISearchFilter searchData){
        SuccessfulSearchPage successfulSearchPage = loadApplication()
                .searchProducts(searchData.getProductSearchName())
                .searchProductsByFilter(searchData);
        ProductsContainerComponent productComponents=successfulSearchPage
                .getSearchCriteriaComponent()
                .getProductsContainerComponent();

        for(int i=0;i<productComponents.getProductComponents().size();i++) {
            ProductPage productPage=successfulSearchPage
                    .chooseProduct(productComponents
                            .getProductComponents().get(i));
            Assert.assertTrue(productPage.getProduct().getPartialDescription()
                    .getText().contains(searchData.getProductSearchName()));
            successfulSearchPage=productPage.gotoSearchPage();
            productComponents=successfulSearchPage
                    .getSearchCriteriaComponent()
                    .getProductsContainerComponent();
        }
    }
//    @DataProvider
//    public Object[][] searchNegativeData() {
//        return new Object[][]{
//                {SearchFieldRepository.getByEmptyField()},
//                {SearchFieldRepository.getByIncorrectData()},
//                {SearchFieldRepository.getBySymbols()},
//                {SearchFieldRepository.getByIncorrectWord()},
//                {SearchFieldRepository.getBySomeElement()},
//                {SearchFieldRepository.getByWordFromDescription()}
//        };
//    }
//
//    @Test(dataProvider = "searchNegativeData")
//    public void searchFieldNegativeTest(String searchData) {
//        //Steps
//        UnsuccessfulSearchPage unsuccessfulSearchPage = loadApplication().searchIncorrectProducts(searchData);
//        Assert.assertEquals(unsuccessfulSearchPage.getMessage(), "There is no product that matches the search criteria.");
//
//    }
}

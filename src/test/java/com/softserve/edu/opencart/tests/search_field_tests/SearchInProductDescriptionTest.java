package com.softserve.edu.opencart.tests.search_field_tests;

import com.softserve.edu.opencart.data.Product;
import com.softserve.edu.opencart.data.ProductRepository;
import com.softserve.edu.opencart.data.SearchFilter;
import com.softserve.edu.opencart.data.SearchFilterRepository;
import com.softserve.edu.opencart.pages.shop.ProductComponent;
import com.softserve.edu.opencart.pages.shop.ProductPage;
import com.softserve.edu.opencart.pages.shop.ProductsContainerComponent;
import com.softserve.edu.opencart.pages.common.SuccessfulSearchPage;
import com.softserve.edu.opencart.pages.common.UnsuccessfulSearchPage;
import com.softserve.edu.opencart.tests.ATestRunner;
import com.softserve.edu.opencart.tools.utils_for_search_field.PageDoNotExistException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;


public class SearchInProductDescriptionTest extends ATestRunner {
//    @DataProvider
//    public Object[][] searchFilterData() {
//        return new Object[][] {
//                { SearchFilterRepository.getFromDescription() },
//        };
//    }
//    @Test(dataProvider = "searchFilterData")
//    public void newTest(SearchFilter searchData){
//        SuccessfulSearchPage successfulSearchPage = loadApplication()
//                .searchProducts(searchData)
//                .searchProductsByFilter(searchData);
//        ProductsContainerComponent productComponents=successfulSearchPage
//                .getSearchCriteriaComponent()
//                .getProductsContainerComponent();
//
//        for(int i=1;i<productComponents.getProductComponents().size();i++) {
//            ProductPage productPage=successfulSearchPage
//                    .chooseProduct(productComponents
//                            .getProductComponents().get(i));
//            Assert.assertTrue(productPage.getProduct().getPartialDescription()
//                    .getText().contains(searchData.getProductSearchName()));
//            successfulSearchPage=productPage.gotoSearchPage();
//            productComponents=successfulSearchPage
//                    .getSearchCriteriaComponent()
//                    .getProductsContainerComponent();
//        }
//    }
//@DataProvider
//    public Object[][] searchNegativeData() {
//        return new Object[][]{
//                {SearchFilterRepository.getByEmptyField()},
//                {SearchFilterRepository.getByIncorrectData()},
//                {SearchFilterRepository.getBySymbols()},
//                {SearchFilterRepository.getByIncorrectWord()},
//                {SearchFilterRepository.getBySomeElement()},
//                {SearchFilterRepository.getByWordFromDescription()}
//        };
//    }
//
//    @Test(dataProvider = "searchNegativeData")
//    public void searchFieldNegativeTest(SearchFilter searchData) {
//        //Steps
//        UnsuccessfulSearchPage unsuccessfulSearchPage = loadApplication().unsuccessfulSearch(searchData);
//        //Check
//        Assert.assertEquals(unsuccessfulSearchPage.getMessage(),
//                UnsuccessfulSearchPage.THERE_IS_NO_PRODUCT_THAT_MATCHES_THE_SEARCH_CRITERIA);
//
//    }

    @DataProvider
    public Object[][] dataForFindCanon(){
        return new Object[][]{
                {ProductRepository.getCanonEOS5D().getName(),
                        SearchFilterRepository.getCanonFromName()},
                {ProductRepository.getCanonEOS5D().getName()
                        ,SearchFilterRepository.getCanonFromDescription()},
        };

    }
    @Test(dataProvider = "dataForFindCanon")
    public void searchCanonTest(String productWeWantToFind, SearchFilter searchFilterWeUse) throws PageDoNotExistException {
        ProductsContainerComponent productsOnThePage=loadApplication()
                .gotoSearchPageWithFilters()
                .searchProductsByFilter(searchFilterWeUse)
                .getSearchCriteriaComponent()
                .getProductsContainerComponent();
        Assert.assertTrue(productsOnThePage.isProductOnThePage(productWeWantToFind));
    }
    @Test
    public void negativeTestSearchCanonTest() throws PageDoNotExistException {
        ProductsContainerComponent productsOnThePage=loadApplication()
                .searchProducts(ProductRepository.getCanonEOS5D().getName())
                .searchProductsByFilter(SearchFilterRepository.getCanonIncorrectData())
                .getSearchCriteriaComponent()
                .getProductsContainerComponent();
        Assert.assertTrue(productsOnThePage
                .isProductOnThePage(ProductRepository
                        .getCanonEOS5D()
                        .getName())==false);
    }




}

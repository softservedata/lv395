package com.softserve.edu.opencart.tests.search_field_tests;

import com.softserve.edu.opencart.data.ProductRepository;
import com.softserve.edu.opencart.data.SearchFilter;
import com.softserve.edu.opencart.data.SearchFilterRepository;

import com.softserve.edu.opencart.pages.shop.ProductsContainerComponent;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Tests for using "search in product description" checkbox
 * @author Ratushniak Iryna
 */
@Epic("Search Testing")
@Feature("SearchInProductDescriptionTest")
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

    /**
     * Here we have data for searching product,
     * using search in product description checkbox.
     * We can find product, using name/partial name of product
     * and using something from product description.
     * @return - data for finding Canon
     */
    @DataProvider
    public Object[][] dataForFindCanon(){
        return new Object[][]{
                {ProductRepository.getCanonEOS5D().getName(),
                        SearchFilterRepository.getCanonFromName()},
                {ProductRepository.getCanonEOS5D().getName()
                        ,SearchFilterRepository.getCanonFromDescription()},
        };

    }

    /**
     * Positive test for finding Canon.
     * Here we input name/partial name
     * in the search field and we are using
     * check in product description checkbox.
     * We are expecting to see product ,that
     * we are looking for on the page
     * @param productWeWantToFind- Canon
     * @param searchFilterWeUse - search filter,
     *                         that we use for finding canon
     */
    @Test(dataProvider = "dataForFindCanon")
    @Severity(SeverityLevel.NORMAL)
    @Description("Positive test for finding Canon." +
            "Here we input name/partial name"+
            "in the search field and we are using" +
            "check in product description checkbox." +
            "We are expecting to see product ,that" +
            "we are looking for on the page")
    @Story("Valid data for searching")
    public void searchInProductDescriptionPositiveTest(String productWeWantToFind,
                                                       SearchFilter searchFilterWeUse){
        //Steps
        ProductsContainerComponent productsOnThePage=loadApplication()
                .gotoSearchPageWithFilters()
                .searchProductsByFilter(searchFilterWeUse)
                .getSearchCriteriaComponent()
                .getProductsContainerComponent();

        //Check
        Assert.assertTrue(productsOnThePage.isProductOnThePage(productWeWantToFind));
    }

    /**
     * Here we have negative test.
     * If we use some  incorrect data for
     * product searching
     * we expect that product won`t be shown
     * on the page
     */
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Here we have negative test." +
            "If we use some  incorrect data for" +
            "product searching" +
            "we expect that product won`t be shown" +
            "on the page")
    @Story("Invalid data for searching")
    public void searchInProductDescriptionNegativeTest()  {
        ProductsContainerComponent productsOnThePage=loadApplication()
                .gotoSearchPageWithFilters()
                .searchProductsByFilter(SearchFilterRepository
                        .getCanonIncorrectData())
                .getSearchCriteriaComponent()
                .getProductsContainerComponent();
        Assert.assertFalse(productsOnThePage
                .isProductOnThePage(ProductRepository
                        .getCanonEOS5D()
                        .getName()));
    }




}

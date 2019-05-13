package com.softserve.edu.opencart.tests.search_field_tests;

import com.softserve.edu.opencart.data.IProduct;
import com.softserve.edu.opencart.data.ProductRepository;
import com.softserve.edu.opencart.data.SearchFilter;
import com.softserve.edu.opencart.data.SearchFilterRepository;
import com.softserve.edu.opencart.pages.common.SuccessfulSearchPage;
import com.softserve.edu.opencart.pages.common.UnsuccessfulSearchPage;
import com.softserve.edu.opencart.pages.shop.ProductComponent;
import com.softserve.edu.opencart.tests.ATestRunner;
import com.softserve.edu.opencart.tools.utils.LongString;

import com.softserve.edu.opencart.tools.utils_for_search_field.ElementDoNotExistException;
import com.softserve.edu.opencart.tools.utils_for_search_field.PageDoesNotExistException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

/**
 * This class includes tests for search field
 * @author Iryna Ratushniak
 */
public class SearchFieldTest extends ATestRunner {
    /**
     * Here we have possible ata for positive testing search field
     * @return data for search field
     */
    @DataProvider
    public Object[][] searchPositiveData() {
        return new Object[][]{
                {SearchFilterRepository.getByCorrectName().getProductSearchName()},
                {SearchFilterRepository.getByPartialNameIp().getProductSearchName()},
                {SearchFilterRepository.getByPartialNameN().getProductSearchName()},
                {SearchFilterRepository.getByPartialNamePo().getProductSearchName()},
                {SearchFilterRepository.getByWordInLowerCase().getProductSearchName()},
                {SearchFilterRepository.getByWordInUpperCase().getProductSearchName()},
                {SearchFilterRepository.getByWordWithAdditionalSpacesAfter().getProductSearchName()},
                {SearchFilterRepository.getByWordWithAdditionalSpacesBefore().getProductSearchName()},
        };
    }

    /**
     * Positive test for search field.
     * Here we are checking that all products,
     * that we will see on the page,
     * include data we input in the search field
     * @param searchData - possible data for search field
     * @throws PageDoesNotExistException - if page, that we want to use/to see
     *                                 do not exist this exception will be thrown
     */
    @Test(dataProvider = "searchPositiveData")
    public void searchFieldPositiveTest(String searchData) throws PageDoesNotExistException{
        //Steps
        SuccessfulSearchPage successfulSearchPage = loadApplication().
                searchProducts(searchData);
        // Check
        List<ProductComponent> productComponents = successfulSearchPage.
                getSearchCriteriaComponent().
                getProductsContainerComponent().
                getProductComponents();
        for (ProductComponent productComponent : productComponents) {
            Assert.assertTrue(productComponent.getName().
                    getText().toLowerCase().contains(searchData.toLowerCase().trim()));
        }
    }

    /**
     * Here we have data for negative testing
     * @return -data for negative testing
     */
    @DataProvider
    public Object[][] searchNegativeData() {
        return new Object[][]{
                {SearchFilterRepository.getByEmptyField()},
                {SearchFilterRepository.getByIncorrectData()},
                {SearchFilterRepository.getBySymbols()},
                {SearchFilterRepository.getByIncorrectWord()},
                {SearchFilterRepository.getBySomeElement()},
                {SearchFilterRepository.getByWordFromDescription()}
        };
    }

    /**
     * Negative test for search field.
     * Here we are checking that if we input wrong data,
     * we will see unsuccessful searchPage
     * @param searchData - possible data for search field
     * @throws PageDoesNotExistException - if page, that we want to use/to see
     *                                 do not exist this exception will be thrown
     */
    @Test(dataProvider = "searchNegativeData")
    public void searchFieldNegativeTest(SearchFilter searchData) throws PageDoesNotExistException {
        //Steps
        UnsuccessfulSearchPage unsuccessfulSearchPage = loadApplication()
                .unsuccessfulSearch(searchData.getProductSearchName());
        Assert.assertEquals(unsuccessfulSearchPage.getMessage(),
                UnsuccessfulSearchPage
                        .THERE_IS_NO_PRODUCT_THAT_MATCHES_THE_SEARCH_CRITERIA);

    }

    /**
     * It is positive test for search field.
     * If we input "%" in the search field we
     * are expecting to see all products.
     * Here we are checking, that list of all products,
     * that we see, contains all existing products
     *
     * @throws PageDoesNotExistException - if page, that we want to use/to see
     *                                  do not exist this exception will be thrown
     * @throws ElementDoNotExistException -if element, that we want to use/to see
     *                                  do not exist this exception will be thrown
     */
    @Test
    public void findAllProductsTest() throws  PageDoesNotExistException, ElementDoNotExistException {
        SuccessfulSearchPage successfulSearchPage = loadApplication().searchProducts("%");
        List<String> productComponents = successfulSearchPage
                .getSearchCriteriaComponent()
                .getProductsContainerComponent()
                .getProductComponentsName();
        if (successfulSearchPage.isThereMoreThenOnePage()) {
            List<String> productFromSecondPage = successfulSearchPage.gotoNextPage()
                    .getSearchCriteriaComponent()
                    .getProductsContainerComponent()
                    .getProductComponentsName();
            productComponents.addAll(productFromSecondPage);
        }
        for (IProduct product : ProductRepository.getAllProducts()) {
            Assert.assertTrue(productComponents.contains(product.getName()));
        }


    }
    /**
     * Negative test for search field.
     * If we input value longer then 9000 characters
     * we are expecting to see some page with information
     * about wrong actions.
     * @throws PageDoesNotExistException - if page, that we want to use/to see
     *                                 do not exist this exception will be thrown
     */
    @Test(expectedExceptions = PageDoesNotExistException.class)
    public void stressSearchFieldTest() throws PageDoesNotExistException, IOException {
        String dataForField = new LongString().createLongString(9000);
        loadApplication().unsuccessfulSearch(dataForField);
        takeScreenshot("search_field_stress_test");
    }


}

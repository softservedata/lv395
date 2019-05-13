package com.softserve.edu.opencart.tests.search_field_tests;

import com.softserve.edu.opencart.data.ProductRepository;
import com.softserve.edu.opencart.data.SearchFilter;
import com.softserve.edu.opencart.data.SearchFilterRepository;
import com.softserve.edu.opencart.pages.common.SuccessfulSearchPage;
import com.softserve.edu.opencart.tests.ATestRunner;
import com.softserve.edu.opencart.tools.utils_for_search_field.PageDoesNotExistException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


/**
 * This class is for testing "choose category element"
 * @author Ratushniak Iryna
 */
public class ChooseCategoryTest extends ATestRunner {
    /**
     * Here we have data for positive testing search
     * in sub categories.
     *
     *This data can be divided for two groups:
     * 1)do not use search in product description
     * 2)do not use search in product description
     *
     * For finding some product we can use :
     * 1)category of this products
     *
     * @return - data for positive testing
     */
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
    /**
     * Here we have test for positive testing
     * Here we are trying to find some product,
     * using choose category element
     * (We are expecting to see product, we are looking for,
     * becouse we are using correct data)
     * @param searchFilterData - filter, that will be used in the test
     * @param productName -  name/parial name of product we are looking for
     * @throws PageDoesNotExistException - it is possible, that page that
     *                                   we want to see/to use doesnot exist
     */


    @Test(dataProvider = "dataForPositiveTest")
    public void chooseCategoryPositiveTest(SearchFilter searchFilterData, String productName) throws PageDoesNotExistException {
        SuccessfulSearchPage successfulSearchPage=loadApplication()
                .gotoSearchPageWithFilters()
                .searchProductsByFilter(searchFilterData);
        Assert.assertTrue(successfulSearchPage
                .getSearchCriteriaComponent()
                .getProductsContainerComponent()
                .isProductOnThePage(productName));
    }

    /**
     * Here we have data for negative testing search
     *  in sub categories.
     *This data can be divided for two groups:
     * 1)do not use search in product description
     * 2)do not use search in product description
     *
     * For finding some product we can`t use :
     *      1)father category of product's category (we have to use only product`s category)
     *      2)some incorrect category
     *
     * @return - data for negative test
     */
    @DataProvider
    public Object[][] dataForNegativeTest(){
        return new Object[][] {
                //negative test search in description is not used
                   {SearchFilterRepository.getIMacWrongCategory(),
                        ProductRepository.getIMac().getName()} ,
                   //IMac category - Mac and Mac is
                   // Desktops's sub category, here we use desktop category
                   {SearchFilterRepository.getIMacFathersCategory()
                        ,ProductRepository.getIMac().getName()},

                //negative test search in description is used

                {SearchFilterRepository.getIMacWrongCategoryUseSearchInDescription()
                        ,ProductRepository.getIMac().getName()},
                //IMac category - Mac and Mac is
                // Desktops's sub category, here we use desktop category
                {SearchFilterRepository.getIMacFathersCategoryUseSearchInDescription(),
                        ProductRepository.getIMac().getName()},
        };
    }
    /**
     * Here we have negative test
     * We are choosing wrong category
     * (category, that wee choose, is not product`s category
     * and is not father of products category)
     * We are expecting, that product, that we want to find,
     * won`t be present on the page
     * @param searchFilterData - filter, that will be used
     *                        for finding product
     * @param productName - name/partial name of product we
     *                     want to find
     * @throws PageDoesNotExistException- it is possible, that page that
     *                                   we want to see/to use doesnot exist
     */
    @Test(dataProvider = "dataForNegativeTest")
    public void chooseCategoryNegativeTest(SearchFilter searchFilterData, String productName) throws PageDoesNotExistException {
        SuccessfulSearchPage successfulSearchPage=loadApplication()
                .gotoSearchPageWithFilters()
                .searchProductsByFilter(searchFilterData);
        Assert.assertTrue(successfulSearchPage
                .getSearchCriteriaComponent()
                .getProductsContainerComponent()
                .isProductOnThePage(productName)==false);
    }

}

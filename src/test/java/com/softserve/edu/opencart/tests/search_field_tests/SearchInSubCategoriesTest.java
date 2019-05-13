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
 * This class includes test for
 * search in sub categories
 * checkbox
 * @author Ratushniak Iryna
 */
public class SearchInSubCategoriesTest extends ATestRunner {
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
     * 2)father category of product`s category
     *
     * @return - data for positive testing
     */
    @DataProvider
    public Object[][] dataForPositiveTest(){
        return new Object[][] {
                //1)positive test search in description is not used

                //IMac category - Mac and Mac is
                //Desktops's sub category , here we use mac category
                {SearchFilterRepository.getIMacPositiveDataUseSubCategories(),
                        ProductRepository.getIMac().getName()},
                //IMac category - Mac and Mac is
                // Desktops's sub category, here we use desktop category
                {SearchFilterRepository.getIMacFathersCategoryUseSubCategories()
                        ,ProductRepository.getIMac().getName()},

                //2)positive test search in description is used
                {SearchFilterRepository.getIMacPositiveDataUseSearchInDescriptionUseSubCategories(),
                        ProductRepository.getIMac().getName()},

                //IMac category - Mac and Mac is
                // Desktops's sub category, here we use desktop category
                {SearchFilterRepository.getIMacFathersCategoryUseSearchInDescriptionUseSubCategories(),
                        ProductRepository.getIMac().getName()},
        };
    }

    /**
     * Here we have test for positive testing
     * Here we are trying to find some product,
     * using search in subcategory checkbox.
     * (We are expecting to see product, we are looking for,
     * becouse we are using correct data)
     * @param searchFilterData - filter, that will be used in the test
     * @param productName -  name/parial name of product we are looking for
     * @throws PageDoesNotExistException - it is possible, that page that
     *                                   we want to see/to use doesnot exist
     */

    @Test(dataProvider = "dataForPositiveTest")
    public void findSonyPositiveTest(SearchFilter searchFilterData, String productName) throws PageDoesNotExistException {
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
     * @return - data for negative test
     */
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
    public void findSonyNegativeTest(SearchFilter searchFilterData, String productName) throws PageDoesNotExistException {
        SuccessfulSearchPage successfulSearchPage=loadApplication()
                .gotoSearchPageWithFilters()
                .searchProductsByFilter(searchFilterData);
        Assert.assertTrue(successfulSearchPage
                .getSearchCriteriaComponent()
                .getProductsContainerComponent()
                .isProductOnThePage(productName)==false);
    }

}

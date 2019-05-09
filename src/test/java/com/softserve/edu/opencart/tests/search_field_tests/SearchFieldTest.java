package com.softserve.edu.opencart.tests.search_field_tests;

import com.softserve.edu.opencart.data.SearchFilter;
import com.softserve.edu.opencart.data.SearchFilterRepository;
import com.softserve.edu.opencart.pages.common.SuccessfulSearchPage;
import com.softserve.edu.opencart.pages.common.UnsuccessfulSearchPage;
import com.softserve.edu.opencart.pages.shop.ProductComponent;
import com.softserve.edu.opencart.tests.ATestRunner;
import com.softserve.edu.opencart.tools.utils.LongString;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class SearchFieldTest extends ATestRunner {
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

    @Test(dataProvider = "searchPositiveData")
    public void positiveTest(String searchData) {
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

    @Test(dataProvider = "searchNegativeData")
    public void searchFieldNegativeTest(SearchFilter searchData) {
        //Steps
        UnsuccessfulSearchPage unsuccessfulSearchPage = loadApplication()
                .unsuccessfulSearch(searchData.getProductSearchName());
        Assert.assertEquals(unsuccessfulSearchPage.getMessage(),
                UnsuccessfulSearchPage
                        .THERE_IS_NO_PRODUCT_THAT_MATCHES_THE_SEARCH_CRITERIA);

    }
   //to do use % and db
//    @Test
////    public void findAllProducts(){
////
////    }

    /**
     * Negative test for search field.
     * We input value longer then 9000 characters
     */
    @Test
    public void stressSearchFieldTest() {
        String dataForField = LongString.createLongString(9000);
        boolean applicationWorksCorrect=true;
        try{
        loadApplication().unsuccessfulSearch(dataForField);
            }
        catch (Exception e){applicationWorksCorrect=false;}
        Assert.assertTrue(applicationWorksCorrect);
    }


}

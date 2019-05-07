package com.softserve.edu.opencart.tests.search_field_tests;

import com.softserve.edu.opencart.data.data_for_search.SearchFieldRepository;
import com.softserve.edu.opencart.pages.common.ProductComponent;
import com.softserve.edu.opencart.pages.common.SuccessfulSearchPage;
import com.softserve.edu.opencart.pages.common.UnsuccessfulSearchPage;
import com.softserve.edu.opencart.tests.ATestRunner;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class SearchFieldTest extends ATestRunner {
    @DataProvider
    public Object[][] searchPositiveData() {
        return new Object[][]{
                {SearchFieldRepository.getByCorrectName()},
                {SearchFieldRepository.getByPartialNameIp()},
                {SearchFieldRepository.getByPartialNameN()},
                {SearchFieldRepository.getByPartialNamePo()},
                {SearchFieldRepository.getByWordInLowerCase()},
                {SearchFieldRepository.getByWordInUpperCase()},
                {SearchFieldRepository.getByWordWithAdditionalSpacesAfter()},
                {SearchFieldRepository.getByWordWithAdditionalSpacesBefore()},
        };
    }

    @Test(dataProvider = "searchPositiveData")
    public void positiveTest(String searchData) {
        //Steps

        SuccessfulSearchPage successfulSearchPage = loadApplication().searchProducts(searchData);
        // Check
        List<ProductComponent> productComponents = successfulSearchPage.getSearchCriteriaComponent().getProductsContainerComponent().getProductComponents();
        for (ProductComponent productComponent : productComponents) {
            Assert.assertTrue(productComponent.getName().getText().toLowerCase().contains(searchData.toLowerCase().trim()));
        }
    }

    @DataProvider
    public Object[][] searchNegativeData() {
        return new Object[][]{
                {SearchFieldRepository.getByEmptyField()},
                {SearchFieldRepository.getByIncorrectData()},
                {SearchFieldRepository.getBySymbols()},
                {SearchFieldRepository.getByIncorrectWord()},
                {SearchFieldRepository.getBySomeElement()},
                {SearchFieldRepository.getByWordFromDescription()}
        };
    }

    @Test(dataProvider = "searchNegativeData")
    public void searchFieldNegativeTest(String searchData) {
        //Steps
        UnsuccessfulSearchPage unsuccessfulSearchPage = loadApplication().searchIncorrectProducts(searchData);
        Assert.assertEquals(unsuccessfulSearchPage.getMessage(), "There is no product that matches the search criteria.");

    }

    /**
     * Negative test for search field.
     * We input value longer then 9000 characters
     */
    @Test
    public void stressSearchFieldTest() {
        StringBuffer dataForField = new StringBuffer("a");
        for (int i = 0; i < 9000; i++) {
            dataForField.append("a");
        }
        boolean elementIsOnPage = true;
        try {
            loadApplication().searchProducts(dataForField.toString()).getCategoryDropdownOptions();
        } catch (Exception  e) {
            elementIsOnPage = false;
        }
        Assert.assertTrue(elementIsOnPage);


    }


}

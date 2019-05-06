package com.softserve.edu.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.softserve.edu.opencart.data.Product;
import com.softserve.edu.opencart.data.ProductRepository;
import com.softserve.edu.opencart.data.SearchFilter;
import com.softserve.edu.opencart.data.SearchFilterRepository;
import com.softserve.edu.opencart.pages.common.HomePage;
import com.softserve.edu.opencart.pages.common.ProductComponent;
import com.softserve.edu.opencart.pages.common.SuccessfulSearchPage;

public class SearchTest extends ATestRunner {

    @DataProvider // (parallel = true)
    public Object[][] searchData() {
        return new Object[][] {
            { SearchFilterRepository.getMacSimple(), ProductRepository.getMacBook() },
        };
    }

    //@Test(dataProvider = "searchData")
    public void checkSearch(SearchFilter searchData, Product product) throws Exception {
        // Steps
        SuccessfulSearchPage successfulSearchPage = loadApplication()
                .searchProducts(searchData);
        // Check
        ProductComponent productComponent = successfulSearchPage
                .getSearchCriteriaComponent()
                .getProductsContainerComponent()
                .getProductComponentByName(product.getName()); 
        Assert.assertTrue(productComponent.getPriceText()
                .contains(product.getPriceDollarExTax()));
        // Steps
        HomePage homePage = successfulSearchPage
                .gotoHomePage();
        // Check
        Assert.assertTrue(homePage
                .getSlideshow0FirstImageAttributeSrcText()
                .contains(HomePage.IPHONE_IMAGE));
    }
    
    @DataProvider // (parallel = true)
    public Object[][] searchFilterData() {
        return new Object[][] {
            { SearchFilterRepository.getMacWithFilter(), ProductRepository.getMacBook() },
        };
    }

    @Test(dataProvider = "searchFilterData")
    public void checkSearchFilter(SearchFilter searchData, Product product) throws Exception {
        // Steps
        SuccessfulSearchPage successfulSearchPage = loadApplication()
                .searchProducts(searchData)
                .searchProductsByFilter(searchData);
        Thread.sleep(4000);
        // Check
        ProductComponent productComponent = successfulSearchPage
                .getSearchCriteriaComponent()
                .getProductsContainerComponent()
                .getProductComponentByName(product.getName()); 
        Assert.assertTrue(productComponent.getPriceText()
                .contains(product.getPriceDollarExTax()));
        // Steps
        HomePage homePage = successfulSearchPage
                .gotoHomePage();
        // Check
        Assert.assertTrue(homePage
                .getSlideshow0FirstImageAttributeSrcText()
                .contains(HomePage.IPHONE_IMAGE));
    }
}

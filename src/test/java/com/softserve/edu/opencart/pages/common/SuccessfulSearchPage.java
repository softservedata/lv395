package com.softserve.edu.opencart.pages.common;

import com.softserve.edu.opencart.pages.shop.ProductComponent;
import com.softserve.edu.opencart.pages.shop.ProductPage;
import com.softserve.edu.opencart.tools.utils_for_search_field.ElementDoNotExistException;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.softserve.edu.opencart.data.Currencies;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SuccessfulSearchPage extends ASearchPart {
    protected final Logger log = Logger.getLogger(this.getClass());
    private SearchCriteriaComponent searchCriteriaComponent;
    private final String PAGINATION_IS_NOT_PRESENT_ON_THE_PAGE = "Pagination is not present on the page!!!!";


    public SuccessfulSearchPage(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements() {
        searchCriteriaComponent = new SearchCriteriaComponent(driver);
    }

    // Page Object
    public List<WebElement> findPagination() {
        return driver.findElements(By.cssSelector("ul.pagination li a"));
    }

    public void clickPaginationIfOnFirstPageGotoNext() {
        try {
            findPagination().get(1).click();
        } catch (Exception e) {
            log.error(PAGINATION_IS_NOT_PRESENT_ON_THE_PAGE);
            throw new ElementDoNotExistException(PAGINATION_IS_NOT_PRESENT_ON_THE_PAGE);
        }

    }

    public void clickPaginationIfOnSecondPageGotoPreviou() {
        try {
            findPagination().get(1).click();
        } catch (Exception e) {
            log.error(PAGINATION_IS_NOT_PRESENT_ON_THE_PAGE);
            throw new ElementDoNotExistException(PAGINATION_IS_NOT_PRESENT_ON_THE_PAGE);
        }
    }

    // searchCriteriaComponent

    public SearchCriteriaComponent getSearchCriteriaComponent() {
        return searchCriteriaComponent;
    }

    // Functional


    // Business Logic
    public ProductPage chooseProduct(ProductComponent product) {
        product.clickName();
        return new ProductPage(driver, product);
    }

    @Step("Step: goto next page with products")
    public SuccessfulSearchPage gotoNextPage() {
        clickPaginationIfOnFirstPageGotoNext();
        return new SuccessfulSearchPage(driver);
    }

    public SuccessfulSearchPage gotoPreviousPage() {
        clickPaginationIfOnSecondPageGotoPreviou();
        return new SuccessfulSearchPage(driver);
    }

    public boolean isThereMoreThenOnePage() {
        if (findPagination().size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public SuccessfulSearchPage chooseCurrency(Currencies currency) {
        clickCurrencyByPartialName(currency);
        return new SuccessfulSearchPage(driver);
    }


}

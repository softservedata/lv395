package com.softserve.edu.opencart.pages.common;

import org.openqa.selenium.WebDriver;

import com.softserve.edu.opencart.data.Currencies;

public class SuccessfulSearchPage extends ASearchPart {

    private SearchCriteriaComponent searchCriteriaComponent;
    
    public SuccessfulSearchPage(WebDriver driver) {
        super(driver);
        initElements();
    }
    
    private void initElements() {
        searchCriteriaComponent = new SearchCriteriaComponent(driver);
    }

    // Page Object

    // searchCriteriaComponent
    public SearchCriteriaComponent getSearchCriteriaComponent() {
        return searchCriteriaComponent;
    }
    
    // Functional

    // Business Logic
    
    public SuccessfulSearchPage chooseCurrency(Currencies currency) {
        clickCurrencyByPartialName(currency);
        return new SuccessfulSearchPage(driver);
    }
}

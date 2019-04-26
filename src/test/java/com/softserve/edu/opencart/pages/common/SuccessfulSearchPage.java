package com.softserve.edu.opencart.pages.common;

import org.openqa.selenium.WebDriver;

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
}

package com.softserve.edu.opencart.pages.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.softserve.edu.opencart.data.SearchFilter;

public abstract class AHeaderPart {
    
    protected final String TAG_ATTRIBUTE_VALUE = "value";
    protected final String TAG_ATTRIBUTE_SRC = "src";
    //
    protected WebDriver driver;
    //
    private WebElement searchField;
    private WebElement searchButton;
    private WebElement logo;

    protected AHeaderPart(WebDriver driver) {
        this.driver = driver;
        initElements();
    }

    private void initElements() {
        searchField = driver.findElement(By.name("search"));
        searchButton = driver.findElement(By.cssSelector("button.btn.btn-default"));
        logo = driver.findElement(By.id("logo"));
    }

    // Page Object

    // searchField
    public WebElement getSearchField() {
        return searchField;
    }
    
    public String getSearchFieldText() {
        return getSearchField().getAttribute(TAG_ATTRIBUTE_VALUE);
    }
    
    public void clearSearchField() {
        getSearchField().clear();
    }
    
    public void clickSearchField() {
        getSearchField().click();
    }
    
    public void setSearchField(String text) {
        getSearchField().sendKeys(text);
    }
    
    // searchButton
    public WebElement getSearchButton() {
        return searchButton;
    }
    public void clickSearchButton() {
        getSearchButton().click();
    }
    
    // logo
    public WebElement getLogo() {
        return logo;
    }

    public void clickLogo() {
        getLogo().click();
    }
    
    // Functional

    // searchField
    private void fillSearchField(String name) {
        clickSearchField();
        clearSearchField();
        setSearchField(name);
    }
    
    // Business Logic
    
    public HomePage gotoHomePage() {
        clickLogo();
        return new HomePage(driver); 
    }
 
    public SuccessfulSearchPage searchProducts(SearchFilter searchItems) {
        fillSearchField(searchItems.getProductSearchName());
        clickSearchButton();
        return new SuccessfulSearchPage(driver);
    }
}

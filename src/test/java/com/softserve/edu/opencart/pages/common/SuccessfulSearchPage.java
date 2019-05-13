package com.softserve.edu.opencart.pages.common;

import com.softserve.edu.opencart.pages.shop.ProductComponent;
import com.softserve.edu.opencart.pages.shop.ProductPage;
import com.softserve.edu.opencart.tools.utils_for_search_field.ElementDoNotExistException;
import com.softserve.edu.opencart.tools.utils_for_search_field.PageDoesNotExistException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.softserve.edu.opencart.data.Currencies;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SuccessfulSearchPage extends ASearchPart {

    private SearchCriteriaComponent searchCriteriaComponent;
    private WebElement secondPage;


    public SuccessfulSearchPage(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements() {
        searchCriteriaComponent = new SearchCriteriaComponent(driver);
    }

    // Page Object
    public List<WebElement> findPagination(){
        return driver.findElements(By.cssSelector("ul.pagination li a"));
    }
    public void clickPaginationIfOnFirstPageGotoNext ()throws ElementDoNotExistException {
        try{findPagination().get(1).click();}
        catch (Exception e){
            throw new ElementDoNotExistException("Pagination is not present on the page!!!!");
        }
    }
    public void clickPaginationIfOnSecondPageGotoPreviou() throws ElementDoNotExistException{
        findPagination().get(1).click();
    }
    // searchCriteriaComponent
    public SearchCriteriaComponent getSearchCriteriaComponent(){
        return searchCriteriaComponent;
    }

    // Functional



    // Business Logic
    public ProductPage chooseProduct(ProductComponent product) {
        product.clickName();
        return new ProductPage(driver, product);
    }

    public SuccessfulSearchPage gotoNextPage() throws PageDoesNotExistException, ElementDoNotExistException {
       clickPaginationIfOnFirstPageGotoNext();
        return new SuccessfulSearchPage(driver);
    }

    public SuccessfulSearchPage gotoPreviousPage() throws PageDoesNotExistException, ElementDoNotExistException {
       clickPaginationIfOnSecondPageGotoPreviou();
        return new SuccessfulSearchPage(driver);
    }

    public boolean isThereMoreThenOnePage() {
        try {
            driver.findElements(By.cssSelector("ul.pagination li a"));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public SuccessfulSearchPage chooseCurrency(Currencies currency) {
        clickCurrencyByPartialName(currency);
        return new SuccessfulSearchPage(driver);
    }


}

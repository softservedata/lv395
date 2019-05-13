package com.softserve.edu.opencart.pages.common;

import com.softserve.edu.opencart.data.Currencies;
import com.softserve.edu.opencart.tools.utils_for_search_field.PageDoesNotExistException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UnsuccessfulSearchPage extends ASearchPart {
    private String thereIsNoProductThatMatchesTheSearchCriteria;
    private WebElement productLayout;
    public static String THERE_IS_NO_PRODUCT_THAT_MATCHES_THE_SEARCH_CRITERIA = "There is no product that matches the search criteria.";
    private final String NUMBER_FOR_FINDING_MESSAGE="[2]";

    public UnsuccessfulSearchPage(WebDriver driver) {
        super(driver);
        initElements();

    }

    private void initElements() {
        thereIsNoProductThatMatchesTheSearchCriteria = driver.findElement(By.xpath(".//div/p"+NUMBER_FOR_FINDING_MESSAGE)).getText();
    }

    // Page Object
    public String getMessage() {
        return thereIsNoProductThatMatchesTheSearchCriteria;
    }

    // Functional

    // Business Logic
    public UnsuccessfulSearchPage chooseCurrency(Currencies currency) throws PageDoesNotExistException {
        clickCurrencyByPartialName(currency);
        return new UnsuccessfulSearchPage(driver);
    }
}

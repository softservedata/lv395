package com.softserve.edu.opencart.pages.common;

import com.softserve.edu.opencart.data.Currencies;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UnsuccessfulSearchPage extends ASearchPart {
    private String theraIsNoProductThatMatchesTheSearchCriteria ;
    private WebElement productLayout;

    public UnsuccessfulSearchPage(WebDriver driver) {
        super(driver);
        initElements();

    }
    private void initElements() {
        theraIsNoProductThatMatchesTheSearchCriteria=driver.findElement(By.xpath(".//div/p[2]")).getText();
    }

    // Page Object
    public String getMessage(){
        return theraIsNoProductThatMatchesTheSearchCriteria;
    }

    // Functional

    // Business Logic
    public UnsuccessfulSearchPage chooseCurrency(Currencies currency) {
        clickCurrencyByPartialName(currency);
        return new UnsuccessfulSearchPage(driver);
    }
}

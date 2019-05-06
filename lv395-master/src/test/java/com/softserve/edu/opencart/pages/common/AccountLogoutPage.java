package com.softserve.edu.opencart.pages.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.softserve.edu.opencart.data.IUser;

public class AccountLogoutPage extends ARightLogoutPart {

    private WebElement continueButton;
    
    public AccountLogoutPage(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements() {
        continueButton = driver.findElement(By.cssSelector("a.btn.btn-primary"));
    }
    
    // Page Object
    
    // continueButton
    public WebElement getContinueButton() {
        return continueButton;
    }

    public String getContinueButtonText() {
        return getContinueButton().getAttribute(TAG_ATTRIBUTE_VALUE);
    }

    public void clickContinueButton() {
        getContinueButton().click();
    }
    
    // Functional

    // Business Logic
    
    public HomePage continueHomePage() {
        clickContinueButton();
        return new HomePage(driver);
    }
    
}

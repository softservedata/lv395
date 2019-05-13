package com.softserve.edu.opencart.pages.account;

import com.softserve.edu.opencart.pages.common.HomePage;
import io.qameta.allure.Step;
import org.bouncycastle.cms.PasswordRecipientId;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AccountLogoutPage extends ARightLogoutPart {

    public final String EXPECTED_ACCOUNT_MESSAGE = "Account Logout";

    private WebElement continueButton;
    private WebElement logoutMessage;
    
    public AccountLogoutPage(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements() {
        continueButton = driver.findElement(By.cssSelector("a.btn.btn-primary"));
        logoutMessage = driver.findElement(By.cssSelector("div[id='content'] > h1"));
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

    //AccountMessage
    public WebElement getAccountLogoutMessage() {
        return logoutMessage;
    }

    public String getActualAccountLogoutMessage() {
        return getAccountLogoutMessage().getText();
    }
    // Functional

    // Business Logic

    @Step("Click continue button and go to HomePage")
    public HomePage continueHomePage() {
        clickContinueButton();
        return new HomePage(driver);
    }
    
}

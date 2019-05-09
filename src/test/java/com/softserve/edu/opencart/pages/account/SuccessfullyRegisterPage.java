package com.softserve.edu.opencart.pages.account;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SuccessfullyRegisterPage extends RegisterPage {
    private WebElement continueButton;

    public SuccessfullyRegisterPage(WebDriver driver) {
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

    // Business Logic
    public MyAccountPage continueMyAccountPage() {
        clickContinueButton();
        return new MyAccountPage(driver);
    }
}

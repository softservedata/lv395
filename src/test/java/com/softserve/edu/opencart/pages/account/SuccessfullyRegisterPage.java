package com.softserve.edu.opencart.pages.account;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SuccessfullyRegisterPage extends ARightMenuPart {

    public static final String EXPECTED_SUCCESS_MESSAGE = "Your Account Has Been Created!";

    private WebElement continueButton;
    private static WebElement successMessage;

    public SuccessfullyRegisterPage(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements() {
        continueButton = driver.findElement(By.cssSelector("a.btn.btn-primary"));
        successMessage = driver.findElement(By.cssSelector("div[id='content'] > h1"));
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

    //
    public static WebElement getSuccessMessage() {
        return successMessage;
    }

    public static String getExpectedSuccessMessage() {
        return getSuccessMessage().getText();
    }

    // Business Logic
    public MyAccountPage continueMyAccountPage() {
        clickContinueButton();
        return new MyAccountPage(driver);
    }
}

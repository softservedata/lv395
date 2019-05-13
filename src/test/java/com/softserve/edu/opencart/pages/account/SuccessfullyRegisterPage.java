package com.softserve.edu.opencart.pages.account;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SuccessfullyRegisterPage extends ARightMenuPart {

    public final String EXPECTED_SUCCESS_MESSAGE = "Your Account Has Been Created!";

    private WebElement continueButton;
    private WebElement logOutButton;
    private WebElement successMessage;

    public SuccessfullyRegisterPage(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements() {
        continueButton = driver.findElement(By.cssSelector("a.btn.btn-primary"));
        successMessage = driver.findElement(By.cssSelector("div[id='content'] > h1"));
        logOutButton = driver.findElement(By.xpath("//*[@id=\"column-right\"]/div/a[13]"));
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
    public WebElement getSuccessMessage() {
        return successMessage;
    }

    public String getExpectedSuccessMessage() {
        return getSuccessMessage().getText();
    }

    //TODO logout button for CorrectDataTest

    public WebElement getLogOutButton() {
        return logOutButton;
    }

    public void clickLogOutButton() {
        getLogOutButton().click();
    }




    // Business Logic
//    public MyAccountPage continueMyAccountPage() {
//        clickContinueButton();
//        return new MyAccountPage(driver);
//    }

    public AccountLogoutPage logOut() {
        clickLogOutButton();
        return new AccountLogoutPage(driver);
    }

    public AccountLogoutPage continueAccountLogoutPage() {
        clickContinueButton();
        return new AccountLogoutPage(driver);
    }
}

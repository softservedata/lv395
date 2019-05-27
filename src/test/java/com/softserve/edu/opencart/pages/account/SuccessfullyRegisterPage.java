package com.softserve.edu.opencart.pages.account;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * SuccessfullyRegisterPage.
 */
public class SuccessfullyRegisterPage extends ARightMenuPart {

    /**
     * Expected success message.
     */
    public final String EXPECTED_SUCCESS_MESSAGE = "Your Account Has Been Created!";

    /**
     * WebElements of page.
     */
    private WebElement continueButton;
    private WebElement logOutButton;
    private WebElement successMessage;

    public SuccessfullyRegisterPage(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements() {
        continueButton = driver.findElement(By
                .cssSelector("a.btn.btn-primary"));
        successMessage = driver.findElement(By
                .cssSelector("div[id='content'] > h1"));
        logOutButton = driver.findElement(By
                .xpath("//*[@id=\"column-right\"]/div/a[13]"));
    }

    // Page Object

    /**
     * @return continueButton.
     */
    public WebElement getContinueButton() {
        return continueButton;
    }

    public String getContinueButtonText() {
        return getContinueButton().getAttribute(TAG_ATTRIBUTE_VALUE);
    }

    public void clickContinueButton() {
        getContinueButton().click();
    }

    /**
     * @return successMessage.
     */
    public WebElement getSuccessMessage() {
        return successMessage;
    }

    public String getExpectedSuccessMessage() {
        return getSuccessMessage().getText();
    }

    public WebElement getLogOutButton() {
        return logOutButton;
    }

    public void clickLogOutButton() {
        getLogOutButton().click();
    }

    /**
     * Logout.
     */
    @Step("Logout")
    public AccountLogoutPage logOut() {
        clickLogOutButton();
        return new AccountLogoutPage(driver);
    }

    /**
     * @return AccountLogoutPage;
     */
    public AccountLogoutPage continueAccountLogoutPage() {
        clickContinueButton();
        return new AccountLogoutPage(driver);
    }
}

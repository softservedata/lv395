package com.softserve.edu.opencart.pages.account;

import com.softserve.edu.opencart.pages.account.ARightLoginPart;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ChangePasswordPage extends ARightLoginPart {
    private WebElement passwordField;
    private WebElement confirmPasswordField;
    private WebElement backButton;
    private WebElement continueButton;

    public ChangePasswordPage(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements() {
        passwordField = driver.findElement(By.id("input-password"));
        confirmPasswordField = driver.findElement(By.id("input-confirm"));
        continueButton = driver.findElement(By.cssSelector("input[class*='btn-primary']"));
        backButton = driver.findElement(By.linkText("Back"));
    }

    // Page Object

    //passwordField
    public WebElement getPasswordField() {
        return passwordField;
    }

    public void clickPasswordField() {
        getPasswordField().click();
    }

    public void setPasswordField(String text) {
        getPasswordField().clear();
        getPasswordField().sendKeys(text);
    }

    public String getPasswordFieldText() {
        return getPasswordField().getText();
    }

    //confirmPasswordField
    public WebElement getConfirmPasswordField() {
        return confirmPasswordField;
    }

    public void clickConfirmPasswordField() {
        getConfirmPasswordField().click();
    }

    public void setConfirmPasswordField(String text) {
        getConfirmPasswordField().clear();
        getConfirmPasswordField().sendKeys(text);
    }

    public String getConfirmPasswordFieldText() {
        return getConfirmPasswordField().getText();
    }

    //backButton
    public WebElement getBackButton() {
        return backButton;
    }

    public void clickBackButton() {
        getBackButton().click();
    }

    public String getBackButtonText() {
        return getBackButton().getText();
    }

    //continueButton
    public WebElement getContinueButton() {
        return continueButton;
    }

    public void clickContinueButton() {
        getContinueButton().click();
    }

    public String getContinueButtonText() {
        return getBackButton().getText();
    }

    // Functional
    public void setNewPassword(String password) {
        clickPasswordField();
        setPasswordField(password);
    }

    public void confirmNewPassword(String password) {
        clickConfirmPasswordField();
        setConfirmPasswordField(password);
    }

    // Business Logic
    public void changePassword(String password) {
        setNewPassword(password);
        confirmNewPassword(password);
        clickContinueButton();
    }
}

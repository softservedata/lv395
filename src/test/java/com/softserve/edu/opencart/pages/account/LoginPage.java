package com.softserve.edu.opencart.pages.account;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.softserve.edu.opencart.data.IUser;

public class LoginPage extends ARightLogoutPart {

    private WebElement message;
    private WebElement emailField;
    private WebElement passwordField;
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements() {
        emailField = driver.findElement(By.id("input-email"));
        passwordField = driver.findElement(By.id("input-password"));
        loginButton = driver.findElement(By.cssSelector("input.btn.btn-primary"));
    }
    
    // Page Object

    public WebElement getMessage(){
        message = driver.findElement(By.cssSelector(""));
        return message;
    }
    public String getMessageText(){
        return getMessage().getText();
    }

    // emailField
    public WebElement getEmailField() {
        return emailField;
    }

    public String getEmailFieldText() {
        return getEmailField().getAttribute(TAG_ATTRIBUTE_VALUE);
    }

    public void setEmailField(String text) {
        getEmailField().sendKeys(text);
    }

    public void clearEmailField() {
        getEmailField().clear();
    }

    public void clickEmailField() {
        getEmailField().click();
    }

    // passwordField
    public WebElement getPasswordField() {
        return passwordField;
    }

    public String getPasswordFieldText() {
        return getPasswordField().getAttribute(TAG_ATTRIBUTE_VALUE);
    }

    public void setPasswordField(String text) {
        getPasswordField().sendKeys(text);
    }

    public void clearPasswordField() {
        getPasswordField().clear();
    }

    public void clickPasswordField() {
        getPasswordField().click();
    }

    // loginButton
    public WebElement getLoginButton() {
        return loginButton;
    }

    public String getLoginButtonText() {
        return getLoginButton().getAttribute(TAG_ATTRIBUTE_VALUE);
    }

    public void clickLoginButton() {
        getLoginButton().click();
    }

    // Functional

    protected void fillLoginForm(IUser user) {
        clickEmailField();
        clearEmailField();
        setEmailField(user.getEmail());
        clickPasswordField();
        clearPasswordField();
        setPasswordField(user.getPassword());
        clickLoginButton();
    }

    // Business Logic

    public MyAccountPage successLogin(IUser user) {
        fillLoginForm(user);
        return new MyAccountPage(driver);
    }

    public UnsuccessfulLoginPage unsuccessfulLogin(IUser invalidUser) {
        fillLoginForm(invalidUser);
        return new UnsuccessfulLoginPage(driver);
    }
}

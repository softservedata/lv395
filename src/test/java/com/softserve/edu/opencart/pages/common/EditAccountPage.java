package com.softserve.edu.opencart.pages.common;

import com.softserve.edu.opencart.data.IUser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EditAccountPage extends ARightLoginPart {
    private WebElement firstNameField;
    private WebElement lastNameField;
    private WebElement emailField;
    private WebElement telephoneField;
    private WebElement faxField;
    private WebElement backButton;
    private WebElement continueButton;


    public EditAccountPage(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements() {
        firstNameField = driver.findElement(By.id("input-firstname"));
        lastNameField = driver.findElement(By.id("input-lastname"));
        emailField = driver.findElement(By.id("input-email"));
        telephoneField = driver.findElement(By.id("input-telephone"));
        faxField = driver.findElement(By.id("input-fax"));
        backButton = driver.findElement(By.xpath("//a[contains(text(),'Back')]"));
        continueButton = driver.findElement(By.xpath("//input[contains(@class,'btn-primary')]"));
    }

    // Page Object
    //firstNameField
    public WebElement getFirstNameField() {
        return firstNameField;
    }

    public void clickFirstNameField() {
        getFirstNameField().click();
    }

    public void setFirstNameField(String text) {
        getFirstNameField().clear();
        getFirstNameField().sendKeys(text);
    }

    public String getFirstNameFieldText() {
        return getFirstNameField().getText();
    }

    //lastNameField
    public WebElement getLastNameField() {
        return lastNameField;
    }

    public void clickLastNameField() {
        getLastNameField().click();
    }

    public void setLastNameField(String text) {
        getLastNameField().clear();
        getLastNameField().sendKeys(text);
    }

    public String getLastNameFieldText() {
        return getLastNameField().getText();
    }

    //emailField
    public WebElement getEmailField() {
        return emailField;
    }

    public void clickEmailField() {
        getEmailField().click();
    }

    public void setEmailField(String text) {
        getEmailField().clear();
        getEmailField().sendKeys(text);
    }

    public String getEmailFieldText() {
        return getEmailField().getText();
    }

    //telephoneField
    public WebElement getTelephoneField() {
        return telephoneField;
    }

    public void clickTelephoneField() {
        getTelephoneField().click();
    }

    public void setTelephoneField(String text) {
        getTelephoneField().clear();
        getTelephoneField().sendKeys(text);
    }

    public String getTelephoneFieldText() {
        return getTelephoneField().getText();
    }

    //faxField
    public WebElement getFaxField() {
        return faxField;
    }

    public void clickFaxField() {
        getFaxField().click();
    }

    public void setFaxField(String text) {
        getFaxField().clear();
        getFaxField().sendKeys(text);
    }

    public String getFaxFieldText() {
        return getFaxField().getText();
    }

    //backButton
    public WebElement getBackButton() {
        return backButton;
    }

    public void clickBackButton() {
        backButton.click();
    }

    public String getBackButtondText() {
        return getBackButton().getText();
    }

    //continueButton
    public WebElement getContinueButton() {
        return continueButton;
    }

    public void clickContinueButton() {
        continueButton.click();
    }

    public String getContinueButtondText() {
        return getContinueButton().getText();
    }

    // Functional
    public void changeFirstName(IUser user) {
        clickFirstNameField();
        setFirstNameField(user.getFirstname());
    }

    public void changeLastName(IUser user) {
        clickLastNameField();
        setLastNameField(user.getLastname());
    }

    public void changeEmail(IUser user) {
        clickEmailField();
        setEmailField(user.getEmail());
    }

    public void changeTelephone(IUser user) {
        clickTelephoneField();
        setTelephoneField(user.getTelephone());
    }

    public void changeFax(IUser user) {
        clickFaxField();
        setFaxField(user.getFax());
    }

    // Business Logic
    public MyAccountPage changeUserInfo(IUser user) {
        changeFirstName(user);
        changeEmail(user);
        changeLastName(user);
        changeTelephone(user);
        changeFax(user);
        clickContinueButton();
        return new MyAccountPage(driver);
    }
}

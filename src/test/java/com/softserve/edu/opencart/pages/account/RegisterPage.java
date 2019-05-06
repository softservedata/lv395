package com.softserve.edu.opencart.pages.account;

import com.softserve.edu.opencart.data.IUser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class RegisterPage extends ARightLogoutPart {

    //fields
    private WebElement firstNameField;
    private WebElement lastNameField;
    private WebElement emailField;
    private WebElement telephoneNumberField;
    private WebElement faxField;
    private WebElement companyField;
    private WebElement address1Field;
    private WebElement address2Field;
    private WebElement cityField;
    private WebElement postcodeField;
    private WebElement passwordField;
    private WebElement confirmPasswordField;
    //drop downs
    private Select countryField;
    //Buttons
    private WebElement country;
    private WebElement defaultCountry;
    private WebElement regionField;
    private WebElement region;
    private WebElement submitButton;
    //CheckBox
    private WebElement privacyPolicy;
    //Error Messages
    private WebElement actualFirstNameError;
    private WebElement actualLastNameError;
    private WebElement actualEmailError;
    private WebElement actualTelephoneError;
    private WebElement actualAddressError;
    private WebElement actualCityError;
    private WebElement actualRegionError;
    private WebElement actualPasswordError;


    public RegisterPage(WebDriver driver) {
        super(driver);
        initElements();
    }

    /**
     * Locators on Registration page.
     */
    private void initElements() {
        firstNameField = driver.findElement(By.id("input-firstname"));
        lastNameField = driver.findElement(By.id("input-lastname"));
        emailField = driver.findElement(By.id("input-email"));
        telephoneNumberField = driver.findElement(By.id("input-telephone"));
        faxField = driver.findElement(By.id("input-fax"));
        companyField = driver.findElement(By.id("input-company"));
        address1Field = driver.findElement(By.id("input-address-1"));
        address2Field = driver.findElement(By.id("input-address-2"));
        cityField = driver.findElement(By.id("input-city"));
        postcodeField = driver.findElement(By.id("input-postcode"));
        countryField = new Select(driver.findElement(By.id("input-country")));
        country = driver.findElement(By.cssSelector("#input-country "
                + "> option:nth-child(8)"));
        defaultCountry = driver.findElement(By.id("input-country"));
        regionField = driver.findElement(By.id("input-zone"));
        region = driver.findElement(By.cssSelector("select[id*='input-zone'] >"
                + " option[value*='3519']"));
        passwordField = driver.findElement(By.id("input-password"));
        confirmPasswordField = driver.findElement(By.id("input-confirm"));
        privacyPolicy = driver.findElement(By
                .cssSelector("input[type='checkbox']"));
        submitButton = driver.findElement(By
                .cssSelector("input[type='submit']"));
    }

    //fields

    /**
     * FirstName;
     */
    public WebElement getFirstName() {
        return firstNameField;
    }

    public String getFirstnameText() {
        return getFirstName().getAttribute(TAG_ATTRIBUTE_VALUE);
    }

    public void setFirstNameField(String text) {
        getFirstName().sendKeys(text);
    }

    public void clearFirstNameField() {
        getFirstName().clear();
    }

    public void clickFirstNameField() {
        getFirstName().click();
    }

    /**
     * LastName;
     */
    public WebElement getLastName() {
        return lastNameField;
    }

    public String getLastnameText() {
        return getLastName().getAttribute(TAG_ATTRIBUTE_VALUE);
    }

    public void setLastNameField(String text) {
        getLastName().sendKeys(text);
    }

    public void clearLastNameField() {
        getLastName().clear();
    }

    public void clickLastNameField() {
        getLastName().click();
    }

    /**
     * EmailField;
     */
    public WebElement getEmail() {
        return emailField;
    }

    public String getEmailText() {
        return getEmail().getAttribute(TAG_ATTRIBUTE_VALUE);
    }

    public void setEmailField(String text) {
        getEmail().sendKeys(text);
    }

    public void clearEmailField() {
        getEmail().clear();
    }

    public void clickEmailField() {
        getEmail().click();
    }

    /**
     * TelephoneNumberField;
     */
    public WebElement getTelephoneNumber() {
        return telephoneNumberField;
    }

    public String getTelephoneText() {
        return getTelephoneNumber().getAttribute(TAG_ATTRIBUTE_VALUE);
    }

    public void setTelephoneNumberField(String text) {
        getTelephoneNumber().sendKeys(text);
    }

    public void clearTelephoneNumberField() {
        getTelephoneNumber().clear();
    }

    public void clickTelephoneNumberField() {
        getTelephoneNumber().click();
    }

    /**
     * Fax;
     */
    public WebElement getFax() {
        return faxField;
    }

    public String getFaxText() {
        return getFax().getAttribute(TAG_ATTRIBUTE_VALUE);
    }

    public void setFaxField(String text) {
        getFax().sendKeys(text);
    }

    public void clearFaxField() {
        getFax().clear();
    }

    public void clickFaxField() {
        getFax().click();
    }

    /**
     * Company;
     */
    public WebElement getCompany() {
        return faxField;
    }

    public String getCompanyText() {
        return getCompany().getAttribute(TAG_ATTRIBUTE_VALUE);
    }

    public void setCompanyField(String text) {
        getCompany().sendKeys(text);
    }

    public void clearCompanyField() {
        getCompany().clear();
    }

    public void clickCompanyField() {
        getCompany().click();
    }

    /**
     * Address1;
     */
    public WebElement getAddress1() {
        return address1Field;
    }

    public String getAddress1Text() {
        return getAddress1().getAttribute(TAG_ATTRIBUTE_VALUE);
    }

    public void setAddress1Field(String text) {
        getAddress1().sendKeys(text);
    }

    public void clearAdress1Field() {
        getAddress1().clear();
    }

    public void clickAdress1Field() {
        getAddress1().click();
    }

    /**
     * Address2;
     */
    public WebElement getAddress2() {
        return address2Field;
    }

    public String getAddress2Text() {
        return getAddress2().getAttribute(TAG_ATTRIBUTE_VALUE);
    }

    public void setAddress2Field(String text) {
        getAddress2().sendKeys(text);
    }

    public void clearAdress2Field() {
        getAddress2().clear();
    }

    public void clickAdress2Field() {
        getAddress2().click();
    }


    /**
     * CityField;
     */
    public WebElement getCity() {
        return cityField;
    }

    public String getCityText() {
        return getCity().getAttribute(TAG_ATTRIBUTE_VALUE);
    }

    public void setCityField(String text) {
        getCity().sendKeys(text);
    }

    public void clearCityField() {
        getCity().clear();
    }

    public void clickCityField() {
        getCity().click();
    }

    /**
     * Postcode;
     */
    public WebElement getPostcode() {
        return postcodeField;
    }

    public String getPostcodeText() {
        return getPostcode().getAttribute(TAG_ATTRIBUTE_VALUE);
    }

    public void setPostcodeField(String text) {
        getPostcode().sendKeys(text);
    }

    public void clearPostcodeField() {
        getPostcode().clear();
    }

    public void clickPostcodeField() {
        getPostcode().click();
    }

    /**
     * Country;
     */
    public Select getCountry() {
        return countryField;
    }

    public WebElement getCountryAsWebElement() {
        return getCountry().getWrappedElement();
    }

    public WebElement getCountrySelected() {
        return getCountry().getFirstSelectedOption();
    }

    public String getCountrySelectedText() {
        return getCountrySelected().getText().trim();
    }

    protected void setCountry(String text) {
        getCountry().selectByVisibleText(text);
    }

    public void clickCountry() {
        getCountryAsWebElement().click();
    }


    /**
     * PasswordField;
     */
    public WebElement getPasswordField() {
        return passwordField;
    }

    public void setPasswordField() {
        getPasswordField().sendKeys();
    }

    public void clearPasswordField() {
        getPasswordField().clear();
    }

    public void clickPasswordField() {
        getPasswordField().click();
    }

    /**
     * ConfirmPasswordField;
     */
    public WebElement getConfirmPasswordField() {
        return confirmPasswordField;
    }

    public void setConfirmPasswordField() {
        getConfirmPasswordField().sendKeys();
    }

    public void clearConfirmPasswordField() {
        getConfirmPasswordField().clear();
    }

    public void clickConfirmPasswordField() {
        getConfirmPasswordField().click();
    }

    //buttons

    /**
     * RegionField;
     */
    public WebElement getRegionField() {
        return regionField;
    }

    public void clickRegionField() {
        getRegionField().click();
    }

    /**
     * Region;
     */
    public WebElement getRegion() {
        return region;
    }

    public void clickRegion() {
        getRegion().click();
    }

    /**
     * SubmitButton;
     */
    public WebElement getSubmitButton() {
        return submitButton;
    }

//        public ReceiptPage clickSubmitButton() {
//            submitButton.click();
//            return new ReceiptPage(driver);
//        }

    //CheckBox

    /**
     * PrivacyPolicy;
     */
    public WebElement getPrivacyPolicy() {
        return privacyPolicy;
    }

    public void clickPrivacyPolicy() {
        privacyPolicy.click();
    }

    //Error Messages

    /**
     * CheckErrorMessages;
     */
    public void checkErrorMessages() {
        actualFirstNameError = driver.findElement(By.cssSelector("#account "
                + "> div:nth-child(3) > div > div"));
        actualLastNameError = driver.findElement(By.cssSelector("#account "
                + "> div:nth-child(4) > div > div"));
        actualEmailError = driver.findElement(By.cssSelector("#account "
                + "> div:nth-child(5) > div > div"));
        actualTelephoneError = driver.findElement(By.cssSelector("#account "
                + "> div:nth-child(6) > div > div"));
        actualAddressError = driver.findElement(By.cssSelector("#address "
                + "> div:nth-child(3) > div > div"));
        actualCityError = driver.findElement(By.cssSelector("#address "
                + "> div:nth-child(5) > div > div"));
        actualRegionError = driver.findElement(By.cssSelector("#address"
                + " > div:nth-child(8) > div > div"));
        actualPasswordError = driver.findElement(By.cssSelector("#content >"
                + " form > fieldset:nth-child(3) >"
                + " div.form-group.required.has-error >"
                + " div > div"));

        /////////////////////////////////////////////////////////////////////////
        Assert.assertEquals("First Name must be between 1 and 32 characters!",
                actualFirstNameError.getText());
        Assert.assertEquals("Last Name must be between 1 and 32 characters!",
                actualLastNameError.getText());
        Assert.assertEquals("E-Mail Address does not appear to be valid!",
                actualEmailError.getText());
        Assert.assertEquals("Telephone must be between 3 and 32 characters!",
                actualTelephoneError.getText());
        Assert.assertEquals("Address 1 must be between 3 and 128 characters!",
                actualAddressError.getText());
        Assert.assertEquals("City must be between 2 and 128 characters!",
                actualCityError.getText());
        Assert.assertEquals("Please select a region / state!",
                actualRegionError.getText());
        Assert.assertEquals("Password must be between 4 and 20 characters!",
                actualPasswordError.getText());
    }


    //Business Logic

//    /**
//     * Register User Successfully.
//     */
//    public void registerUserSuccessfully(IUser user) {
//        setFirstNameField(user.getFirstname());
//        setLastNameField();
//        setEmailField();
//        setTelephoneNumberField();
//        setAddress1Field();
//        setCityField();
//        setPostcodeField();
//        clickRegionField();
//        clickRegion();
//        setPasswordField();
//        setConfirmPasswordField();
//        clickPrivacyPolicy();
//    }
//
//    /**
//     * Register User Unsuccessfully.
//     */
//    public void registerUserUnsuccessfully(IUser user) {
//        setFirstNameField(user.getFirstname());
//        setLastNameField();
//        setEmailField();
//        setTelephoneNumberField();
//        setAddress1Field();
//        setCityField();
//        setPostcodeField();
//        setPasswordField();
//        setConfirmPasswordField();
//        clickPrivacyPolicy();
//    }
}



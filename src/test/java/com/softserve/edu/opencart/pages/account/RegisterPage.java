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
    private WebElement subscribeYes;
    private WebElement subscribeNo;
    //drop downs
    private Select countryField;
    private Select regionField;
    //Button
    private WebElement continueButton;
    //CheckBox
    private WebElement privacyPolicy;

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
        regionField = new Select(driver.findElement(By.id("input-zone")));
        passwordField = driver.findElement(By.id("input-password"));
        confirmPasswordField = driver.findElement(By.id("input-confirm"));
        privacyPolicy = driver.findElement(By
                .cssSelector("input[type='checkbox']"));
        continueButton = driver.findElement(By
                .cssSelector("input[type='submit']"));
        subscribeYes = driver.findElement(By.cssSelector("label.radio-inline" +
                " > input[value='1']"));
        subscribeNo = driver.findElement(By.cssSelector("label.radio-inline " +
                " > input[value='0']"));
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
        return companyField;
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

    public void clearAddress1Field() {
        getAddress1().clear();
    }

    public void clickAddress1Field() {
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

    public void clearAddress2Field() {
        getAddress2().clear();
    }

    public void clickAddress2Field() {
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
     * Region;
     */
    public Select getRegion() {
        return regionField;
    }

    public WebElement getRegionAsWebElement() {
        return getRegion().getWrappedElement();
    }

    public WebElement getRegionSelected() {
        return getRegion().getFirstSelectedOption();
    }

    public String getRegionSelectedText() {
        return getRegionSelected().getText().trim();
    }

    protected void setRegion(String text) {
        getRegion().selectByVisibleText(text);
    }

    public void clickRegion() {
        getRegionAsWebElement().click();
    }

    /**
     * PasswordField;
     */
    public WebElement getPassword() {
        return passwordField;
    }

    public String getPasswordText() {
        return getPassword().getAttribute(TAG_ATTRIBUTE_VALUE);
    }

    public void setPasswordField(String text) {
        getPassword().sendKeys(text);
    }

    public void clearPasswordField() {
        getPassword().clear();
    }

    public void clickPasswordField() {
        getPassword().click();
    }

    /**
     * ConfirmPasswordField;
     */
    public WebElement getConfirmPassword() {
        return confirmPasswordField;
    }

    public String getConfirmPasswordText() {
        return getConfirmPassword().getAttribute(TAG_ATTRIBUTE_VALUE);
    }

    public void setConfirmPasswordField(String text) {
        getConfirmPassword().sendKeys(text);
    }

    public void clearConfirmPasswordField() {
        getConfirmPassword().clear();
    }

    public void clickConfirmPasswordField() {
        getConfirmPassword().click();
    }

    /**
     * SubscribeYes
     */
    public WebElement getSubscribeYes() {
        return subscribeYes;
    }

    public void clickSubscribeYes() {
        getSubscribeYes().click();
    }

    /**
     * SubscribeNo
     */
    public WebElement getSubscribeNo() {
        return subscribeNo;
    }

    public void clickSubscribeNo() {
        getSubscribeNo().click();
    }

    //CheckBox

    /**
     * PrivacyPolicy;
     */
    public WebElement getPrivacyPolicy() {
        return privacyPolicy;
    }

    public void clickPrivacyPolicy() {
        getPrivacyPolicy().click();
    }

    //button

    /**
     * SubmitButton;
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

    //TODO Methods
    //functional

    public void fillFirstName(IUser user) {
        clickFirstNameField();
        clearFirstNameField();
        setFirstNameField(user.getFirstname());
    }

    public void fillLastName(IUser user) {
        clickLastNameField();
        clearLastNameField();
        setLastNameField(user.getLastname());
    }

    public void fillEmail(IUser user) {
        clickEmailField();
        clearEmailField();
        setEmailField(user.getEmail());
    }

    public void fillTelephone(IUser user) {
        clickTelephoneNumberField();
        clearTelephoneNumberField();
        setTelephoneNumberField(user.getTelephone());
    }

    public void fillFax(IUser user) {
        clickFaxField();
        clearFaxField();
        if (user.getFax() != null && user.getFax().trim().length() > 0) {
            setFaxField(user.getFax());
        }
    }

    public void fillCompany(IUser user) {
        clickCompanyField();
        clearCompanyField();
        if (user.getCompany() != null && user.getCompany().trim().length() > 0) {
            setCompanyField(user.getCompany());
        }
    }

    public void fillAddress1(IUser user) {
        clickAddress1Field();
        clearAddress1Field();
        setAddress1Field(user.getAddress1());
    }

    public void fillAddress2(IUser user) {
        clickAddress2Field();
        clearAddress2Field();
        if (user.getAddress2() != null && user.getAddress2().trim().length() > 0) {
            setAddress2Field(user.getAddress2());
        }
    }

    public void fillCity(IUser user) {
        clickCityField();
        clearCityField();
        setCityField(user.getCity());
    }

    public void fillPostcode(IUser user) {
        clickPostcodeField();
        clearPostcodeField();
        setPostcodeField(user.getPostcode());
    }

    public void fillCountry(IUser user) {
        clickCountry();
        setCountry(user.getCountry());//?
    }

    public void fillRegion (IUser user) {
        clickRegion();
        setRegion(user.getRegion());//?
    }

    public void fillPassword(IUser user) {
        clickPasswordField();
        clearPasswordField();
        setPasswordField(user.getPassword());
    }

    public void fillConfirmPassword(IUser user) {
        clickConfirmPasswordField();
        clearConfirmPasswordField();
        if (user.getPassword() != null//?
                && user.getPassword().trim().length() > 0) {
            setConfirmPasswordField(user.getPassword());
        } else {
            setConfirmPasswordField(user.getPassword());
        }//?
    }

    public void subscribe(IUser user) {
        if (user.isSubscribe()) {
            clickSubscribeYes();
        } else {
            clickSubscribeNo();
        }
    }

    private void fillRegistrationForm(IUser user) {
        fillFirstName(user);
        fillLastName(user);
        fillEmail(user);
        fillTelephone(user);
        fillFax(user);
        fillCompany(user);
        fillAddress1(user);
        fillAddress2(user);
        fillCity(user);
        fillPostcode(user);
        fillCountry(user);
        fillRegion(user);
        fillPassword(user);
        fillConfirmPassword(user);
        subscribe(user);
        clickPrivacyPolicy();
        clickContinueButton();
    }

    private void fillRegistrationFormWithNoPrivacyPolicy(IUser user) {
        fillFirstName(user);
        fillLastName(user);
        fillEmail(user);
        fillTelephone(user);
        fillFax(user);
        fillCompany(user);
        fillAddress1(user);
        fillAddress2(user);
        fillCity(user);
        fillPostcode(user);
        fillCountry(user);
        fillRegion(user);
        fillPassword(user);
        fillConfirmPassword(user);
        subscribe(user);

        clickContinueButton();
    }

    //Business Logic

    //valid user with valid registration
    public MyAccountPage successfullyRegisterUser(IUser validBoundaryUser) {
        //valid registration
        fillRegistrationForm(validBoundaryUser);
        return new MyAccountPage(driver);
    }

    //valid user with invalid registration
    public MyAccountPage registerUserWithoutPolicy(IUser validBoundaryUser) {
        fillRegistrationFormWithNoPrivacyPolicy(validBoundaryUser);
        return new MyAccountPage(driver);
    }

    //invalid user bad boundary data with valid registration
    public UnsuccessfullyRegisterPage userWithBadData(IUser invalidBoundaryUser) {
        fillRegistrationForm(invalidBoundaryUser);
        return new UnsuccessfullyRegisterPage(driver);
    }

    //invalid user with bad email valid registration
    public UnsuccessfullyRegisterPage userWithBadEmail(IUser userBadEmail) {
        fillRegistrationForm(userBadEmail);
        return new UnsuccessfullyRegisterPage(driver);
    }

    public UnsuccessfullyRegisterPage userWithNoData(IUser emptyFieldsUser) {
        fillRegistrationForm(emptyFieldsUser);
        return new UnsuccessfullyRegisterPage(driver);
    }

    public UnsuccessfullyRegisterPage userBoundary(IUser userBoundaryValues) {
        fillRegistrationForm(userBoundaryValues);
        return new UnsuccessfullyRegisterPage(driver);
    }

//    public UnsuccessfullRegisterPageAlert userWithNoPrivacyPolicy(IUser validUser) {
//        fillRegistrationFormWithNoPrivacyPolicy(validUser);
//        return new UnsuccessfullRegisterPageAlert(driver);
//    }

    public UnsuccessfullyRegisterPage userWithFirstNameConsistsDigits(IUser userWithFirstNameConsistsDigits) {
        fillRegistrationForm(userWithFirstNameConsistsDigits);
        return new UnsuccessfullyRegisterPage(driver);
    }
}



package com.softserve.edu.opencart.pages.account;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class RegisterPage extends ARightLogoutPart {


        //fields
        private WebElement firstNameField;
        private WebElement lastNameField;
        private WebElement emailField;
        private WebElement telephoneNumberField;
        private WebElement address1Field;
        private WebElement cityField;
        private WebElement postcodeField;
        private WebElement passwordField;
        private WebElement confirmPasswordField;
        //Buttons
        private WebElement countryField;
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
            address1Field = driver.findElement(By.id("input-address-1"));
            cityField = driver.findElement(By.id("input-city"));
            postcodeField = driver.findElement(By.id("input-postcode"));
            countryField = driver.findElement(By.id("input-country"));
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
        public WebElement getFirstNameField() {
            return firstNameField;
        }

//        public void setFirstNameField() {
//            getFirstNameField().sendKeys(data.get(0));
//        }

        public void clearFirstNameField() {
            getFirstNameField().clear();
        }

        public void clickFirstNameField() {
            getFirstNameField().click();
        }

        /**
         * LastName;
         */
        public WebElement getLastNameField() {
            return lastNameField;
        }

//        public void setLastNameField(TestData data) {
//            getLastNameField().sendKeys(data.get(1));
//        }

        public void clearLastNameField() {
            getLastNameField().clear();
        }

        public void clickLastNameField() {
            getLastNameField().click();
        }

        /**
         * EmailField;
         */
        public WebElement getEmailField() {
            return emailField;
        }

//        public void setEmailField(TestData data) {
//            getEmailField().sendKeys(data.get(2));
//        }

        public void clearEmailField() {
            getEmailField().clear();
        }

        public void clickEmailField() {
            getEmailField().click();
        }

        /**
         * TelephoneNumberField;
         */
        public WebElement getTelephoneNumberField() {
            return telephoneNumberField;
        }

//        public void setTelephoneNumberField(TestData data) {
//            getTelephoneNumberField().sendKeys(data.get(3));
//        }

        public void clearTelephoneNumberField() {
            getTelephoneNumberField().clear();
        }

        public void clickTelephoneNumberField() {
            getTelephoneNumberField().click();
        }

        /**
         * Adress1;
         */
        public WebElement getAddress1Field() {
            return address1Field;
        }

//        public void setAddress1Field(TestData data) {
//            getAddress1Field().sendKeys(data.get(4));
//        }

        public void clearAdress1Field() {
            getAddress1Field().clear();
        }

        public void clickAdress1Field() {
            getAddress1Field().click();
        }

        /**
         * CityField;
         */
        public WebElement getCityField() {
            return cityField;
        }

//        public void setCityField(TestData data) {
//            getCityField().sendKeys(data.get(5));
//        }

        public void clearCityField() {
            getCityField().clear();
        }

        public void clickCityField() {
            getCityField().click();
        }

        /**
         * Postcode;
         */
        public WebElement getPostcodeField() {
            return postcodeField;
        }

//        public void setPostcodeField(TestData data) {
//            getPostcodeField().sendKeys(data.get(6));
//        }

        public void clearPostcodeField() {
            getPostcodeField().clear();
        }

        public void clickPostcodeField() {
            getPostcodeField().click();
        }

        /**
         * PasswordField;
         */
        public WebElement getPasswordField() {
            return passwordField;
        }

//        public void setPasswordField(TestData data) {
//            getPasswordField().sendKeys(data.get(7));
//        }

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

//        public void setConfirmPasswordField(TestData data) {
//            getConfirmPasswordField().sendKeys(data.get(8));
//        }

        public void clearConfirmPasswordField() {
            getConfirmPasswordField().clear();
        }

        public void clickConfirmPasswordField() {
            getConfirmPasswordField().click();
        }

        //buttons

        /**
         * CountryField;
         */
        public WebElement getCountryField() {
            return countryField;
        }

        public void clickCountryField() {
            getCountryField().click();
        }

        /**
         * Country;
         */
        public WebElement getCountry() {
            return country;
        }

        public void clickCountry() {
            getCountry().click();
        }

        /**
         * defaultCountry;
         */
        public WebElement getDefaultCountry() {
            return defaultCountry;
        }

        public void clickDefaultCountry() {
            getDefaultCountry().click();
        }

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

        /**
         * Register User Successfully.
         * @param data data for tests.
         */
//        public void registerUserSuccessfully(TestData data) {
//            setFirstNameField(data);
//            setLastNameField(data);
//            setEmailField(data);
//            setTelephoneNumberField(data);
//            setAddress1Field(data);
//            setCityField(data);
//            setPostcodeField(data);
//            clickRegionField();
//            clickRegion();
//            setPasswordField(data);
//            setConfirmPasswordField(data);
//            clickPrivacyPolicy();
//        }
//
//        /**
//         * Register User Unsuccessfully.
//         * @param data data for tests.
//         */
//        public void registerUserUnsuccessfully(TestData data) {
//            setFirstNameField(data);
//            setLastNameField(data);
//            setEmailField(data);
//            setTelephoneNumberField(data);
//            setAddress1Field(data);
//            setCityField(data);
//            setPostcodeField(data);
//            setPasswordField(data);
//            setConfirmPasswordField(data);
//            clickPrivacyPolicy();
//        }
    }


    // Page Object

    // Functional

    // Business Logic


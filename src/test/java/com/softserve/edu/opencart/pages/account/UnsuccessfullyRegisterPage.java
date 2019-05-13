package com.softserve.edu.opencart.pages.account;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class UnsuccessfullyRegisterPage extends ARightMenuPart {


    public static final String EXPECTED_WARNING_FIRST_NAME = "First Name must be between 1 and 32 characters!";
    public static final String EXPECTED_WARNING_LAST_NAME = "Last Name must be between 1 and 32 characters!";
    public static final String EXPECTED_WARNING_EMAIL = "E-Mail Address does not appear to be valid!";
    public static final String EXPECTED_WARNING_TELEPHONE = "Telephone must be between 3 and 32 characters!";
    public static final String EXPECTED_WARNING_ADDRESS1 = "Address 1 must be between 3 and 128 characters!";
    public static final String EXPECTED_WARNING_CITY = "City must be between 2 and 128 characters!";
    public static final String EXPECTED_WARNING_REGION = "Please select a region / state!";
    public static final String EXPECTED_WARNING_PASSWORD = "Password must be between 4 and 20 characters!";
    public static final String EXPECTED_WARNING_POLICY = "Warning: You must agree to the Privacy Policy!";

    private WebElement actualFirstNameError;
    private WebElement actualLastNameError;
    private WebElement actualEmailError;
    private WebElement actualTelephoneError;
    private WebElement actualAddressError;
    private WebElement actualCityError;
    private WebElement actualRegionError;
    private WebElement actualPasswordError;
    private WebElement actualPolicyError;

    public UnsuccessfullyRegisterPage(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements() {
        //TODO valid selectors and privacy
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
    }

    /**
     * @return actualFirstNameError.
     */
    public String getActualFirstNameError() {
        return actualFirstNameError.getText();
    }

    /**
     * @return actualLastNameError.
     */
    public String getActualLastNameError() {
        return actualLastNameError.getText();
    }

    /**
     * @return actualEmailError.
     */
    public String getActualEmailError() {
        return actualEmailError.getText();
    }

    /**
     * @return actualTelephoneError.
     */
    public String getActualTelephoneError() {
        return actualTelephoneError.getText();
    }

    /**
     * @return actualAddressError.
     */
    public String getActualAddressError() {
        return actualAddressError.getText();
    }

    /**
     * @return actualCityError.
     */
    public String getActualCityError() {
        return actualCityError.getText();
    }

    /**
     * @return actualRegionError.
     */
    public String getActualRegionError() {
        return actualRegionError.getText();
    }

    /**
     * @return actualPasswordError.
     */
    public String getActualPasswordError() {
        return actualPasswordError.getText();
    }

    /**
     * @return actualPolicyError.
     */
    public String getActualPolicyErrorText() {
        return driver.findElement(By
                .cssSelector("div[class*='alert']")).getText();
    }

    //Business Logic

    public void checkErrorMessages() {
        Assert.assertEquals(EXPECTED_WARNING_FIRST_NAME, getActualFirstNameError());
        Assert.assertEquals(EXPECTED_WARNING_LAST_NAME, getActualLastNameError());
        Assert.assertEquals(EXPECTED_WARNING_EMAIL, getActualEmailError());
        Assert.assertEquals(EXPECTED_WARNING_TELEPHONE, getActualTelephoneError());
        Assert.assertEquals(EXPECTED_WARNING_ADDRESS1, getActualAddressError());
        Assert.assertEquals(EXPECTED_WARNING_CITY, getActualCityError());
        Assert.assertEquals(EXPECTED_WARNING_REGION, getActualRegionError());
        Assert.assertEquals(EXPECTED_WARNING_PASSWORD, getActualPasswordError());
    }

    public void checkPolicyError() {
        Assert.assertEquals(EXPECTED_WARNING_POLICY, getActualPolicyErrorText());
    }
}

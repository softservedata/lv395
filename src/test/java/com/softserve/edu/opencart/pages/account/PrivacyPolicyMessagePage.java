package com.softserve.edu.opencart.pages.account;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * PrivacyPolicyMessagePage.
 */
public class PrivacyPolicyMessagePage extends AUnsuccessfullyRegisterPage {

    /**
     * Expected Warning.
     */
    public final String EXPECTED_WARNING_POLICY = "Warning: You must agree to the Privacy Policy!";

    /**
     * Message.
     */
    private WebElement message;

    public PrivacyPolicyMessagePage(WebDriver driver) {
        super(driver);
        initElements();
    }

    void initElements() {
        message = driver.findElement(By.cssSelector("div[class*='alert']"));
    }

    public WebElement getMessage() {
        return message;
    }

    public String getActualPolicyErrorText() {
        return getMessage().getText();
    }
}

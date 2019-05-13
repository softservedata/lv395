package com.softserve.edu.opencart.pages.account;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PrivacyPolicyMessageComponent {

    public static final String EXPECTED_WARNING_POLICY = "Warning: You must agree to the Privacy Policy!";

    private WebDriver driver;
    private static WebElement message;

    public PrivacyPolicyMessageComponent(WebDriver driver) {
        this.driver = driver;
        initElements();
    }

    private void initElements() {
        message = driver.findElement(By.cssSelector("div[class*='alert']"));
    }

    public static WebElement getMessage() {
        return message;
    }

    public static String getActualPolicyErrorText() {
        return getMessage().getText();
    }
}

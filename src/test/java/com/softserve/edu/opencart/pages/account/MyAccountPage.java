package com.softserve.edu.opencart.pages.account;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MyAccountPage extends ARightLoginPart {

    public static final String MY_ACCOUNT_MESSAGE = "My Account";
    public static final String MY_ACCOUNT_UPDATE_MESSAGE = "Success: Your account has been successfully updated.";
    public static final String MY_PASSWORD_UPDATE_MESSAGE = "Success: Your password has been successfully updated.";

    private MyAccountMessageComponent messageComponent;
    //
    private WebElement myAccountLabel;

    public MyAccountPage(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements() {
        myAccountLabel = driver.findElement(By.cssSelector("#content > h2:first-child"));
    }

    // Page Object
    // myAccountLabel
    public WebElement getMyAccountLabel() {
        return myAccountLabel;
    }

    public String getMyAccountLabelText() {
        return getMyAccountLabel().getText();
    }

    // Functional

    public String getMessage() {
        messageComponent = new MyAccountMessageComponent(driver);
        return messageComponent.getMessageText();
    }
    // Business Logic
}

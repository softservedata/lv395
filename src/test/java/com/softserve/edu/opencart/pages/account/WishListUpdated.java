package com.softserve.edu.opencart.pages.account;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class WishListUpdated extends WishListPage {
    public final String EXPECTED_MESSAGE_WISH_REMOVE = "Success: You have modified your wish list!";

    private WebElement alertMessage;

    public WishListUpdated(WebDriver driver) {
        super(driver);
        initAlertMessage();
    }

    private void initAlertMessage() {
        alertMessage = driver.findElement(By.cssSelector(".alert.alert-success"));
    }

    public WebElement getAlertMessage() {
        return alertMessage;
    }

    public WebElement getAlertMessageCloseButton() {
        return getAlertMessage().findElement(By.cssSelector(".close"));
    }

    public String getAlertMessageText() {
        String textMessage = getAlertMessage().getText();
        // Remove x Symbol from Message
        return textMessage.substring(0, textMessage.length() - 2);
    }

    public WishListPage closeAlertMessage() {
        getAlertMessageCloseButton().click();
        return new WishListPage(driver);
    }

}
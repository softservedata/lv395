package com.softserve.edu.opencart.pages.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MyAccountPage extends ARightLoginPart {

    public static final String MY_ACCOUNT_MESSAGE = "My Account";
    //
    private WebElement myAccountLabel;
    
    public MyAccountPage(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements() {
        myAccountLabel = driver.findElement(By.cssSelector("#content > h2:first-child"));
        //myAccountLabel = driver.findElement(By.xpath("//div[@id='content']/h2[contains(text(), 'cc')]"));
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

    // Business Logic
}

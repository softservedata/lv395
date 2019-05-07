package com.softserve.edu.opencart.pages.account;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UnsuccessfulLoginPage extends LoginPage {

    private WebElement unsuccessfulMessage;
    public static final String LOGIN_PAGE_INCORRECT_CREDENTIALS_MESSAGE = "Warning: No match for E-Mail Address and/or Password.";
    public static final String TOO_MUCH_LOGIN_ATTEMPTS = "Warning: Your account has exceeded allowed number of login attempts. Please try again in 1 hour.";

    public UnsuccessfulLoginPage(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements(){
        unsuccessfulMessage = driver.findElement(By.cssSelector("div[class*='alert']"));
    }
    // Page Object

    public WebElement getUnsuccessfulMessage(){
        return unsuccessfulMessage;
    }

    public String getUnsuccessfulMessageText(){
        return getUnsuccessfulMessage().getText();
    }
    // Functional

    // Business Logic
}

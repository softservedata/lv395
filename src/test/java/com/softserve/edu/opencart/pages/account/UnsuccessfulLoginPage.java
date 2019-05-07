package com.softserve.edu.opencart.pages.account;

import com.softserve.edu.opencart.pages.account.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UnsuccessfulLoginPage extends LoginPage {

    private WebElement unsuccessfulMessage;
    public static final String ERROR_MESSAGE = "Warning: No match for E-Mail Address and/or Password.";

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

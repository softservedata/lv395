package com.softserve.edu.opencart.pages.account;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author Yurii Antokhiv
 * @version 1.0
 */
public class LoginPageMessageComponent {
    private WebDriver driver;
    private WebElement message;

    public LoginPageMessageComponent(WebDriver driver) {
        this.driver = driver;
        initElements();
    }

    private void initElements(){
        message = driver.findElement(By.cssSelector("div[class*='alert']"));
    }

    public WebElement getMessage(){
        return message;
    }

    public String getMessageText(){
        return getMessage().getText();
    }

}


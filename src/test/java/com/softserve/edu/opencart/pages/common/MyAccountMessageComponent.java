package com.softserve.edu.opencart.pages.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MyAccountMessageComponent {
    private WebDriver driver;
    private WebElement message;

    public MyAccountMessageComponent(WebDriver driver) {
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

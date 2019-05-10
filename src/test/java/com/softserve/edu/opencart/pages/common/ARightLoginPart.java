package com.softserve.edu.opencart.pages.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.softserve.edu.opencart.data.IUser;

public class ARightLoginPart extends ARightMenuPart {

    private WebElement editAccountBar;
    // passwordBar
    // logoutBar

    public ARightLoginPart(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements() {
        editAccountBar = driver.findElement(By.cssSelector("div.list-group > a[href*='route=account/edit']"));
    }

    // Page Object

    // editAccountBar
    public WebElement getEditAccountBar() {
        return editAccountBar;
    }

    public String getEditAccountBarText() {
        return getEditAccountBar().getText();
    }

    public void clickEditAccountBar() {
        getEditAccountBar().click();
    }

    // password
    // logout

    // Functional

    @Override
    protected void loginUser(IUser user) {
        System.out.println("***loginUser(IUser user) DONE");
    }
    
    // Business Logic
}

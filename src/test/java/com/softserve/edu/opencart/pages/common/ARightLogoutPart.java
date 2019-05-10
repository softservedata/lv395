package com.softserve.edu.opencart.pages.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.softserve.edu.opencart.data.UnloggedMyAccount;

public class ARightLogoutPart extends ARightMenuPart {

    private WebElement loginBar;
    // private WebElement registerBar;
    // private WebElement forgottenPasswordBar;

    public ARightLogoutPart(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements() {
        loginBar = driver.findElement(By.cssSelector("div.list-group > a[href*='route=account/login']"));
    }
    
    // Page Object

    // loginBar
    public WebElement getLoginBar() {
        return loginBar;
    }

    public String getLoginBarText() {
        return getLoginBar().getText();
    }

    public void clickLoginBar() {
        getLoginBar().click();
    }
    
    // registerBar
    // forgottenPasswordBar

    // Functional

    // Business Logic
    
    public LoginPage gotoLoginBarPage() {
        clickLoginBar();
        return new LoginPage(driver);
    }

}

package com.softserve.edu.opencart.pages.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class ARightLoginPart extends ARightMenuPart {
    private WebElement myAccountButton;
    private WebElement editAccountButton;
    private WebElement passwordButton;
    private WebElement addressBookButton;
    private WebElement wishListButton;

    public ARightLoginPart(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements() {
        myAccountButton = driver.findElement(By.linkText("My Account"));
        editAccountButton = driver.findElement(By.linkText("Edit Account"));
        passwordButton = driver.findElement(By.linkText("Password"));
        addressBookButton = driver.findElement(By.linkText("Address Book"));
        wishListButton = driver.findElement(By.linkText("Wish List"));
    }

    // Page Object

    //myAccountButton
    public WebElement getMyAccountButton() {
        return myAccountButton;
    }

    public String getMyAccountButtonText(){
        return getMyAccountButton().getText();
    }

    public void clickMyAccountButton(){
        getMyAccountButton().click();
    }

    //editAccountButton
    public WebElement getEditAccountButton() {
        return editAccountButton;
    }

    public String getEditAccountButtonText(){
        return getEditAccountButton().getText();
    }

    public EditAccountPage clickEditAccountButton(){
        getEditAccountButton().click();
        return new EditAccountPage(driver);
    }

    //passwordButton
    public WebElement getPasswordButton() {
        return passwordButton;
    }

    public String getPasswordButtonText(){
        return getPasswordButton().getText();
    }

    public void clickPasswordButton(){
        getPasswordButton().click();
    }

    //addressBookButton
    public WebElement getAddressBookButton() {
        return addressBookButton;
    }

    public String getAddressBookButtonText(){
        return getAddressBookButton().getText();
    }

    public void clickAddressBookButton(){
        getAddressBookButton().click();
    }

    //wishListButton
    public WebElement getWishListButton() {
        return wishListButton;
    }

    public String getWishListButtonText(){
        return getWishListButton().getText();
    }

    public void clickWishListButton(){
        getWishListButton().click();
    }

    // Functional

//    public MyAccountPage openMyAccountPage(){
//        clickMyAccountButton();
//        return new MyAccountPage;
//    }

    // Business Logic


}
